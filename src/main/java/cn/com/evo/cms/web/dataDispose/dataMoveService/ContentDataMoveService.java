package cn.com.evo.cms.web.dataDispose.dataMoveService;

import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.utils.WriteLogUtil;
import cn.com.evo.cms.web.dataDispose.dataMoveApi.Conn;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.apache.poi.ss.usermodel.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;


@Service
public class ContentDataMoveService {

    String id = "0";
    String name = "";

    @Autowired
    private Mapper mapper;

    @Autowired
    private ContentService contentService;

    public void importFile(MultipartFile[] files) {
        PreparedStatement pstmt = null;
        Connection conn = Conn.getConn();
        for (MultipartFile mFile : files) {
            try {
                InputStream is = mFile.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
                    System.out.println("进入sheet" + i);
                    try {
                        Sheet sheet = workbook.getSheetAt(i);

                        for (int rowNum = 2; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {//获取每行
                            try {
                                Row row = sheet.getRow(rowNum);
                                id = getValue(row.getCell(0));// 序号
                                System.out.println("进入第" + id + "行");
                                name = getValue(row.getCell(1));// 片名
//                                name = "%" + name + "%";

                                String sql = "select id,name,`code`,title,info,`year`,classifyTags,actorTags,"
                                        + "directorTags,yearTags,areaTags,scores,nameLongPy,nameShortPy,"
                                        + "`enable`,titleLongPy,titleShortPy,contentClassify,sumnum"
                                        + " from cms_content_info where name = '" + name + "'";
                                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                                ResultSet rs = pstmt.executeQuery();
                                if (rs.next()) {
                                    rs.previous();//指针上移一位
                                    while (rs.next()) {
                                        int classify = intoDBContent(rs);//内容详情入库
                                        if (classify == 2) {//判断该内容是否为剧集
                                            epiSodeChildImport(rs.getString(1), conn, pstmt);//剧集子集导入
                                        }
                                    }
                                } else {
                                    WriteLogUtil.writrLog("/Users/shilinxiao/Desktop/log/excelFileContentImport.txt", "sheet:" + i + "==序号：" + id + "===名称：" + name + "\n");
                                }
                            } catch (Exception e) {
                                WriteLogUtil.writrLog("/Users/shilinxiao/Desktop/log/excelFileContentImport.txt", "sheet:" + i + "==序号：" + id + "===名称：" + name + "\n");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("处理sheet异常");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("解析excel导入数据库异常：", e);
            } finally {
                try {
                    if (pstmt != null && conn != null) {
                        pstmt.close();// 关闭Statement对象，释放资源
                        System.out.println("关闭Statement对象，释放资源");
                        conn.close();// 关闭连接
                        System.out.println("关闭连接");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void epiSodeChildImport(String id, Connection conn, PreparedStatement pstmt) {
        try {
            String sql = "select contentId,videoId,sort from cms_content_video where contentId ='" + id + "'";
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                intoDBEpiSodeChild(rs, pstmt, conn);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void intoDBEpiSodeChild(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        String name = "";
        try {
            // 创建子集
            Content epiSodeChild = new Content();
            String contentId = rs.getString(1);
            String sourceId = rs.getString(2);
            String sql = "select name,byName from cms_video_info where id ='" + sourceId + "' ";
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet videoRs = pstmt.executeQuery();
            while (videoRs.next()) {
                System.out.println("创建子集为：" + videoRs.getString(1));
                epiSodeChild.setpId(contentId);
                epiSodeChild.setName(videoRs.getString(1));
                epiSodeChild.setTitle(videoRs.getString(2));
                epiSodeChild.setClassify(3);
                epiSodeChild.setEnable(1);
                epiSodeChild.setInfo(videoRs.getString(2));
                if (rs.getString(3) != null && !"".equals(rs.getString(3))) {
                    epiSodeChild.setSort(rs.getInt(3));
                } else {
                    epiSodeChild.setSort(1);
                }
                contentService.save(epiSodeChild);
                break;
            }
        } catch (Exception e) {
            WriteLogUtil.writrLog("/Users/shilinxiao/Desktop/log/excelFileContentImport.txt", "子集名称：" + name + "\n");
            throw new RuntimeException(e);
        }
    }


    private Integer intoDBContent(ResultSet rs) {
        Integer classify = 0;
        try {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String code = rs.getString(3);
            String title = rs.getString(4);
            String info = rs.getString(5);
            String year = rs.getString(6);
            String classifyTags = rs.getString(7);
            String actorTags = rs.getString(8);
            String directorTags = rs.getString(9);
            String yearTags = rs.getString(10);
            String areaTags = rs.getString(11);
            String grade = rs.getString(12);
            String nameSpellLong = rs.getString(13);
            String nameSpellShort = rs.getString(14);
            int enable = 0;
            if (rs.getString(15) == null || "".equals(rs.getString(15))) {
                enable = 0;
            } else {
                enable = rs.getInt(15);
            }
            String titleSpellLong = rs.getString(16);
            String titleSpellShort = rs.getString(17);

            if (rs.getString(18) != null || !"".equals(rs.getString(18))) {
                if (rs.getInt(18) == 3) {
                    classify = 2;
                } else {
                    classify = rs.getInt(18);
                }
            }
            int sumNum = 0;
            if (rs.getString(19) != null || !"".equals(rs.getString(19))) {
                if (classify != 1) {
                    sumNum = rs.getInt(19);
                }
            }
            Content content = contentService.getByNamePublish(name);
            if (content == null) {
                content = new Content();
                content.setName(name);
                content.setCode(code);
                content.setTitle(title);
                content.setInfo(info);
                content.setYear(year);
                content.setClassifyTags(classifyTags);
                content.setActorTags(actorTags);
                content.setDirectorTags(directorTags);
                content.setYearTags(yearTags);
                content.setAreaTags(areaTags);
                content.setGrade(grade);
                content.setNameSpellLong(nameSpellLong);
                content.setNameSpellShort(nameSpellShort);
                content.setTitleSpellLong(titleSpellLong);
                content.setTitleSpellShort(titleSpellShort);
                content.setSort(1);
                content.setClassify(classify);
                content.setSumNum(sumNum);
                contentService.save(content);
                contentService.dataMoveUpdate(id, content.getId());
            } else {
                classify = 0;
            }
            return classify;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取cell的值
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("static-access")
    public String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            DecimalFormat df = new DecimalFormat("0");
            return df.format(cell.getNumericCellValue());// 去除科学记数法
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }
}
