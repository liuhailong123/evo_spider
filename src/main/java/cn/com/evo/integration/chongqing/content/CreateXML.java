package cn.com.evo.integration.chongqing.content;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.chongqing.content.model.*;
import cn.com.evo.integration.chongqing.content.model.Object;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.utils.BeanToXml;
import cn.com.evo.integration.shenzheng.common.chck.Chck;
import com.frameworks.utils.DateUtil;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author rf
 * @date 2019/6/5
 */
public class CreateXML {

    /**
     * 创建图片属性
     * @param picture
     * @param action
     * @param ftpPath
     * @return
     */
    public static Object createPic(Picture picture, String action, String ftpPath){
        //图片
        Object pic = new Object();
        pic.setElementType("Picture");
        pic.setID(picture.getId());
        pic.setAction(action);
        pic.setCode(picture.getId());
        ObjectPropertys hpicPropertys = new ObjectPropertys();
        String hpicPath = ftpPath + "/" + ConstantFactory.map.get(CQConstantEnum.ftp_image_path.getKey()) + getFileName(picture.getFileName());
        hpicPropertys.setFileURL(hpicPath);
        if(picture.getType() == 1){
            hpicPropertys.setDescription("h");
        }else {
            hpicPropertys.setDescription("s");
        }
        List<Property> hpicPropertysForObj = Property.createPropertysForObj(hpicPropertys);
        pic.setProperties(hpicPropertysForObj);
        return pic;
    }

    /**
     * 创建剧集头信息obj
     * @param episode
     * @param action
     * @return
     */
    public static Object getSeriesObj(Content episode, String action){
        //创建剧集属性
        Object series = new Object();
        series.setElementType("Series");
        series.setID(episode.getId());
        series.setAction(action);
        series.setCode(episode.getId());
        ObjectPropertys seriesPropertys = new ObjectPropertys();
        seriesPropertys.setType("1");
        seriesPropertys.setMobileLicense("0");
        seriesPropertys.setProvide("未来媒体科技股份有限公司");
        seriesPropertys.setName(episode.getName());
        seriesPropertys.setEnglishName(episode.getNameSpellLong());
        seriesPropertys.setSearchName(episode.getNameSpellShort());
        seriesPropertys.setPublicLicenseNo("--");
        seriesPropertys.setImportLicenseNo("--");
        seriesPropertys.setAudiovisualProgramNo("--");
        seriesPropertys.setIMDBNo("--");
        seriesPropertys.setAuthType("1");
        seriesPropertys.setAuthChannel("0/1/2");
        seriesPropertys.setLicensingWindowStart(DateUtil.thisDate());
        seriesPropertys.setLicensingWindowEnd(DateUtil.thisDateThreeEnd());
        seriesPropertys.setOriginalCountry(episode.getClassifyTags());
        seriesPropertys.setOriginalcompany("---");
        seriesPropertys.setPremierePlat("---");
        seriesPropertys.setPlayPlat("---");
        seriesPropertys.setUpdateDate("---");
        seriesPropertys.setActorDisplay(episode.getActorTags());
        seriesPropertys.setActorSearchName(episode.getActorTags());
        seriesPropertys.setWriterDisplay(episode.getDirectorTags());
        seriesPropertys.setWriterSearchName(episode.getDirectorTags());
        seriesPropertys.setLanguage(episode.getLanguage());
        seriesPropertys.setReleaseYear(episode.getYear());
        seriesPropertys.setOriginalName(episode.getName());
        seriesPropertys.setSortName(episode.getSort()+"");
        seriesPropertys.setSearchName(episode.getNameSpellShort());
        seriesPropertys.setOrgAirDate(DateUtil.getDateBYyyyyMMdd());
        seriesPropertys.setMacrovision("0");
        seriesPropertys.setPrice("0");
        seriesPropertys.setVolumnCount(episode.getSumNum() + "");
        seriesPropertys.setStatus("1");
        seriesPropertys.setDescription(episode.getInfo());
        seriesPropertys.setKeywords(episode.getClassifyTags());
        seriesPropertys.setTags(episode.getClassifyTags());
        seriesPropertys.setDefinitionFlag("1");
        series.setProperties(Property.createPropertysForObj(seriesPropertys));
        return series;
    }

    /**
     * 分集上传(创建剧集头xml字符串)
     * 创建剧集信息
     * @param episode
     * @param hPicture
     * @param sPicture
     * @param province
     * @param action
     * @return
     */
    public static String createSeriesInfo(Content episode, Picture hPicture,
                                          Picture sPicture, Province province, String action){
        Chck.chckValue("action", action);
        Chck.chckValue("contentId", episode.getId());
        Chck.chckValue("contetnName", episode.getName());
        Chck.chckValue("NameSpellLong", episode.getNameSpellLong());
        Chck.chckValue("NameSpellShort", episode.getNameSpellShort());
        Chck.chckValue("ClassifyTags", episode.getClassifyTags());
        Chck.chckValue("ActorTags", episode.getActorTags());
        Chck.chckValue("DirectorTags", episode.getDirectorTags());
        Chck.chckValue("Language", episode.getLanguage());
        Chck.chckValue("Year", episode.getYear());
        Chck.chckValue("Sort", episode.getSort()+"");
        Chck.chckValue("Info", episode.getInfo());

        ADI adi = new ADI();
        adi.setBizDomain("0");
        adi.setPriority("1");
        List<Object> objects = new ArrayList<>();
        List<Mapping> mappings = new ArrayList<>();
        //ftp路径
        String ftpPath = "ftp://" + province.getFtpUser() + ":" +
                province.getFtpPassword() + "@" + province.getFtpHost() + ":" + province.getFtpPort();
        //创建剧集对象obj
        Object series = getSeriesObj(episode, action);
        objects.add(series);
        //创建图片对象obj
        Object pic = createPic(hPicture, action, ftpPath);
        objects.add(pic);
        Object pic2 = createPic(sPicture, action, ftpPath);
        objects.add(pic2);
        //图片和主剧集绑定
        Mapping hpicAndSeriesMapping = new Mapping();
        hpicAndSeriesMapping.setAction(action);
        hpicAndSeriesMapping.setParentType("Picture");
        hpicAndSeriesMapping.setElementType("Series");
        hpicAndSeriesMapping.setParentID(hPicture.getId());
        hpicAndSeriesMapping.setElementID(episode.getId());
        hpicAndSeriesMapping.setParentCode(hPicture.getId());
        hpicAndSeriesMapping.setElementCode(episode.getId());
        MappingPropertys hpicAndSeriesMappingProperty = new MappingPropertys();
        hpicAndSeriesMappingProperty.setType("1");
        hpicAndSeriesMappingProperty.setSequence("1");
        hpicAndSeriesMapping.setProperties(Property.createPropertysForObj(hpicAndSeriesMappingProperty));
        mappings.add(hpicAndSeriesMapping);
        Mapping spicAndSeriesMapping = new Mapping();
        spicAndSeriesMapping.setAction(action);
        spicAndSeriesMapping.setParentType("Picture");
        spicAndSeriesMapping.setElementType("Series");
        spicAndSeriesMapping.setParentID(sPicture.getId());
        spicAndSeriesMapping.setElementID(episode.getId());
        spicAndSeriesMapping.setParentCode(sPicture.getId());
        spicAndSeriesMapping.setElementCode(episode.getId());
        MappingPropertys spicAndSeriesMappingProperty = new MappingPropertys();
        spicAndSeriesMappingProperty.setType("1");
        spicAndSeriesMappingProperty.setSequence("2");
        spicAndSeriesMapping.setProperties(Property.createPropertysForObj(spicAndSeriesMappingProperty));
        mappings.add(spicAndSeriesMapping);

        //挂栏目
        Mapping pkgMapping = new Mapping();
        pkgMapping.setAction(action);
        pkgMapping.setParentType("Category");
        pkgMapping.setParentID("10000100000000090000000000112061");
        pkgMapping.setParentCode("10000100000000090000000000112061");
        pkgMapping.setElementType("Series");
        pkgMapping.setElementID(episode.getId());
        pkgMapping.setElementCode(episode.getId());
        mappings.add(pkgMapping);
        adi.setObjects(objects);
        adi.setMappings(mappings);
        try {
            String str = BeanToXml.beanToXml(adi, false);
            return str;
        } catch (JAXBException e) {
            throw new RuntimeException("重庆sp子集注入流程生成剧集头xml发生异常" + e.getMessage(), e);
        }
    }

    /**
     * 创建分集xml
     * @param episode
     * @param child
     * @param hPicture
     * @param sPicture
     * @param province
     * @param action
     * @return
     */
    public static String createSeriesChild(Content episode, ContentDto child, Picture hPicture,
                                      Picture sPicture, Province province, String action){
        Video video = child.getVideo();
        Content content = child.getContent();
        Chck.chckValue("videoId", video.getId());
        Chck.chckValue("action", action);
        Chck.chckValue("contentId", content.getId());
        Chck.chckValue("contetnName", content.getName());
        Chck.chckValue("NameSpellLong", content.getNameSpellLong());
        Chck.chckValue("NameSpellShort", content.getNameSpellShort());
        Chck.chckValue("ClassifyTags", episode.getClassifyTags());
        Chck.chckValue("ActorTags", episode.getActorTags());
        Chck.chckValue("DirectorTags", episode.getDirectorTags());
        Chck.chckValue("Language", episode.getLanguage());
        Chck.chckValue("Year", episode.getYear());
        Chck.chckValue("Sort", content.getSort()+"");
        Chck.chckValue("Info", episode.getInfo());

        ADI adi = new ADI();
        adi.setBizDomain("0");
        adi.setPriority("1");
        List<Object> objects = new ArrayList<>();
        List<Mapping> mappings = new ArrayList<>();
        //ftp路径
        String ftpPath = "ftp://" + province.getFtpUser() + ":" +
                province.getFtpPassword() + "@" + province.getFtpHost() + ":" + province.getFtpPort();

        //创建片源obj
        Object movie = new Object();
        movie.setType("1");
        movie.setElementType("Movie");
        movie.setID(video.getId());
        movie.setAction(action);
        movie.setCode(video.getId());
        ObjectPropertys moviePropertys = new ObjectPropertys();
        moviePropertys.setType("1");
        String videoPath = ftpPath + "/" + ConstantFactory.map.get(CQConstantEnum.ftp_video_path.getKey()) + video.getId() + "." + video.getExt();
        //ftp全路径
        moviePropertys.setFileURL(videoPath);
        moviePropertys.setSourceDRMType("0");
        moviePropertys.setDestDRMType("0");
        moviePropertys.setAudioType("0");
        moviePropertys.setClosedCaptioning("0");
        moviePropertys.setResolution("8");
        moviePropertys.setSystemLayer("1");
        List<Property> moviePropertysForObj = Property.createPropertysForObj(moviePropertys);
        movie.setProperties(moviePropertysForObj);
        objects.add(movie);
        //创建电视剧子集属性。并把子集和剧集绑定。片源和子集内容绑定

        Object seriesChild = new Object();
        seriesChild.setElementType("Program");
        seriesChild.setID(content.getId());
        seriesChild.setAction(action);
        seriesChild.setCode(content.getId());
        ObjectPropertys seriesChildPropertys = new ObjectPropertys();
        seriesChildPropertys.setMobileLicense("0");
        seriesChildPropertys.setType("1");
        seriesChildPropertys.setGenre("Genre");
        seriesChildPropertys.setProvide("未来媒体科技股份有限公司");
        seriesChildPropertys.setName(content.getName());
        seriesChildPropertys.setEnglishName(content.getNameSpellLong());
        seriesChildPropertys.setSearchName(content.getNameSpellShort());
        seriesChildPropertys.setIssuanceLicenseNo("---");
        seriesChildPropertys.setImportLicenseNo("---");
        seriesChildPropertys.setAudiovisualProgramNo("---");
        seriesChildPropertys.setAuthType("1");
        seriesChildPropertys.setAuthChannel("0/1/2");
        seriesChildPropertys.setLicensingWindowStart(DateUtil.thisDate());
        seriesChildPropertys.setLicensingWindowEnd(DateUtil.thisDateThreeEnd());
        seriesChildPropertys.setOriginalCountry(episode.getClassifyTags());
        seriesChildPropertys.setOriginalcompany("---");
        seriesChildPropertys.setPremierePlat("---");
        seriesChildPropertys.setActorDisplay(episode.getActorTags());
        seriesChildPropertys.setActorSearchName(episode.getActorTags());
        seriesChildPropertys.setWriterDisplay(episode.getDirectorTags());
        seriesChildPropertys.setWriterSearchName(episode.getDirectorTags());
        seriesChildPropertys.setLanguage(episode.getLanguage());
        seriesChildPropertys.setReleaseYear(episode.getYear());
        seriesChildPropertys.setSourceType("1");
        seriesChildPropertys.setOriginalName(content.getName());
        seriesChildPropertys.setSortName(content.getSort()+"");
        seriesChildPropertys.setOrgAirDate(DateUtil.getDateBYyyyyMMdd());
        seriesChildPropertys.setMacrovision("0");
        seriesChildPropertys.setStatus("1");
        seriesChildPropertys.setDescription(episode.getInfo());
        seriesChildPropertys.setKeywords(episode.getClassifyTags());
        seriesChildPropertys.setTags(episode.getClassifyTags());
        seriesChildPropertys.setDefinitionFlag("1");
        seriesChildPropertys.setStorageType("2");
        seriesChildPropertys.setSeriesFlag("1");
        seriesChildPropertys.setSeriesItemNo(content.getSort()+"");
        seriesChild.setProperties(Property.createPropertysForObj(seriesChildPropertys));
        objects.add(seriesChild);

        //对上面资源进行绑定关系
        Mapping seriesMapping = new Mapping();
        seriesMapping.setAction(action);
        seriesMapping.setParentType("Series");
        seriesMapping.setElementType("Program");
        seriesMapping.setParentID(episode.getId());
        seriesMapping.setElementID(content.getId());
        seriesMapping.setParentCode(episode.getId());
        seriesMapping.setElementCode(content.getId());
        mappings.add(seriesMapping);
        //子集与片源绑定
        Mapping programAndMovieMapping = new Mapping();
        programAndMovieMapping.setAction(action);
        programAndMovieMapping.setParentType("Program");
        programAndMovieMapping.setElementType("Movie");
        programAndMovieMapping.setParentID(content.getId());
        programAndMovieMapping.setElementID(video.getId());
        programAndMovieMapping.setParentCode(content.getId());
        programAndMovieMapping.setElementCode(video.getId());
        mappings.add(programAndMovieMapping);
        System.out.println(content.getSort());

        adi.setObjects(objects);
        adi.setMappings(mappings);
        try {
            String str = BeanToXml.beanToXml(adi, false);
            return str;
        } catch (JAXBException e) {
            throw new RuntimeException("重庆sp子集注入流程生成剧集xml发生异常" + e.getMessage(), e);
        }
    }

    /**
     * 创建整体剧集xml
     * @param episode
     * @param childs
     * @param hPicture
     * @param sPicture
     * @param province
     * @param action
     * @return
     */
    public static String createSeries(Content episode, List<ContentDto> childs, Picture hPicture,
                                      Picture sPicture,  Province province, String action) {
        ADI adi = new ADI();
        adi.setBizDomain("0");
        adi.setPriority("1");
        List<Object> objects = new ArrayList<>();
        List<Mapping> mappings = new ArrayList<>();
        //创建剧集对象obj
        Object series = getSeriesObj(episode, action);
        objects.add(series);

        //图片和主剧集绑定
        Mapping hpicAndSeriesMapping = new Mapping();
        hpicAndSeriesMapping.setAction(action);
        hpicAndSeriesMapping.setParentType("Picture");
        hpicAndSeriesMapping.setElementType("Series");
        hpicAndSeriesMapping.setParentID(hPicture.getId());
        hpicAndSeriesMapping.setElementID(episode.getId());
        hpicAndSeriesMapping.setParentCode(hPicture.getId());
        hpicAndSeriesMapping.setElementCode(episode.getId());
        MappingPropertys hpicAndSeriesMappingProperty = new MappingPropertys();
        hpicAndSeriesMappingProperty.setType("1");
        hpicAndSeriesMappingProperty.setSequence("1");
        hpicAndSeriesMapping.setProperties(Property.createPropertysForObj(hpicAndSeriesMappingProperty));
        mappings.add(hpicAndSeriesMapping);
        Mapping spicAndSeriesMapping = new Mapping();
        spicAndSeriesMapping.setAction(action);
        spicAndSeriesMapping.setParentType("Picture");
        spicAndSeriesMapping.setElementType("Series");
        spicAndSeriesMapping.setParentID(sPicture.getId());
        spicAndSeriesMapping.setElementID(episode.getId());
        spicAndSeriesMapping.setParentCode(sPicture.getId());
        spicAndSeriesMapping.setElementCode(episode.getId());
        MappingPropertys spicAndSeriesMappingProperty = new MappingPropertys();
        spicAndSeriesMappingProperty.setType("1");
        spicAndSeriesMappingProperty.setSequence("2");
        spicAndSeriesMapping.setProperties(Property.createPropertysForObj(spicAndSeriesMappingProperty));
        mappings.add(spicAndSeriesMapping);

        //创建Program 和movie、pic 和表关系
        String ftpPath = "ftp://" + province.getFtpUser() + ":" +
                province.getFtpPassword() + "@" + province.getFtpHost() + ":" + province.getFtpPort();

        //设置横图定义
        Object hpic = new Object();
        hpic.setElementType("Picture");
        hpic.setID(hPicture.getId());
        hpic.setAction(action);
        hpic.setCode(hPicture.getId());
        ObjectPropertys hpicPropertys = new ObjectPropertys();
        String hpicPath = ftpPath + "/" + ConstantFactory.map.get(CQConstantEnum.ftp_image_path.getKey()) + getFileName(hPicture.getFileName());
        hpicPropertys.setFileURL(hpicPath);
        hpicPropertys.setDescription("h");
        List<Property> hpicPropertysForObj = Property.createPropertysForObj(hpicPropertys);
        hpic.setProperties(hpicPropertysForObj);
        objects.add(hpic);
        //设置竖图定义
        Object spic = new Object();
        spic.setElementType("Picture");
        spic.setID(sPicture.getId());
        spic.setAction(action);
        spic.setCode(sPicture.getId());
        ObjectPropertys spicPropertys = new ObjectPropertys();
        String spicPath = ftpPath +"/"+ ConstantFactory.map.get(CQConstantEnum.ftp_image_path.getKey()) + getFileName(sPicture.getFileName());
        spicPropertys.setFileURL(spicPath);
        spicPropertys.setDescription("s");
        List<Property> spicPropertysForObj = Property.createPropertysForObj(spicPropertys);
        spic.setProperties(spicPropertysForObj);
        objects.add(spic);

        for (ContentDto child : childs) {
            Video video = child.getVideo();
            //创建片源obj
            Object movie = new Object();
            movie.setElementType("Movie");
            movie.setID(video.getId());
            movie.setAction(action);
            movie.setCode(video.getId());
            ObjectPropertys moviePropertys = new ObjectPropertys();
            moviePropertys.setType("1");
            String videoPath = ftpPath +"/"+ ConstantFactory.map.get(CQConstantEnum.ftp_video_path.getKey()) + getFileName(video.getUrl());
            //ftp全路径
            moviePropertys.setFileURL(videoPath);
            moviePropertys.setSourceDRMType("0");
            moviePropertys.setDestDRMType("0");
            moviePropertys.setAudioType("0");
            moviePropertys.setClosedCaptioning("0");
//            moviePropertys.setOCSURL("1");
//            moviePropertys.setVideoType("1");
//            moviePropertys.setAudioFormat("0");
            moviePropertys.setResolution("8");
            moviePropertys.setSystemLayer("1");
            List<Property> moviePropertysForObj = Property.createPropertysForObj(moviePropertys);
            movie.setProperties(moviePropertysForObj);
            objects.add(movie);
            //创建电视剧子集属性。并把子集和剧集绑定。片源和子集内容绑定
            Object seriesChild = new Object();
            Content content = child.getContent();
            seriesChild.setElementType("Program");
            seriesChild.setID(content.getId());
            seriesChild.setAction(action);
            seriesChild.setCode(content.getId());
            ObjectPropertys seriesChildPropertys = new ObjectPropertys();
            seriesChildPropertys.setMobileLicense("0");
            seriesChildPropertys.setType("1");
            seriesChildPropertys.setGenre("Genre");
            seriesChildPropertys.setProvide("未来媒体科技股份有限公司");
            seriesChildPropertys.setName(content.getName());
            seriesChildPropertys.setEnglishName(content.getNameSpellLong());
            seriesChildPropertys.setSearchName(content.getNameSpellShort());
            seriesChildPropertys.setIssuanceLicenseNo("---");
            seriesChildPropertys.setImportLicenseNo("---");
            seriesChildPropertys.setAudiovisualProgramNo("---");
            seriesChildPropertys.setAuthType("1");
            seriesChildPropertys.setAuthChannel("0/1/2");
            seriesChildPropertys.setLicensingWindowStart(DateUtil.thisDate());
            seriesChildPropertys.setLicensingWindowEnd(DateUtil.thisDateThreeEnd());
            seriesChildPropertys.setOriginalCountry(content.getClassifyTags());
            seriesChildPropertys.setOriginalcompany("---");
            seriesChildPropertys.setPremierePlat("---");
            seriesChildPropertys.setActorDisplay(episode.getActorTags());
            seriesChildPropertys.setActorSearchName(episode.getActorTags());
            seriesChildPropertys.setWriterDisplay(episode.getDirectorTags());
            seriesChildPropertys.setWriterSearchName(episode.getDirectorTags());
            seriesChildPropertys.setLanguage(episode.getLanguage());
            seriesChildPropertys.setReleaseYear(episode.getYear());
            seriesChildPropertys.setSourceType("1");
            seriesChildPropertys.setOriginalName(content.getName());
            seriesChildPropertys.setSortName(content.getSort()+"");
            seriesChildPropertys.setOrgAirDate(DateUtil.getDateBYyyyyMMdd());
            seriesChildPropertys.setMacrovision("0");
            seriesChildPropertys.setStatus("1");
            seriesChildPropertys.setDescription(episode.getInfo());
            seriesChildPropertys.setKeywords(episode.getClassifyTags());
            seriesChildPropertys.setTags(episode.getClassifyTags());
            seriesChildPropertys.setDefinitionFlag("1");
            seriesChildPropertys.setStorageType("2");
            seriesChildPropertys.setSeriesFlag("1");
            seriesChildPropertys.setSeriesItemNo(content.getSort()+"");
            seriesChild.setProperties(Property.createPropertysForObj(seriesChildPropertys));
            objects.add(seriesChild);

            //对上面资源进行绑定关系
            Mapping seriesMapping = new Mapping();
            seriesMapping.setAction(action);
            seriesMapping.setParentType("Series");
            seriesMapping.setElementType("Program");
            seriesMapping.setParentID(episode.getId());
            seriesMapping.setElementID(content.getId());
            seriesMapping.setParentCode(episode.getId());
            seriesMapping.setElementCode(content.getId());
            mappings.add(seriesMapping);
            //图片和主剧集绑定
            Mapping hpicAndProgramMapping = new Mapping();
            hpicAndProgramMapping.setAction(action);
            hpicAndProgramMapping.setParentType("Picture");
            hpicAndProgramMapping.setElementType("Program");
            hpicAndProgramMapping.setParentID(hPicture.getId());
            hpicAndProgramMapping.setElementID(content.getId());
            hpicAndProgramMapping.setParentCode(hPicture.getId());
            hpicAndProgramMapping.setElementCode(content.getId());
            MappingPropertys hpicAndProgramMappingProperty = new MappingPropertys();
            hpicAndProgramMappingProperty.setType("1");
            hpicAndProgramMappingProperty.setSequence(content.getSort()+"");
            hpicAndProgramMapping.setProperties(Property.createPropertysForObj(hpicAndProgramMappingProperty));
            mappings.add(hpicAndProgramMapping);
            Mapping spicAndProgramMapping = new Mapping();
            spicAndProgramMapping.setAction(action);
            spicAndProgramMapping.setParentType("Picture");
            spicAndProgramMapping.setElementType("Program");
            spicAndProgramMapping.setParentID(sPicture.getId());
            spicAndProgramMapping.setElementID(content.getId());
            spicAndProgramMapping.setParentCode(sPicture.getId());
            spicAndProgramMapping.setElementCode(content.getId());
            MappingPropertys spicAndProgramMappingProperty = new MappingPropertys();
            spicAndProgramMappingProperty.setType("1");
            spicAndProgramMappingProperty.setSequence(content.getSort()+"");
            spicAndProgramMapping.setProperties(Property.createPropertysForObj(spicAndProgramMappingProperty));
            mappings.add(spicAndProgramMapping);
            //子集与片源绑定
            Mapping programAndMovieMapping = new Mapping();
            programAndMovieMapping.setAction(action);
            programAndMovieMapping.setParentType("Program");
            programAndMovieMapping.setElementType("Movie");
            programAndMovieMapping.setParentID(content.getId());
            programAndMovieMapping.setElementID(video.getId());
            programAndMovieMapping.setParentCode(content.getId());
            programAndMovieMapping.setElementCode(video.getId());
            mappings.add(programAndMovieMapping);
            System.out.println(content.getSort());

            //图片和片源绑定
            Mapping hpicAndMovieMapping = new Mapping();
            hpicAndMovieMapping.setAction(action);
            hpicAndMovieMapping.setParentType("Picture");
            hpicAndMovieMapping.setElementType("Movie");
            hpicAndMovieMapping.setParentID(hPicture.getId());
            hpicAndMovieMapping.setElementID(video.getId());
            hpicAndMovieMapping.setParentCode(hPicture.getId());
            hpicAndMovieMapping.setElementCode(video.getId());
            MappingPropertys hpicAndMovieMappingProperty = new MappingPropertys();
            hpicAndMovieMappingProperty.setType("1");
            hpicAndMovieMappingProperty.setSequence("1");
            hpicAndMovieMapping.setProperties(Property.createPropertysForObj(hpicAndProgramMappingProperty));
            mappings.add(hpicAndMovieMapping);
            Mapping spicAndMovieMapping = new Mapping();
            spicAndMovieMapping.setAction(action);
            spicAndMovieMapping.setParentType("Picture");
            spicAndMovieMapping.setElementType("Movie");
            spicAndMovieMapping.setParentID(sPicture.getId());
            spicAndMovieMapping.setElementID(video.getId());
            spicAndMovieMapping.setParentCode(sPicture.getId());
            spicAndMovieMapping.setElementCode(video.getId());
            MappingPropertys spicAndMovieMappingProperty = new MappingPropertys();
            spicAndMovieMappingProperty.setType("1");
            spicAndMovieMappingProperty.setSequence("1");
            spicAndMovieMapping.setProperties(Property.createPropertysForObj(spicAndProgramMappingProperty));
            mappings.add(spicAndMovieMapping);
        }
        adi.setObjects(objects);
        adi.setMappings(mappings);
        try {
            String str = BeanToXml.beanToXml(adi, false);
            return str;
        } catch (JAXBException e) {
            throw new RuntimeException("重庆sp注入流程生成剧集xml发生异常" + e.getMessage(), e);
        }
    }

    public static String createMovie(Content content, Video video, Picture hPicture, Picture sPicture,
                                     Province province, String action) {
        ADI adi = new ADI();
        adi.setBizDomain("0");
        adi.setPriority("1");
        List<Object> objects = new ArrayList<>();
        List<Mapping> mappings = new ArrayList<>();
        String ftpPath = "ftp://" + province.getFtpUser() + ":" +
                province.getFtpPassword() + "@" + province.getFtpHost() + ":" + province.getFtpPort();

        //创建片源obj
        Object movie = new Object();
        movie.setElementType("Movie");
        movie.setID(video.getId());
        movie.setAction(action);
        movie.setCode(video.getId());
        ObjectPropertys moviePropertys = new ObjectPropertys();
        moviePropertys.setType("1");
        String videoPath = ConstantFactory.map.get(CQConstantEnum.ftp_video_path.getKey()) + getFileName(video.getUrl());
        //ftp全路径
        moviePropertys.setFileURL(ftpPath + "/" + videoPath);
        moviePropertys.setSourceDRMType("0");
        moviePropertys.setDestDRMType("0");
        moviePropertys.setAudioType("0");
        moviePropertys.setScreenFormat("0");
        moviePropertys.setClosedCaptioning("0");
//        moviePropertys.setOCSURL("1");
//        moviePropertys.setVideoType("1");
//        moviePropertys.setAudioFormat("0");
        moviePropertys.setResolution("8");
        moviePropertys.setSystemLayer("1");
        List<Property> moviePropertysForObj = Property.createPropertysForObj(moviePropertys);
        movie.setProperties(moviePropertysForObj);
        objects.add(movie);

        //添加内容program
        Object program = new Object();
        program.setElementType("Program");
//        String isID = DateUtil.format(new Date(), "yyyyMMddHHmmss") + 1 + getRandom() + ConstantFactory.map.get(CQConstantEnum.csp_id.getKey());
        program.setID(content.getId());
        program.setAction(action);
        program.setCode(content.getId());
        ObjectPropertys programPropertys = new ObjectPropertys();
        programPropertys.setType("0");
        programPropertys.setProvide("未来媒体科技股份有限公司");
        programPropertys.setName(content.getName());
        programPropertys.setEnglishName(content.getNameSpellLong());
        programPropertys.setLicensingWindowStart(DateUtil.thisDate());
        programPropertys.setLicensingWindowEnd(DateUtil.thisDateThreeEnd());
        programPropertys.setAuthType("0");
        programPropertys.setAuthChannel("0/1/2");
        programPropertys.setPublicLicenseNo("--");
        programPropertys.setImportLicenseNo("--");
        programPropertys.setAudiovisualProgramNo("--");
        programPropertys.setIMDBNo("--");
        programPropertys.setOriginalName(content.getName());
        programPropertys.setSortName(content.getSort()+"");
        programPropertys.setSearchName(content.getNameSpellShort());
        programPropertys.setGenre("Genre");
        programPropertys.setActorDisplay(content.getActorTags());
        programPropertys.setActorSearchName(content.getActorTags());
        programPropertys.setWriterDisplay(content.getDirectorTags());
        programPropertys.setWriterSearchName(content.getDirectorTags());
        programPropertys.setOriginalCountry(content.getClassifyTags());
        programPropertys.setLanguage(content.getLanguage());
        programPropertys.setReleaseYear(content.getYear());
        programPropertys.setOrgAirDate(DateUtil.getDateBYyyyyMMdd());
        programPropertys.setMacrovision("0");
        programPropertys.setDescription(content.getInfo());
        programPropertys.setStatus("1");
        programPropertys.setSourceType("1");
        programPropertys.setSeriesFlag("0");
        programPropertys.setKeywords(content.getClassifyTags());
        programPropertys.setTags(content.getClassifyTags());
        programPropertys.setStorageType("0");
        programPropertys.setDefinitionFlag("1");
        List<Property> propertysForObj = Property.createPropertysForObj(programPropertys);
        program.setProperties(propertysForObj);
        objects.add(program);
        //进行关系绑定
        Mapping mappingProgramAndMovie = new Mapping();
        mappingProgramAndMovie.setAction(action);
        mappingProgramAndMovie.setParentType("Program");
        mappingProgramAndMovie.setElementType("Movie");
        mappingProgramAndMovie.setParentID(video.getId());
        mappingProgramAndMovie.setElementID(content.getId());
        mappingProgramAndMovie.setParentCode(video.getId());
        mappingProgramAndMovie.setElementCode(content.getId());
//        MappingPropertys programAndMovie = new MappingPropertys();
//      注释内容programAndMovie关系为非必填项，待联调确定是否传入
        mappings.add(mappingProgramAndMovie);


        //设置横图定义
        Object hpic = new Object();
        hpic.setElementType("Picture");
        hpic.setID(hPicture.getId());
        hpic.setAction(action);
        hpic.setCode(hPicture.getId());
        ObjectPropertys hpicPropertys = new ObjectPropertys();
        String hpicPath = ftpPath + "/" + ConstantFactory.map.get(CQConstantEnum.ftp_image_path.getKey()) + getFileName(hPicture.getFileName());
        hpicPropertys.setFileURL(hpicPath);
        hpicPropertys.setDescription("h");
        List<Property> hpicPropertysForObj = Property.createPropertysForObj(hpicPropertys);
        hpic.setProperties(hpicPropertysForObj);
        objects.add(hpic);
        //进行关系绑定
        Mapping mappingHpicAndProgram = new Mapping();
        mappingHpicAndProgram.setAction(action);
        mappingHpicAndProgram.setParentType("Picture");
        mappingHpicAndProgram.setElementType("Program");
        mappingHpicAndProgram.setParentID(hPicture.getId());
        mappingHpicAndProgram.setElementID(content.getId());
        mappingHpicAndProgram.setParentCode(hPicture.getId());
        mappingHpicAndProgram.setElementCode(content.getId());
        MappingPropertys hpicAndProgram = new MappingPropertys();
        hpicAndProgram.setType("1");
        hpicAndProgram.setSequence("1");
        List<Property> hpicAndPrograms = Property.createPropertysForObj(hpicAndProgram);
        mappingHpicAndProgram.setProperties(hpicAndPrograms);
        mappings.add(mappingHpicAndProgram);

        //设置竖图定义
        Object spic = new Object();
        spic.setElementType("Picture");
        spic.setID(sPicture.getId());
        spic.setAction(action);
        spic.setCode(sPicture.getId());
        ObjectPropertys spicPropertys = new ObjectPropertys();
        String spicPath = ftpPath + "/" + ConstantFactory.map.get(CQConstantEnum.ftp_image_path.getKey()) + getFileName(sPicture.getFileName());
        spicPropertys.setFileURL(spicPath);
        spicPropertys.setDescription("s");
        List<Property> spicPropertysForObj = Property.createPropertysForObj(spicPropertys);
        spic.setProperties(spicPropertysForObj);
        objects.add(spic);
        //进行关系绑定
        Mapping mappingSpicAndProgram = new Mapping();
        mappingSpicAndProgram.setAction(action);
        mappingSpicAndProgram.setParentType("Picture");
        mappingSpicAndProgram.setElementType("Program");
        mappingSpicAndProgram.setParentID(hPicture.getId());
        mappingSpicAndProgram.setElementID(content.getId());
        mappingSpicAndProgram.setParentCode(hPicture.getId());
        mappingSpicAndProgram.setElementCode(content.getId());
        MappingPropertys spicAndProgram = new MappingPropertys();
        spicAndProgram.setType("1");
        spicAndProgram.setSequence("2");
        List<Property> spicAndPrograms = Property.createPropertysForObj(spicAndProgram);
        mappingHpicAndProgram.setProperties(spicAndPrograms);
        mappings.add(mappingSpicAndProgram);

        //添加adi并生成xml字符串
        adi.setObjects(objects);
        adi.setMappings(mappings);
        try {
            String str = BeanToXml.beanToXml(adi, false);
            return str;
        } catch (JAXBException e) {
            throw new RuntimeException("重庆sp注入流程生成电影xml发生异常" + e.getMessage(), e);
        }
    }

    /**
     * 获取随机数(6位)
     *
     * @return
     */
    public static String getRandom() {
        Random r = new Random();
        long num = Math.abs(r.nextLong() % 9999L);
        String s = String.valueOf(num);
        for (int i = 0; i < 4 - s.length(); i++) {
            s = "0" + s;
        }
        return s;
    }

    /**
     * 获取文件名
     *
     * @param url 文件路径
     * @return
     */
    private static String getFileName(String url) {
        Integer index = url.lastIndexOf("/");
        return url.substring(index + 1, url.length());
    }

}
