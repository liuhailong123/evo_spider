package cn.com.evo.cms.service.impl.book;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.cms.domain.entity.book.*;
import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.enums.BusinessTypeEnum;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.repository.book.BookInfoRepository;
import cn.com.evo.cms.repository.cms.IndexConfChildRepository;
import cn.com.evo.cms.repository.cms.PictureRepository;
import cn.com.evo.cms.repository.cms.SourceRepository;
import cn.com.evo.cms.service.book.*;
import cn.com.evo.cms.service.cms.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.shiro.ShiroUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookInfoServiceImpl extends AbstractBaseService<BookInfo, String> implements BookInfoService {

    @Autowired
    private BookInfoRepository bookInfoRepository;
    @Autowired
    private IndexConfChildRepository indexConfChildRepository;

    @Autowired
    private PictureService pictureInfoService;

    @Autowired
    private BookPictureService bookPictureService;

    @Autowired
    private BookChildInfoService bookChildInfoService;

    @Autowired
    private ColumnService columnsInfoService;

    @Autowired
    private BookStorageService bookStorageService;

    @Autowired
    private BookInOutRecordService bookInOutRecordService;

    @Autowired
    private SourceRelService sourceRelService;

    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private PictureRepository pictureRepository;



    @Override
    protected BaseRepository<BookInfo, String> getBaseRepository() {
        return bookInfoRepository;
    }

    @Override
    public void importFile(MultipartFile[] bookInfoFile, MultipartFile[] pictureInfoFile, MultipartFile[] relFile) {
        if (bookInfoFile == null) {
            throw new RuntimeException("没有文件");
        }

        // 导入图书信息
        for (MultipartFile bookFile : bookInfoFile) {
            try {
                InputStream is = bookFile.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                        // rowNum==0为表头
                        Row row = sheet.getRow(rowNum);
                        // 图书id
                        String bookId = getValue(row.getCell(0));
                        // 图书名称
                        String name = getValue(row.getCell(1));
                        //去空格
                        name = name.replace(" ", "");
                        // 图书书号
                        String number = getValue(row.getCell(2));
                        // 作者
                        String author = getValue(row.getCell(3));
                        // 类型
                        String tag = getValue(row.getCell(4));
                        // 简介
                        String info = getValue(row.getCell(5));
                        // 最小年龄
                        String minAge = getValue(row.getCell(6));
                        // 最大年龄
                        String maxAge = getValue(row.getCell(7));

                        // 出版社
                        String supplier = getValue(row.getCell(8));

                        BookInfo bookInfo = bookInfoRepository.findOne(bookId);
                        if (bookInfo == null) {
                            String ageTag = minAge + "-" + maxAge;
                            bookInfoRepository.insertEntity(bookId, name, number, author, tag, info, ageTag, supplier);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }
        // 导入海报信息
        for (MultipartFile pictureFIle : pictureInfoFile) {
            try {
                InputStream is = pictureFIle.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                        // rowNum==0为表头
                        Row row = sheet.getRow(rowNum);
                        // 海报id
                        String imageId = getValue(row.getCell(0));
                        // 海报名称
                        String imageName = getValue(row.getCell(1));
                        //去空格
                        imageName = imageName.replace(" ", "");
                        // 海报文件名称
                        String imageFileName = getValue(row.getCell(2));
                        // 海报地址
                        String wangxiangUrl = getValue(row.getCell(3));
                        // 宽
                        String width = getValue(row.getCell(4));
                        // 高
                        String height = getValue(row.getCell(5));
                        // 大小
                        String size = getValue(row.getCell(6));
                        // 文件后缀
                        String ext = getValue(row.getCell(7));

                        if (StringUtils.isNotBlank(imageId) &&
                                StringUtils.isNotBlank(imageId.trim())) {
                            Picture picture = pictureInfoService.findById(imageId);
                            if (picture == null) {
                                sourceRepository.insertEntity(imageName, 2);

                                List<Source> temps = sourceService.findByName(imageName);

                                pictureRepository.insertEntity(imageId, temps.get(0).getId(), size, ext, wangxiangUrl, imageFileName, width + "*" + height, 1);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }

        // 导入关系信息
        for (MultipartFile rel : relFile) {
            try {
                InputStream is = rel.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                        // rowNum==0为表头
                        Row row = sheet.getRow(rowNum);
                        // 图书id
                        String bookId = getValue(row.getCell(0));
                        // 海报id
                        String imageId = getValue(row.getCell(1));
                        if (StringUtils.isNotBlank(imageId) &&
                                StringUtils.isNotBlank(imageId.trim()) &&
                                StringUtils.isNotBlank(bookId) &&
                                StringUtils.isNotBlank(bookId.trim())) {
                            SourceRel sourceRel = new SourceRel();
                            sourceRel.setSourceId(imageId);
                            sourceRel.setfId(bookId);
                            sourceRel.setRelType(1);
                            sourceRel.setSourcetype(SourceTypeEnum.image.getIndex());
                            sourceRel.setBusinessType(BusinessTypeEnum.cover.getIndex());

                            sourceRelService.saveOrUpdate(sourceRel);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
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
            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                Date theDate = cell.getDateCellValue();
                SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
                return dff.format(theDate);
            } else {
                DecimalFormat df = new DecimalFormat("0");
                return df.format(cell.getNumericCellValue());
            }

        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }

    @Override
    public void changeBookPictureEnable(String bookpictureId) {
        BookPicture bookPicture = bookPictureService.findById(bookpictureId);
        if (bookPicture.getEnable() == 0) {
            bookPicture.setEnable(1);
            bookPictureService.saveOrUpdate(bookPicture);
        } else {
            bookPicture.setEnable(0);
            bookPictureService.saveOrUpdate(bookPicture);
        }
    }

    @Override
    public void update(BookInfo entity, String bookPicture) {
        // 更新实体
        String columnIds = getColumnIds(entity);
        entity.setColumnsIds(columnIds);
        super.update(entity);
        // 保存内容与推荐图片关系
        saveBookPicture(bookPicture);
    }

    @Override
    public void save(BookInfo entity) {
        /**
         * 关联海报
         */
        List<Picture> pictureInfos = pictureInfoService.findByName(entity.getNumber());
        if (pictureInfos.size() > 0) {
            BookPicture bookPicture = new BookPicture();
            bookPicture.setBookInfo(entity);
            bookPicture.setPictureInfo(pictureInfos.get(0));
            bookPictureService.saveOrUpdate(bookPicture);
        }
        String columnIds = getColumnIds(entity);
        entity.setColumnsIds(columnIds);
        super.save(entity);
    }

    private String getColumnIds(BookInfo entity) {
        String columnIds = "";
        if (!"".equals(entity.getColumnsNames()) && entity.getColumnsNames() != null) {
            String[] columnsNames = entity.getColumnsNames().split(",");
            if (columnsNames.length > 0) {
                for (String columnsName : columnsNames) {
                    List<Column> columCnsInfos = columnsInfoService.findByNameAndEnable(columnsName, 1);
                    if (columCnsInfos.size() > 0) {
                        for (Column columnsInfo : columCnsInfos) {
                            columnIds += columnsInfo.getId() + ",";
                        }
                    }
                }
            }
        }
        return columnIds;
    }

    private void saveBookPicture(String bookPicture) {
        JSONArray temp = JSONArray.parseArray(bookPicture);
        if (temp != null) {
            for (int i = 0; i < temp.size(); i++) {
                JSONObject cv = temp.getJSONObject(i);
                String bookId = cv.getString("bookId");
                String pictureId = cv.getString("pictureId");
                Integer enable = cv.getInteger("enable");
                bookPictureService.save(bookId, pictureId, enable);
            }
        }
    }

    /**
     * 增加库存
     */
    @Override
    public void addRepertory(BookInfo entity, String storageId, String addCount) {
        entity = this.findById(entity.getId());
        Integer repertoryCountAfter = null;
        /**
         * 记录增加库存操作记录
         */
        BookInOutRecord bookInOutRecord = new BookInOutRecord();
        bookInOutRecord.setBookInfo(entity);
        bookInOutRecord.setNumber(entity.getNumber());
        bookInOutRecord.setName(entity.getName());
        bookInOutRecord.setRemark("增加库存");
        bookInOutRecord.setCount(Integer.valueOf(addCount));
        if (entity.getRepertoryCount() == null || "".equals(entity.getRepertoryCount())) {
            entity.setRepertoryCount("0");
        }
        List<BookInOutRecord> bookInOutRecordList = this.bookInOutRecordService.findByBookId(entity.getId());
        if (bookInOutRecordList.size() == 0) {
            bookInOutRecord.setRepertoryCountBefore(0);
            repertoryCountAfter = Integer.valueOf(addCount);
            bookInOutRecord.setRepertoryCountAfter(repertoryCountAfter);
        } else {
            bookInOutRecord.setRepertoryCountBefore(Integer.valueOf(bookInOutRecordList.size()));
            repertoryCountAfter = Integer.valueOf(addCount) + Integer.valueOf(bookInOutRecordList.size());
            bookInOutRecord.setRepertoryCountAfter(repertoryCountAfter);
        }
        // 获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        if (shiroUser != null) {
            Account account = shiroUser.getAccount();
            bookInOutRecord.setAccount(account);
        }
        bookInOutRecord.setCreateDate(getToDay());
        bookInOutRecordService.save(bookInOutRecord);
        /**
         * 自动生成图书子表数据
         */
        BookChildInfo bookChildInfo = bookChildInfoService.getByBookInfoIdOrderByNumberDescLimitOne(entity.getId());
        if (bookChildInfo != null) {
            for (int i = bookChildInfo.getNumber() + 1; i <= bookChildInfo.getNumber()
                    + Integer.valueOf(addCount); i++) {
                BookChildInfo bookChildInfo2 = new BookChildInfo();
                bookChildInfo2.setBookInfo(entity);
                bookChildInfo2.setNumber(i);
                bookChildInfo2.setDepreciationRate(0);
                bookChildInfo2.setBookStorage(bookStorageService.findById(storageId));
                bookChildInfoService.save(bookChildInfo2);
            }
        } else {
            for (int i = 1; i <= Integer.valueOf(addCount); i++) {
                BookChildInfo bookChildInfo2 = new BookChildInfo();
                bookChildInfo2.setBookInfo(entity);
                bookChildInfo2.setNumber(i);
                bookChildInfo2.setDepreciationRate(0);
                bookChildInfo2.setBookStorage(bookStorageService.findById(storageId));
                bookChildInfoService.save(bookChildInfo2);
            }
        }

        /**
         * 图书表增加库存数
         */
        List<BookChildInfo> bookChildInfoList = bookChildInfoService.findByBookInfoId(entity.getId());
        entity.setRepertoryCount(bookChildInfoList.size() + "");
        this.update(entity);

    }

    @Override
    public List<BookInfo> findByTags(String tags) {
        String tagName = "%" + tags + "%";
        return bookInfoRepository.findByTags(tagName);
    }

    @Override
    public List<BookInfo> findBySectionId(String sectionId) {
        return bookInfoRepository.findBySectionId(sectionId);
    }

    private Date getToDay() {
        Date date = null;
        // 获取当前时间字符串 并指定格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String nowTimeStr = df.format(new Date());

        // 字符串转日期
        try {
            date = df.parse(nowTimeStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 获取图书上面绑定的标签
     *
     * @param str
     * @return
     */
    public static String gettagsStr(String str) {
        String[] strs = str.split(",");
        String string3 = null;
        for (int g = 0; g < strs.length; g++) {
            String string2 = null;
            if (strs[g].indexOf("-") != -1) {
                String test = strs[g];
                System.out.println(test);
                Integer in = strs[g].indexOf("-");
                Integer small = Integer.valueOf(strs[g].substring(0, in)); //适合的最小年龄数
                Integer big = Integer.valueOf(strs[g].substring(in + 1, strs[g].length() - 1)); //适合的最大年龄数
                Integer index = big - small;
                for (int i = small; i < big; i++) {
                    for (int j = small + 1; j < big + 1; j++) {
                        if (i < j) {
                            String string = String.valueOf(i) + "-" + String.valueOf(j) + "岁";
                            if (string2 == null) {
                                string2 = string;
                            } else {
                                string2 += "," + string;
                            }
                        }
                    }
                }
            } else {
                string2 = strs[g];
            }
            if (string3 == null) {
                string3 = string2;
            } else {
                string3 += "," + string2;
            }
        }
        return string3;
    }

    @Override
    public void deleteById(String id) {
        // 判断内容是否挂载至栏目
        List<CatalogueRelation> list = catalogueRelationService.findByBId(id);
        if (list.size() > 0) {
            throw new RuntimeException("该内容已被挂载至栏目，请先断开相关关系后，再进行删除！！！");
        }

        // 检测内容是否被挂载至首页
        List<IndexConfChild> indexConfChildren = indexConfChildRepository.findByContentId(id);
        if (indexConfChildren.size() > 0) {
            throw new RuntimeException(" 该内容已被挂载至首页！请先断开相关关系后，再进行删除！！！");
        }

        //删除图书资源关系
        List<SourceRel> sourceRels = sourceRelService.findByFIdAndRelType(id, 1);
        if (sourceRels != null) {
            if (sourceRels.size() > 0) {
                sourceRelService.deleteByEntities(sourceRels);
            }
        }
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }
}