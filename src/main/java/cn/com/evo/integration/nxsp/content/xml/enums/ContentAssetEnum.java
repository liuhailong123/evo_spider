package cn.com.evo.integration.nxsp.content.xml.enums;

import cn.com.evo.integration.scyd.content.xml.enums.ElementTypeEnum;

/**
 * @author rf
 * @date 2019/5/15
 */
public enum  ContentAssetEnum {
    /**
     * 剧集对象
     */
    Image("图片", "Image"),
    /**
     * 节目对象
     */
    Video("视频", "Video"),
    /**
     * 节目对象
     */
    PhysicalChannel("物理频道实体内容", "PhysicalChannel"),
    /**
     * 节目对象
     */
    AssetProgram("节目单实体内容", "AssetProgram"),
    /**
     * 媒体对象
     */
    Audio("音频", "Audio");

    ContentAssetEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private String value;


    public static ContentAssetEnum val(Integer name) {
        //values()方法返回enum实例的数组
        for (ContentAssetEnum s : values()) {
            if (name.equals(s.getName())) {
                return s;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
