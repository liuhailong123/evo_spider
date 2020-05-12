package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.LliveBroadcast;

import java.io.Serializable;


public class LiveApiVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    private String contentId;

    /**
     * 频道号
     */
    private String number;

    /**
     * 频道名称
     */
    private String name;

    /**
     * 频道类型 1-导视 2-推荐 3-广告 4-内容
     */
    private int type;

    /**
     * 直播地址
     */
    private String url;

    public LiveApiVo(LliveBroadcast live, CatalogueRelation catalogueRelation) {
        this.id = catalogueRelation.getId();
        this.contentId = live.getId();
        this.number = live.getNumber();
        this.name = live.getName();
        this.type = live.getType();
        this.url = live.getUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}