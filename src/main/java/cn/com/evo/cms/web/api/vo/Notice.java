package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.app.Announcement;

import java.io.Serializable;

public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Integer type;
    private String content;
    private String blankUrl;
    private Integer count;

    public Notice() {
    }

    public Notice(Announcement announcement) {
        this.id = announcement.getId();
        this.type = announcement.getType();
        this.content = announcement.getContent();
        this.blankUrl = announcement.getBlankUrl();
        this.count=announcement.getContent().length();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBlankUrl() {
        return blankUrl;
    }

    public void setBlankUrl(String blankUrl) {
        this.blankUrl = blankUrl;
    }
}
