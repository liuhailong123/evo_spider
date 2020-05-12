package cn.com.evo.integration.xjdx.content;

import cn.com.evo.integration.common.utils.BeanToXml;
import cn.com.evo.integration.xjdx.content.model.*;
import cn.com.evo.integration.xjdx.content.model.Object;
import com.frameworks.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author rf
 * @date 2019/8/13
 */
public class CreateXml {

    public static String createSeries(Program program) {
        ADI adi = new ADI();
        List<Object> objects = new ArrayList<Object>();
        List<Mapping> mappings = new ArrayList<Mapping>();
        Object seriesTitle = createSeriesTitle(program);
        objects.add(seriesTitle);
        Object pic = createPic(program);
        objects.add(pic);
        if(program.getpId() != null && program.getpId().equals(program.getId())){
            //进行关系绑定
            Mapping mappingHpicAndProgram = new Mapping();
            mappingHpicAndProgram.setAction(program.getAction());
            mappingHpicAndProgram.setParentType("Series");
            mappingHpicAndProgram.setElementType("Program");
            mappingHpicAndProgram.setParentID(program.getPicCode());
            mappingHpicAndProgram.setElementID(seriesTitle.getID());
            mappingHpicAndProgram.setParentCode(program.getPicCode());
            mappingHpicAndProgram.setElementCode(seriesTitle.getID());
            MappingPropertys hpicAndProgram = new MappingPropertys();
            hpicAndProgram.setType("2");
            hpicAndProgram.setSequence("0");
            List<Property> hpicAndPrograms = Property.createPropertysForObj(hpicAndProgram);
            mappingHpicAndProgram.setProperties(hpicAndPrograms);
            mappings.add(mappingHpicAndProgram);
        } else {
            Object video = createVideo(program);
            objects.add(video);
            //进行关系绑定 program-movie
            Mapping mappingProgramAndMovie = new Mapping();
            mappingProgramAndMovie.setAction(program.getAction());
            mappingProgramAndMovie.setParentType("Program");
            mappingProgramAndMovie.setElementType("Movie");
            mappingProgramAndMovie.setParentID(program.getCode());
            mappingProgramAndMovie.setElementID(video.getCode());
            mappingProgramAndMovie.setParentCode(program.getCode());
            mappingProgramAndMovie.setElementCode(video.getCode());
            mappings.add(mappingProgramAndMovie);
            //series-program
            Mapping mappingSeriesAndProgram = new Mapping();
            mappingSeriesAndProgram.setAction(program.getAction());
            mappingSeriesAndProgram.setParentType("Series");
            mappingSeriesAndProgram.setElementType("Program");
            mappingSeriesAndProgram.setParentID(program.getpId());
            mappingSeriesAndProgram.setElementID(program.getCode());
            mappingSeriesAndProgram.setParentCode(program.getpId());
            mappingSeriesAndProgram.setElementCode(program.getCode());
            MappingPropertys property = new MappingPropertys();
            property.setSequence(program.getSort());
            mappingSeriesAndProgram.setProperties(Property.createPropertysForObj(property));
            mappings.add(mappingSeriesAndProgram);
        }
        //添加adi并生成xml字符串
        adi.setObjects(objects);
        adi.setMappings(mappings);
        try {
            String str = BeanToXml.beanToXml(adi, false);
            return str;
        } catch (JAXBException e) {
            throw new RuntimeException("重庆sp注入流程生成剧集头xml发生异常" + e.getMessage(), e);
        }
    }
    public static Object createVideo(Program pro){
        Movie mo = pro.getMovie();
        //创建片源obj
        Object movie = new Object();
        movie.setElementType("Movie");
        movie.setID(mo.getMovieCode());
        movie.setAction(pro.getAction());
        movie.setCode(mo.getMovieCode());
        ObjectPropertys moviePropertys = new ObjectPropertys();
        moviePropertys.setType("1");
        moviePropertys.setFileURL(mo.getUrl());
        moviePropertys.setName(pro.getName());
        moviePropertys.setAudioFormat("0");
        moviePropertys.setOCSURL("0");
        moviePropertys.setDuration("");
        moviePropertys.setFileSize("0");
        moviePropertys.setSourceDRMType("0");
        moviePropertys.setDestDRMType("0");
        moviePropertys.setAudioType("0");
        moviePropertys.setScreenFormat("0");
        moviePropertys.setClosedCaptioning("0");
        moviePropertys.setResolution("9");
        moviePropertys.setSystemLayer("1");
        List<Property> moviePropertysForObj = Property.createPropertysForObj(moviePropertys);
        movie.setProperties(moviePropertysForObj);
        return movie;

    }

    public static Object createPic(Program pro){
        //设置横图定义
        Object hpic = new Object();
        hpic.setElementType("Picture");
        hpic.setID(pro.getPicCode());
        hpic.setAction(pro.getAction());
        hpic.setCode(pro.getPicCode());
        ObjectPropertys hpicPropertys = new ObjectPropertys();
        hpicPropertys.setFileURL(pro.getPicUrl());
        hpicPropertys.setDescription("h");
        List<Property> hpicPropertysForObj = Property.createPropertysForObj(hpicPropertys);
        hpic.setProperties(hpicPropertysForObj);
        return hpic;
    }

    /**
     * 剧头
     * @param pro
     * @return
     */
    public static Object createSeriesTitle(Program pro){
        //添加内容program
        Object object = new Object();
        if(pro.getpId() == null){
            object.setElementType("Series");
        }else {
            object.setElementType("Program");
        }
        object.setID(pro.getId());
        object.setAction(pro.getAction());
        object.setCode(pro.getId());
        ObjectPropertys programPropertys = new ObjectPropertys();
        if(pro.getpId() != null){
            programPropertys.setSeriesFlag("1");
        }
        programPropertys.setName(pro.getName());
        programPropertys.setOriginalName(pro.getOriginalName());
        programPropertys.setSortName(pro.getSearchName());
        programPropertys.setSearchName(pro.getSearchName());
        programPropertys.setGenre("Genre");
        programPropertys.setActorDisplay(pro.getActor());
        programPropertys.setWriterDisplay(pro.getDirector());
        programPropertys.setOriginalCountry(pro.getAddr());
        programPropertys.setLanguage(pro.getLanguage());
        String Year = pro.getShowYear().substring(0,4);
        programPropertys.setReleaseYear(Year);
        programPropertys.setOrgAirDate(pro.getShowYear());
        programPropertys.setLicensingWindowStart(DateUtil.format(new Date(), "YYYYMMDDHHMMSS"));
        programPropertys.setLicensingWindowEnd(DateUtil.format(new Date(), "YYYYMMDDHHMMSS"));
        programPropertys.setDisplayAsNew("");
        programPropertys.setDisplayAsLastChance("");
        programPropertys.setMacrovision("0");
        programPropertys.setDescription(pro.getInfo());
        programPropertys.setType(pro.getContentType());
        programPropertys.setPriceTaxIn("");
        programPropertys.setStatus("1");
        programPropertys.setSourceType("1");
        programPropertys.setStorageType("0");
        programPropertys.setDefinitionFlag("3");
        List<Property> propertysForObj = Property.createPropertysForObj(programPropertys);
        object.setProperties(propertysForObj);
        return object;
    }

    public static String createMovie(Program pro) {
        Movie mo = pro.getMovie();
        ADI adi = new ADI();
        List<Object> objects = new ArrayList<Object>();
        List<Mapping> mappings = new ArrayList<Mapping>();
        //添加内容program
        Object program = new Object();
        program.setElementType("Program");
        program.setID(pro.getId());
        program.setAction(pro.getAction());
        program.setCode(pro.getId());
        ObjectPropertys programPropertys = new ObjectPropertys();
        programPropertys.setType(pro.getContentType());
        programPropertys.setName(pro.getName());
        programPropertys.setOriginalName(pro.getOriginalName());
        programPropertys.setSortName(pro.getSearchName());
        programPropertys.setSearchName(pro.getOriginalName());
        programPropertys.setGenre("Genre");
        programPropertys.setActorDisplay("");
        programPropertys.setWriterDisplay(pro.getActor());
        programPropertys.setOriginalCountry(pro.getAddr());
        programPropertys.setLanguage(pro.getLanguage());
        String Year = pro.getShowYear().substring(0,4);
        programPropertys.setReleaseYear(Year);
        programPropertys.setOrgAirDate(pro.getShowYear());
        programPropertys.setLicensingWindowStart("");
        programPropertys.setLicensingWindowEnd("");
        programPropertys.setDisplayAsNew("");
        programPropertys.setDisplayAsLastChance("");
        programPropertys.setMacrovision("0");
        programPropertys.setDescription(pro.getInfo());
        programPropertys.setPriceTaxIn("");
        programPropertys.setStatus("1");
        programPropertys.setSourceType("1");
        programPropertys.setStorageType("0");
        programPropertys.setDefinitionFlag("3");
        List<Property> propertysForObj = Property.createPropertysForObj(programPropertys);
        program.setProperties(propertysForObj);
        objects.add(program);
        //创建片源obj
        Object movie = new Object();
        movie.setElementType("Movie");
        movie.setID(mo.getMovieCode());
        movie.setAction(pro.getAction());
        movie.setCode(mo.getMovieCode());
        ObjectPropertys moviePropertys = new ObjectPropertys();
        moviePropertys.setType("1");
        moviePropertys.setFileURL(mo.getUrl());
        moviePropertys.setName(pro.getName());
        moviePropertys.setAudioFormat("0");
        moviePropertys.setOCSURL("0");
        moviePropertys.setDuration("");
        moviePropertys.setFileSize("0");
        moviePropertys.setSourceDRMType("0");
        moviePropertys.setDestDRMType("0");
        moviePropertys.setAudioType("0");
        moviePropertys.setScreenFormat("0");
        moviePropertys.setClosedCaptioning("0");
        moviePropertys.setResolution("9");
        moviePropertys.setSystemLayer("1");
        List<Property> moviePropertysForObj = Property.createPropertysForObj(moviePropertys);
        movie.setProperties(moviePropertysForObj);
        objects.add(movie);
        //进行关系绑定
        Mapping mappingProgramAndMovie = new Mapping();
        mappingProgramAndMovie.setAction(pro.getAction());
        mappingProgramAndMovie.setParentType("Program");
        mappingProgramAndMovie.setElementType("Movie");
        mappingProgramAndMovie.setParentID(program.getCode());
        mappingProgramAndMovie.setElementID(movie.getCode());
        mappingProgramAndMovie.setParentCode(program.getCode());
        mappingProgramAndMovie.setElementCode(movie.getCode());
//        MappingPropertys programAndMovie = new MappingPropertys();
//      注释内容programAndMovie关系为非必填项，待联调确定是否传入
        mappings.add(mappingProgramAndMovie);


        //设置横图定义
        Object hpic = new Object();
        hpic.setElementType("Picture");
        hpic.setID(pro.getPicCode());
        hpic.setAction(pro.getAction());
        hpic.setCode(pro.getPicCode());
        ObjectPropertys hpicPropertys = new ObjectPropertys();
        hpicPropertys.setFileURL(pro.getPicUrl());
        hpicPropertys.setDescription("h");
        List<Property> hpicPropertysForObj = Property.createPropertysForObj(hpicPropertys);
        hpic.setProperties(hpicPropertysForObj);
        objects.add(hpic);
        //进行关系绑定
        Mapping mappingHpicAndProgram = new Mapping();
        mappingHpicAndProgram.setAction(pro.getAction());
        mappingHpicAndProgram.setParentType("Picture");
        mappingHpicAndProgram.setElementType("Program");
        mappingHpicAndProgram.setParentID(pro.getPicCode());
        mappingHpicAndProgram.setElementID(pro.getId());
        mappingHpicAndProgram.setParentCode(pro.getPicCode());
        mappingHpicAndProgram.setElementCode(pro.getId());
        MappingPropertys hpicAndProgram = new MappingPropertys();
        hpicAndProgram.setType("2");
        hpicAndProgram.setSequence("0");
        List<Property> hpicAndPrograms = Property.createPropertysForObj(hpicAndProgram);
        mappingHpicAndProgram.setProperties(hpicAndPrograms);
        mappings.add(mappingHpicAndProgram);

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
     * 获取随机数(9位)
     *
     * @return
     */
    public static String getRandom() {
        Random r = new Random();
        long num = Math.abs(r.nextLong() % 999999999L);
        String s = String.valueOf(num);
        for (int i = 0; i < 10 - s.length(); i++) {
            s = "qdd" + s;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getRandom());
        System.out.println(getRandom());
        System.out.println(getRandom());
        System.out.println(getRandom());
        System.out.println(getRandom());
        System.out.println(getRandom());
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
