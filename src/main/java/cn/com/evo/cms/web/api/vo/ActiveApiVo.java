package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.Active;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ActiveApiVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String contentId;
    private String name;
    private String imgUrl = "";
    private String author;
    private String summary;
    private String sponsor;
    private String startTime;
    private String endTime;
    /**
     * 活动详情
     */
    private String info = "";

    public ActiveApiVo(Active active, CatalogueRelation catalogueRelation) {
        this.contentId = active.getId();
        this.id = catalogueRelation.getId();
        this.name = active.getName();
        this.author = active.getAuthor();
        this.summary = active.getSynopsis();
        this.sponsor = active.getSponsor();
        this.startTime = active.getValidTime();
        this.endTime = active.getUnValidTime();
        String[] infos = active.getOtherInfo().split("xxxx");
        String info = infos[0] + active.getInfo() + infos[1];
        try {
            this.info = URLEncoder.encode(info, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("活动详情html转utf-8失败");
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
