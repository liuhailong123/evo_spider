package cn.com.evo.cms.domain.vo.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SourceVideoVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String ext;

    private String size;

    private String name;//资源名称

    private String time;

    private String resolution;

    private String sourceId;

    private String bitrate;

    private int type;//类型 1:2D【平面】 2:3D【立体】 3:VR【全景】

    private String typeName;//类型名称

    private int definition;//	清晰度 1:4K 2:蓝光 3:超清 4:高清 5:标清 6:手机

    private String definitionName;//清晰度名称

    private String url;

    private SourceVo source;

    private String sourceRelId;
    /**
     * 平台来源;字典配置
     */
    private String platform;

    private String platformName;
}