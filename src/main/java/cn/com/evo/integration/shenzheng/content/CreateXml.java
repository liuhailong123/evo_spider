package cn.com.evo.integration.shenzheng.content;

import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.utils.BeanToXml;
import cn.com.evo.integration.guangxi.content.xml.model.Metadata;
import cn.com.evo.integration.shenzheng.common.SzContantEnum;
import cn.com.evo.integration.shenzheng.common.chck.Chck;
import cn.com.evo.integration.shenzheng.content.model.IndexADI;
import cn.com.evo.integration.shenzheng.content.model.MediaADI;
import cn.com.evo.integration.shenzheng.content.model.VideoADI;
import com.frameworks.utils.DateUtil;

import javax.xml.bind.JAXBException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rf
 * @date 2019/5/29
 */
public class CreateXml {

    /**
     * 生成主媒资xml字符串
     *
     * @param content
     * @param picture
     * @return
     */
    public String createVideoXml(Content content, List<Picture> picture) {
        VideoADI adi = new VideoADI();
        adi.setId(Chck.chckValue("id", content.getId()));
        adi.setAssets_id(Chck.chckValue("id", content.getId()));
        String cpId = ConstantFactory.map.get(SzContantEnum.cp_id.getKey());
        adi.setAsset_source(Chck.chckValue("cpId", cpId));
        adi.setAssets_id(Chck.chckValue("cpId", cpId));
        adi.setCp_name(ConstantFactory.map.get(SzContantEnum.cp_name.getKey()));
        adi.setAssets_category("电影");
        adi.setSvc_item_id("");
        adi.setName(Chck.chckValue("媒资名称",content.getName()));
        adi.setView_type("0");
        adi.setSreach_key(content.getClassifyTags());
        adi.setDirectornew(formatTags(content.getDirectorTags()));
        adi.setDirector(formatTags(content.getDirectorTags()));
        adi.setPlayer(formatTags(content.getActorTags()));
        adi.setPlayernew(formatTags(content.getActorTags()));
        adi.setAdaptor("");
        adi.setAdaptornew("");
        adi.setTag(content.getClassifyTags());
        adi.setRegion(content.getAreaTags());
        adi.setRelease_time(content.getYearTags());
        adi.setTotalnum(content.getSumNum() == null ? content.getSumNum() + "" : "1");
        adi.setNational("");
        adi.setSmallpic("");
        adi.setBigpic("");
        if (picture.size() > 0) {
            for (Picture picture1 : picture) {
                if (picture1.getType() == 1) {
                    adi.setHorizontal_img(picture1.getCloudPath());
                } else if (picture1.getType() == 2) {
                    adi.setVerticality_img(picture1.getCloudPath());
                }
            }
        } else {
            throw new RuntimeException("深圳天威主媒资注入，生成xml过程海报为空");
        }
        adi.setSquare_img("");
        adi.setIntro(content.getInfo());
        adi.setPlay_role("");
        adi.setSubordinate_name(content.getName());
        adi.setEnglish_name("");
        adi.setLanguage(content.getLanguage());
        adi.setCaption_language("");
        adi.setInitials(content.getNameSpellShort());
        adi.setCopyright_range("");
        adi.setTotal_clicks("");
        adi.setMonth_clicks("");
        adi.setWeek_clicks("");
        adi.setDay_clicks("");
        adi.setEtag("");
        adi.setView_len(content.getRunTime());
        adi.setOriginal_id(content.getId());
        adi.setCp_video_id(content.getId());
        adi.setOffline_play_support("0");
        adi.setWatch_focus(content.getInfo());
        adi.setCategory_type("");
        adi.setThird_asset_id("");
        adi.setThird_expand("");
        adi.setSeries_id("");
        adi.setAssets_definition("");
        adi.setIs_updated("0");
        adi.setAssets_format("");
        adi.setAssets_dimension("");
        adi.setCopyright_area("");
        adi.setIndex_info("0");
        adi.setOnline_identify("");
        adi.setCite_id("");
        adi.setCite_cp_id("");
        adi.setPoint("");
        adi.setOrg_id("");
        adi.setCopyright("");
        adi.setIs_needed_auth("1");
        adi.setUs_level("");
        adi.setUk_level("");
        adi.setCopyright_begin_date(DateUtil.thisYearThreeEnd() + " 12:00:00");
        adi.setNns_collect_count("");
        adi.setUser_score_count("");
        adi.setUser_score_arg("");
        adi.setAssets_charge("");
        adi.setPreview_type("0");
        adi.setFusion_definition("");
        adi.setDouban_score("");
        adi.setTag("0");
        try {
            String str = BeanToXml.beanToXml(adi, false);
            return str;
        } catch (JAXBException e) {
            throw new RuntimeException("生成主媒资文件异常：" + e.getMessage(), e);
        }
    }

    /**
     * 创建片源xml
     *
     * @param content
     * @param videos
     * @return
     */
    public String createMediaXml(Content content, List<Video> videos) {
        try {
            List<MediaADI> adis = new ArrayList<>();
            if (videos.size() > 0) {
                for (Video video : videos) {
                    MediaADI adi = new MediaADI();
                    adi.setFile_id(video.getId());
                    adi.setId(video.getId());
                    adi.setAsset_source(ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
                    adi.setCp_id(ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
                    adi.setCp_name(ConstantFactory.map.get(SzContantEnum.cp_name.getKey()));
                    adi.setFile_type(video.getExt());
                    //物理文件地址
                    adi.setFile_path(video.getUrl());
                    adi.setFile_definition(formatDefinition(video.getDefinition()));
                    adi.setFile_resolution(video.getResolution());
                    adi.setFile_size(video.getSize());
                    adi.setFile_bit_rate("");
                    adi.setFile_3d("");
                    adi.setFile_3d_type("");
                    adi.setOriginal_id(video.getId());
                    adi.setCp_video_id(video.getId());
                    adi.setFile_coding("");
                    adi.setDrm_flag("0");
                    adi.setDrm_encrypt_solution("");
                    adi.setMedia_type("1");
                    adi.setOriginal_live_id("");
                    adi.setStart_time("");
                    adi.setMedia_service_type("msp");
                    adi.setFile_core_id(content.getId());
                    adi.setClip_cp_id("");
                    adi.setAsset_cp_id("");
                    adi.setTags(content.getClassifyTags());
                    adi.setThird_file_id(content.getInfo());
                    adi.setEx_url("");
                    adi.setFile_time_len(video.getTime());
                    adi.setCdn_ext_source_id("");
                    adi.setFile_etag("");
                    adi.setIsintact("1");
                    adi.setIs_source_media("1");
                    adi.setFile_desc("");
                    adi.setMedia_type("1");
                    adis.add(adi);
                }
            }
            return BeanToXml.beanToXml(adis, false);
        } catch (JAXBException e) {
            throw new RuntimeException("深圳天威注入流程，创建片源xml异常：" + e.getMessage(), e);
        }
    }

    /**
     * 生成打点信息xml
     *
     * @param metadata
     * @return
     */
    public String createMetadataXml(Metadata metadata) {
        try {
            return BeanToXml.beanToXml(metadata, false);
        } catch (JAXBException e) {
            throw new RuntimeException("深圳天威注入流程异常，生成打点信息xml失败");
        }
    }

    /**
     * 生成分集xml
     *
     * @param content
     * @param contents
     * @param pictures
     * @return
     */
    public String createIndexXml(Content content, List<Content> contents, List<Picture> pictures) {
        try {
            List<IndexADI> adis = new ArrayList<>();
            String vImg = "";
            String hImg = "";
            for (Picture picture : pictures) {
                if (picture.getType() == 1) {
                    vImg = picture.getCloudPath();
                } else if (picture.getType() == 2) {
                    hImg = picture.getCloudPath();
                }
            }

            for (Content child : contents) {
                IndexADI adi = new IndexADI();
                adi.setClip_id(child.getId());
                adi.setId(child.getId());
                adi.setAsset_source(ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
                adi.setCp_id(ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
                adi.setCp_name(ConstantFactory.map.get(SzContantEnum.cp_name.getKey()));
                adi.setCp_name(child.getName());
                adi.setWatch_focus(child.getInfo());
                adi.setIndex(child.getSort() + "");
                adi.setTime_len(child.getRunTime());
                adi.setSummary(child.getInfo());
                adi.setPic("");
                adi.setDirector(child.getDirectorTags());
                adi.setDirectornew(child.getDirectorTags());
                adi.setPlayer(child.getActorTags());
                adi.setPlayernew(child.getActorTags());
                adi.setTotal_clicks("");
                adi.setMonth_clicks("");
                adi.setWeek_clicks("");
                adi.setDay_clicks("");
                adi.setUpdate_time("");
                adi.setRelease_time(child.getYearTags());
                adi.setOriginal_id("");
                adi.setCp_video_id(child.getId());
                adi.setIsintact("1");
                adi.setThird_clip_id(child.getId());
                adi.setSeries_id("");
                adi.setIs_updated("0");
                adi.setDrm_flag("0");
                adi.setAsset_cp_id(content.getId());
                adi.setSale_info("");
                adi.setImage_h(hImg);
                adi.setImage_v(vImg);
                adi.setImage_s("");
                adis.add(adi);
            }
            return BeanToXml.beanToXml(adis, false);
        } catch (JAXBException e) {
            throw new RuntimeException("深圳天威创建分集信息xml异常" + e.getMessage(), e);
        }
    }

    /**
     * 转换视频清晰度类型
     *
     * @param def
     * @return
     */
    private String formatDefinition(Integer def) {
        if (def == null) {
            throw new RuntimeException("深圳天威媒资注入参数转化异常:" + def);
        }
        switch (def) {
            case 1:
                return "4k";
            case 2:
                return "1080P";
            case 3:
                return "fhd";
            case 4:
                return "hd";
            case 5:
                return "sd";
            case 6:
                return "sd";
            default:
                return "hd";
        }
    }

    private String formatTags(String tag) {
        if (!tag.isEmpty()) {
            String[] split = tag.split(",");
            String newTag = "";
            for (int i = 0; i < split.length; i++) {
                if (i == split.length - 1) {
                    newTag += split[i];
                } else {
                    newTag += split[i] + "|";
                }
            }
            return newTag;
        } else {
            return "";
        }
    }
}