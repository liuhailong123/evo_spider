package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.*;


/**
 * The persistent class for the cms_video database table.
 */
@Entity
@Table(name = "cms_source_video")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private int type;

    private int definition;

    private String ext;

    private String size;

    private String time;

    private String resolution;

    private String bitrate;

    @ManyToOne
    @JoinColumn(name = "sourceId")
    private Source source; //资源表

    private String url;

    /**
     * 平台来源;字典配置
     */
    private String platform;

    /**
     * 中兴cdn地址
     */
    private String cdn1Url;

    /**
     * 华为cdn地址
     */
    private String cdn2Url;

    /**
     * 烽火cdn地址
     */
    private String cdn3Url;

    public Video() {
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDefinition() {
        return definition;
    }

    public void setDefinition(int definition) {
        this.definition = definition;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResolution() {
        return this.resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getCdn1Url() {
        return cdn1Url;
    }

    public void setCdn1Url(String cdn1Url) {
        this.cdn1Url = cdn1Url;
    }

    public String getCdn2Url() {
        return cdn2Url;
    }

    public void setCdn2Url(String cdn2Url) {
        this.cdn2Url = cdn2Url;
    }

    public String getCdn3Url() {
        return cdn3Url;
    }

    public void setCdn3Url(String cdn3Url) {
        this.cdn3Url = cdn3Url;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}