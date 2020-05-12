package cn.com.evo.cms.web.dataDispose.dataMoveApi;

import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceRelService;
import com.frameworks.core.web.controller.BaseController;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.util.List;

@Controller
@RequestMapping("/api/bookPictureMove")
public class BookPictureMove extends BaseController {
    /**
     * 连接
     */
    Connection conn = null;
    /**
     * 总条数
     */
    private int total;
    /**
     * 数据分页查询 开始值
     */
    private int start = 0;
    /**
     * 已迁移数
     */
    private int count = 0;

    @Autowired
    private PictureService pictureService;
    @Autowired
    private SourceRelService sourceRelService;

    /**
     * 迁移
     */
    @RequestMapping(value = "/migrate/{count}", method = {RequestMethod.GET})
    @ResponseBody
    public void migrate(@PathVariable("count") int count) {
        try {
            this.count = count;
            total = getTotal();
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 迁移开始
     */
    private void start() {
        PreparedStatement pstmt = null;
        if (conn == null) {
            conn = Conn.getConn();
        }
        try {
            String sql = "select bookId,pictureId,classify from book_picture " + "ORDER BY createDate desc LIMIT "
                    + count + ",500";
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                intoDB(rs);
                System.out.println(++count);
            }
            if (total - count > 0) {
                this.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && conn != null) {
                    pstmt.close();// 关闭Statement对象，释放资源
                    conn.close();// 关闭连接
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void intoDB(ResultSet rs) {
        try {
            String bookId = rs.getString(1);
            String pictureId = rs.getString(2);
            Integer classify = rs.getInt(3);
            if (bookId != null || !"".equals(bookId)) {
                if (pictureId != null) {
                    List<Picture> pictures = pictureService.findBySourceId(pictureId);
                    if (pictures.size() > 0) {
                        SourceRel sourceRel = new SourceRel();
                        if (classify == 1) {
                            sourceRel.setBusinessType(1);//海报
                        } else {
                            sourceRel.setBusinessType(7);//推荐
                        }
                        sourceRel.setRelType(1);//内容资源关系
                        sourceRel.setSourcetype(2);//图片
                        sourceRel.setfId(rs.getString(1));
                        sourceRel.setSourceId(pictures.get(0).getId());
                        sourceRelService.save(sourceRel);
                    } else {
                        System.out.println(count);
                        System.out.println(pictureId);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取总条数
     *
     * @return
     */
    private int getTotal() {
        int total = 0;
        Connection conn = Conn.getConn();
        PreparedStatement pstmt = null;
        try {
            String sql = "select count(*) from book_picture";
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && conn != null) {
                    pstmt.close();// 关闭Statement对象，释放资源
                    conn.close();// 关闭连接
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return total;
    }
}
