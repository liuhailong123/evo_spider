package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.constant.Constant;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Source;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.enums.SourceRelTypeEnum;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.repository.cms.PictureRepository;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceRelService;
import cn.com.evo.cms.service.cms.SourceService;
import cn.com.evo.cms.utils.MathUtil;
import cn.com.evo.provincial.service.ProvincialService;
import cn.com.evo.provincial.factory.ProvincialFactory;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.FileUtils;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

@Service
@Transactional
public class PictureServiceImpl extends AbstractBaseService<Picture, String> implements PictureService {
    @Autowired
    private SourceService sourceService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    protected BaseRepository<Picture, String> getBaseRepository() {
        return pictureRepository;
    }


    @Override
    public void deleteById(String id) {
        //判断图片是否被挂载
        List<SourceRel> list = sourceRelService.findBySourceId(id);
        if (list.size() > 0) {
            throw new RuntimeException("该资源已被挂载，请先断开该关系!!!");
        }

        //删除本地文件
        Picture picture = this.findById(id);
        File file = new File(picture.getCloudPath());
        if (file.exists()) {
            file.delete();
        }

        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        try {
            for (String id : ids) {
                this.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("批量删除实体异常：" + e.getMessage());
        }
    }

    @Override
    public void deleteBySourceId(String sourceId) {
        List<Picture> list = this.findBySourceId(sourceId);
        for (Picture picture : list) {
            this.deleteById(picture.getId());
        }
    }

    @Override
    public List<Picture> findBySourceId(String sourceId) {
        return pictureRepository.findBySourceId(sourceId);
    }

    @Override
    public List<Picture> findByColumnId(String columnId) {
        List<Picture> list = pictureRepository.findByContentIdAndRelTypeAndSourcetype(columnId,
                SourceRelTypeEnum.columnRel.getIndex(),
                SourceTypeEnum.image.getIndex());
        return list;
    }

    @Override
    public List<Picture> findByColumnIdAndBusinessType(String columnId, int BusinessType) {
        List<Picture> list = pictureRepository.findByContentIdAndRelTypeAndSourcetypeAndBusinessType(columnId,
                SourceRelTypeEnum.columnRel.getIndex(),
                SourceTypeEnum.image.getIndex(),
                BusinessType);
        return list;
    }

    @Override
    public List<Picture> findByContentId(String contentId) {
        List<Picture> list = pictureRepository.findByContentIdAndRelTypeAndSourcetype(contentId,
                SourceRelTypeEnum.contentRel.getIndex(),
                SourceTypeEnum.image.getIndex());
        return list;
    }

    @Override
    public List<Picture> findByContentIdAndBusinessType(String contentId, int BusinessType) {
        List<Picture> list = pictureRepository.findByContentIdAndRelTypeAndSourcetypeAndBusinessType(contentId,
                SourceRelTypeEnum.contentRel.getIndex(),
                SourceTypeEnum.image.getIndex(),
                BusinessType);
        return list;
    }

    @Override
    public List<Picture> findByName(String name) {
        List<Picture> pictures = Lists.newArrayList();
        List<Source> sources = sourceService.findLikeName(name);
        if (sources.size() > 0) {
            for (Source source : sources) {
                pictures.addAll(this.findBySourceId(source.getId()));
            }
        }
        return pictures;
    }

    @Override
    public String getImageUrl(String id) {
        // 1. 获取可用域名
        Province province = provinceService.getByEnable(1);
        if (province == null) {
            throw new RuntimeException("默认省网未配置");
        }
        // 2. 拼接url返回数据
        try {
            String imageUrl = province.getImageHost() + this.findById(id).getFileName();
            return imageUrl;
        } catch (Exception e) {
            throw new RuntimeException("省网未配置默认图片域名或图片对象不存在");
        }
    }

    @Override
    public void importFile(MultipartFile[] files) {
        if (files == null) {
            throw new RuntimeException("没有文件");
        }
        for (MultipartFile mFile : files) {
            try {
                InputStream is = mFile.getInputStream();
                Workbook workbook = WorkbookFactory.create(is);
                is.close();
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new RuntimeException("导入文件的第一个sheet不存在");
                } else {
                    // rowNum==0为表头
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        if (row != null) {
                            // 名称
                            String name = getValue(row.getCell(0));
                            // 名称去空格
                            name = name.replace(" ", "");
                            // 文件名
                            String fileName = getValue(row.getCell(1));
                            // 文件后缀
                            String ext = getValue(row.getCell(2));
                            // 分辨率
                            String resolution = getValue(row.getCell(3));
                            // 文件大小
                            String size = getValue(row.getCell(4));
                            // 类型
                            String type = getValue(row.getCell(5));
                            //创建资源
                            List<Source> sources = sourceService.findByNameAndType(name, 1);
                            if (sources == null || sources.size() == 0) {
                                Source source = new Source();
                                source.setName(name);
                                source.setType(2);
                                sourceService.save(source);

                                //创建图片信息
                                Picture picture = new Picture();
                                picture.setResolution(resolution);
                                picture.setExt(ext);
                                picture.setSize(String.valueOf(MathUtil.divide(size, 1024, 2)));
                                picture.setSource(source);
                                picture.setFileName(fileName);
                                picture.setType(Integer.valueOf(type));
                                this.save(picture);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }
    }

    @Override
    public void imageUpload(MultipartFile[] files, Picture picture) {
        if (files == null) {
            throw new RuntimeException("没有文件");
        }
        for (MultipartFile file : files) {
            try {
                // 保存picture对象 获取picture对象的id
                this.save(picture);

                // 基础图片上传逻辑
                baseImageUpload(picture, file);

                // 省网图片上传逻辑
                provinceImageUpload(picture);
            } catch (Exception e) {
                throw new RuntimeException("图片文件上传异常！！！", e);
            }
        }
    }

    @Override
    public void imageUpload(MultipartFile file, Integer type) {
        if (file == null) {
            throw new RuntimeException("没有文件");
        }
        Source source = new Source();
        source.setType(2);
        source.setName(getName(file.getOriginalFilename()));
        sourceService.save(source);

        Picture picture = new Picture();
        picture.setType(type);
        picture.setSource(source);

        // 保存图片对象 获取picture对象的id
        this.save(picture);

        try {
            // 保存picture对象 获取picture对象的id
            this.save(picture);

            // 基础图片上传逻辑
            baseImageUpload(picture, file);

            // 省网图片上传逻辑
            provinceImageUpload(picture);
        } catch (Exception e) {
            throw new RuntimeException("图片文件上传异常！！！", e);
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
        try {
            if (cell == null) {
                return "";
            }
            if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                // 返回布尔类型的值
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                // 返回数值类型的值
                DecimalFormat df = new DecimalFormat("0");
                // 去除科学记数法
                return df.format(cell.getNumericCellValue());
            } else {
                // 返回字符串类型的值
                return String.valueOf(cell.getStringCellValue());
            }
        } catch (Exception e) {
            throw new RuntimeException("读取excel单元格值异常" + e.getMessage(), e);
        }
    }

    /**
     * 获取文件后缀名
     *
     * @param originalFileName 文件名
     * @return
     */
    private static String getExt(String originalFileName) {
        int index = originalFileName.lastIndexOf(".");
        String temp = originalFileName.substring(index + 1, originalFileName.length());
        return temp;
    }

    private static String getName(String originalFileName) {
        int index = originalFileName.lastIndexOf(".");
        String temp = originalFileName.substring(0, index);
        return temp;
    }

    /**
     * 基础图片上传流程
     *
     * @param picture
     * @param file
     * @throws IOException
     */
    private void baseImageUpload(Picture picture, MultipartFile file) throws IOException {
        String localDir = Constant.image_upload_local_path();
        String fileName = picture.getId() + "." + getExt(file.getOriginalFilename());
        String localPath = localDir + fileName;

        // 创建本地目录
        InputStream inputStream = file.getInputStream();
        FileUtils.mkdir(localDir);

        // 上传文件至服务器
        File pictureFile = new File(localPath);
        FileUtils.inputStreamToFile(inputStream, pictureFile);


        // 更新图片信息
        picture.setExt(getExt(file.getOriginalFilename()));
        picture.setSize(String.valueOf(MathUtil.divide(file.getSize(), 1024, 2)));
        picture.setFileName(fileName);
        picture.setCloudPath(localPath);


        FileInputStream fis = (FileInputStream) file.getInputStream();
        // 获得图像流对象
        BufferedImage bi = ImageIO.read(fis);
        // 图片高度
        Integer height = bi.getHeight();
        // 图片宽度
        Integer width = bi.getWidth();
        picture.setResolution(width + "*" + height);

        if (picture.getType() == null) {
            if (width > height) {
                picture.setType(1);
            } else {
                picture.setType(2);
            }
        }

        this.update(picture);
    }

    /**
     * 省网图片上传逻辑
     *
     * @param picture
     */
    private void provinceImageUpload(Picture picture) {
        //获取省网配置类
        Province province = getProvince();
        //获取省网操作对象
        ProvincialService provincial = getProvincial(province.getCode());
        provincial.registImage(picture, province, false);
    }

    /**
     * 移动文件至tomcat目录(机房102服务器)
     *
     * @param picture
     */
    private void registImageTo102TomcatDir(Picture picture) {
        try {
            String command = "chmod 777 " + picture.getCloudPath();
            Runtime.getRuntime().exec(command).waitFor();

            // 获取海报，进行上传
            File file = new File(picture.getCloudPath());
            if (file.exists()) {
                String command2 = "mv " + picture.getCloudPath() + " /usr/tomcat/apache-tomcat-8.5.32/webapps/cms_picture/" + picture.getFileName();
                Runtime.getRuntime().exec(command2).waitFor();
            }
        } catch (Exception e) {
            throw new RuntimeException("上传资源至tomcat目录异常：" + e.getMessage(), e);
        }
    }

    /**
     * 获取省网配置对象
     * @return
     */
    private Province getProvince(){
        Province province = provinceService.getByEnable(1);
        if (province == null) {
            throw new RuntimeException("没有可用的省网配置!!!");
        }
        return province;
    }

    /**
     * 获取省网对象
     * @param code 省网配置编码
     * @return
     */
    private ProvincialService getProvincial(String code){
        return new ProvincialFactory().createProvincial(code);
    }
}

