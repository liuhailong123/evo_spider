package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.constant.Constant;
import cn.com.evo.cms.constant.ProvinceCodeEnum;
import cn.com.evo.cms.domain.entity.cms.Source;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.repository.cms.VideoRepository;
import cn.com.evo.cms.service.cms.SourceRelService;
import cn.com.evo.cms.service.cms.SourceService;
import cn.com.evo.cms.service.cms.VideoService;
import cn.com.evo.cms.utils.MathUtil;
import cn.com.evo.cms.utils.XugglerUtil;
import cn.com.evo.provincial.service.ProvincialService;
import cn.com.evo.provincial.factory.ProvincialFactory;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.FileUtils;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

@Service
@Transactional
public class VideoServiceImpl extends AbstractBaseService<Video, String> implements VideoService {
    protected Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private ProvinceService provinceService;

    @Override
    protected BaseRepository<Video, String> getBaseRepository() {
        return videoRepository;
    }

    @Override
    public void deleteById(String id) {
        //判断视频是否被挂载
        List<SourceRel> list = sourceRelService.findBySourceId(id);
        if (list.size() > 0) {
            throw new RuntimeException("该资源已被挂载，请先断开该关系!!!");
        }

        //删除本地文件
        Video video = this.findById(id);
        File file = new File(video.getUrl());
        if (file.exists()) {
            file.delete();
        }

        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public List<Video> findBySourceId(String sourceId) {
        return videoRepository.findBySourceId(sourceId);
    }

    @Override
    public void deleteBySourceId(String sourceId) {
        List<Video> list = this.findBySourceId(sourceId);
        for (Video video : list) {
            this.deleteById(video.getId());
        }
    }

    @Override
    public Long queryVideoCount() {
        return videoRepository.queryVideoCount();
    }

    @Override
    public List<Video> getListWhereSizeAndTimeIsnull(int count) {
        return videoRepository.getListWhereSizeAndTimeIsnull(count);
    }

    @Override
    public List<Video> findByContentId(String contentId) {
        return videoRepository.findByContentId(contentId);
    }

    @Override
    public void importFile(MultipartFile[] files, int isOverwrite) {
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
                    for (int rowNum = 1; rowNum < sheet.getLastRowNum() + 1; rowNum++) {// rowNum==0为表头
                        Row row = sheet.getRow(rowNum);
                        // 视频名称
                        String name = getValue(row.getCell(0));
                        //去空格
                        name = name.replace(" ", "");
                        // 视频地址
                        String url = getValue(row.getCell(1));
                        // 分辨率
                        String resolution = getValue(row.getCell(2));
                        // 时长
                        String time = getValue(row.getCell(3));
                        // 文件后缀
                        String ext = getValue(row.getCell(4));
                        // 文件大小
                        String size = getValue(row.getCell(5));
                        // 清晰度
                        String definition = getValue(row.getCell(6));
                        // 码率
                        String bitrate = getValue(row.getCell(7));
                        // 平台来源
                        String platForm = getValue(row.getCell(8));


                        //创建资源
                        List<Source> sources = sourceService.findByNameAndType(name, 1);
                        if (sources == null || sources.size() == 0) {
                            Source source = new Source();
                            source.setName(name);
                            source.setType(1);
                            sourceService.save(source);
                            //创建视频信息
                            Video video = new Video();
                            video.setResolution(resolution);
                            video.setTime(time);
                            video.setExt(ext);
                            video.setSize(size);
                            video.setBitrate(bitrate);
                            video.setSource(source);
                            video.setUrl(url);
                            video.setType(1);
                            video.setDefinition(Integer.valueOf(definition));
                            video.setPlatform(platForm);
                            this.save(video);
                        } else {
                            Source source = sources.get(0);

                            List<Video> videos = this.findBySourceId(source.getId());
                            Video video = null;
                            if (isOverwrite == 1 && videos.size() > 0) {
                                video = videos.get(0);
                            } else {
                                video = new Video();
                            }
                            //创建视频信息
                            video.setResolution(resolution);
                            video.setTime(time);
                            video.setExt(ext);
                            video.setSize(size);
                            video.setBitrate(bitrate);
                            video.setSource(source);
                            video.setUrl(url);
                            video.setType(1);
                            video.setDefinition(Integer.valueOf(definition));
                            video.setPlatform(platForm);
                            this.save(video);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("数据导入失败" + e);
            }
        }
    }

    @Override
    public void videoUpload(MultipartFile[] files, Video video) {
        if (files == null) {
            throw new RuntimeException("没有文件");
        }
        for (MultipartFile file : files) {
            try {
                // 保存video对象 获取video对象的id
                this.save(video);

                // 基础视频上传逻辑
                baseVideoUpload(file, video);

                // 省网视频上传逻辑
                provinceVideoUpload(video);
            } catch (Exception e) {
                throw new RuntimeException("视频文件上传异常！！！", e);
            }
        }
    }

    @Override
    public void videoUpload(MultipartFile file, Integer definition, String platform) {
        if (file == null) {
            throw new RuntimeException("没有文件");
        }
        logger.error("开始：" + file.getOriginalFilename());
        try {
            Source source = new Source();
            source.setType(1);
            source.setName(getName(file.getOriginalFilename()));
            sourceService.save(source);

            Video video = new Video();
            video.setDefinition(definition);
            video.setSource(source);
            video.setPlatform(platform);

            // 保存video对象 获取video对象的id
            this.save(video);

            // 基础视频上传逻辑
            baseVideoUpload(file, video);

            // 省网视频上传逻辑
            provinceVideoUpload(video);
        } catch (Exception e) {
            logger.error("上传失败" + e.getMessage(), e);
        }
    }

    @Override
    public void videoHlsUpload(MultipartFile[] files, Video video, String dirName) {
        try {
            for (MultipartFile file : files) {
                videoHlsUpload(file, video.getDefinition(), dirName, video.getPlatform());
            }
        } catch (Exception e) {
            logger.error("上传HLS视频文件至本地逻辑异常：" + e.getMessage(), e);
            throw new RuntimeException("上传HLS视频文件至本地逻辑异常：" + e.getMessage(), e);
        }
    }

    @Override
    public void videoHlsUpload(MultipartFile file, Integer definition, String dirName, String platform) {
        try {
            // 1. 上传压缩文件至本地
            String localDir = Constant.video_upload_local_path();
            String fileName = getName(file.getOriginalFilename()) + "." + getExt(file.getOriginalFilename());
            String localPath = localDir + fileName;

            // 创建本地目录
            InputStream inputStream = file.getInputStream();
            FileUtils.mkdir(localDir);

            // 上传视频压缩文件至服务器
            File videoFile = new File(localPath);
            FileUtils.inputStreamToFile(inputStream, videoFile);

            // 2. 省网逻辑
            provinceVideoHlsUpload(localDir, fileName, dirName, definition, platform);
        } catch (Exception e) {
            logger.error("上传HLS视频文件至本地逻辑异常：" + e.getMessage(), e);
            throw new RuntimeException("上传HLS视频文件至本地逻辑异常：" + e.getMessage(), e);
        }
    }

    @Override
    public Video getByUrl(String url) {
        return videoRepository.getByUrl(url);
    }

    @Override
    public Video getByUrlAndSourceId(String url, String sourceId) {
        return videoRepository.getByUrlAndSourceId(url, sourceId);
    }

    @Override
    public List<Video> findLikeName(String name) {
        List<Video> videos = Lists.newArrayList();
        List<Source> sources = sourceService.findLikeName(name);
        if (sources.size() > 0) {
            for (Source source : sources) {
                videos.addAll(this.findBySourceId(source.getId()));
            }
        }
        return videos;
    }

    @Override
    public List<Video> findByName(String name) {
        List<Video> videos = Lists.newArrayList();
        List<Source> sources = sourceService.findByName(name);
        if (sources.size() > 0) {
            for (Source source : sources) {
                videos.addAll(this.findBySourceId(source.getId()));
            }
        }
        return videos;
    }

    @Override
    public Video getBySourceId(String sourceId) {
        return videoRepository.getBySourceId(sourceId);
    }

    /**
     * 基础视频上传逻辑
     *
     * @param file
     * @param video
     */
    private void baseVideoUpload(MultipartFile file, Video video) {
        try {
            String localDir = Constant.video_upload_local_path();
            String fileName = video.getId() + "." + getExt(file.getOriginalFilename());
            String localPath = localDir + fileName;

            // 创建本地目录
            InputStream inputStream = file.getInputStream();
            FileUtils.mkdir(localDir);

            // 上传视频文件至服务器
            File videoFile = new File(localPath);
            FileUtils.inputStreamToFile(inputStream, videoFile);


            // 更新视频信息
            XugglerUtil xu = new XugglerUtil(localPath);
            video.setType(1);
            video.setExt(getExt(file.getOriginalFilename()));
            video.setUrl(localPath);
            video.setSize(String.valueOf(MathUtil.divide(file.getSize(), 1024 * 1024, 2)));
            video.setTime(String.valueOf(MathUtil.divide(xu.getDuration(), 1000 * 1000, 0).intValue()));
            video.setBitrate(String.valueOf(MathUtil.divide(xu.getBitRate(), 1024 * 1024, 2)));
            JSONObject resolution = xu.getWidthAndHeigth();
            video.setResolution(resolution.getString("width") + "*" + resolution.getString("height"));
            this.update(video);
        } catch (Exception e) {
            logger.error("上传视频文件至本地异常：" + e.getMessage(), e);
            throw new RuntimeException("上传视频文件至本地异常:" + e.getMessage(), e);
        }
    }


    /**
     * 省网视频上传逻辑
     *
     * @param video
     */
    private void provinceVideoUpload(Video video) {
        //获取省网配置类
        Province province = getProvince();
        //获取省网操作对象
        ProvincialService provincial = getProvincial(province.getCode());
        provincial.registVideo(video, province);
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

    /**
     * 省网视频上传逻辑
     *
     * @param localDir      压缩包本地目录
     * @param localFileName 本地文件名
     * @param dirName       文件夹名称，例如：HD1080_7 HD720_2 等
     * @param definition    清晰度
     * @param platform      平台来源
     */
    private void provinceVideoHlsUpload(String localDir, String localFileName, String dirName,
                                        Integer definition, String platform) {
        Province province = provinceService.getByEnable(1);
        if (province == null) {
            throw new RuntimeException("没有可用的省网配置!!!");
        }

        // 省网code枚举
        ProvinceCodeEnum provinceCodeEnum = ProvinceCodeEnum.getByName(province.getCode());
        switch (provinceCodeEnum) {
            default:
                logger.error("当前省网暂无特殊的视频上传逻辑!!!");
                break;
        }
    }

    /**
     * 读取cell的值
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("static-access")
    private String getValue(Cell cell) {
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

    /**
     * 获取文件名称
     *
     * @param originalFileName
     * @return
     */
    private static String getName(String originalFileName) {
        int index = originalFileName.lastIndexOf(".");
        String temp = originalFileName.substring(0, index);
        return temp;
    }
}