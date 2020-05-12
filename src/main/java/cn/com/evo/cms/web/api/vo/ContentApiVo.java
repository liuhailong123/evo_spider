package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Content;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

public class ContentApiVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 关系id
     */
    private String id;

    /**
     * 内容id
     */
    private String contentId;
    /**
     * 内容名称
     */
    private String name = "";
    /**
     * 分类标签
     */
    private String classify = "";
    /**
     * 演员标签
     */
    private String actor = "";
    /**
     * 导演标签
     */
    private String director = "";
    /**
     * 区域标签
     */
    private String area = "";
    /**
     * 评分
     */
    private String grade = "";
    /**
     * 简介
     */
    private String info = "";
    /**
     * 顺序
     */
    private int sort = 1;
    /**
     * 标题
     */
    private String title = "";
    /**
     * 上映年份
     */
    private String year = "";

    /**
     * 语言
     */
    private String language = "";

    /**
     * 海报List
     */
    private List<PictureApiVo> pictures;

    public ContentApiVo() {
    }

    public ContentApiVo(Content content, CatalogueRelation catalogueRelation, List<PictureApiVo> pictures) {
        this.id = catalogueRelation.getId();
        this.contentId = content.getId();
        if (StringUtils.isNotBlank(content.getClassifyTags())) {
            this.classify = content.getClassifyTags();
        }
        if (StringUtils.isNotBlank(content.getActorTags())) {
            this.actor = content.getActorTags();
        }
        if (StringUtils.isNotBlank(content.getDirectorTags())) {
            this.director = content.getDirectorTags();
        }
        if (StringUtils.isNotBlank(content.getAreaTags())) {
            this.area = content.getAreaTags();
        }
        if (StringUtils.isNotBlank(content.getGrade() + "")) {
            this.grade = content.getGrade();
        }
        if (StringUtils.isNotBlank(content.getInfo())) {
            this.info = content.getInfo();
        }
        if (StringUtils.isNotBlank(content.getName())) {
            this.name = content.getName();
        }
        if (StringUtils.isNotBlank(content.getSort() + "")) {
            this.sort = content.getSort();
        }

        if (StringUtils.isNotBlank(content.getTitle())) {
            this.title = content.getTitle();
        }
        this.pictures = pictures;
        if (StringUtils.isNotBlank(content.getYear())) {
            this.year = content.getYear();
        }
        if (StringUtils.isNotBlank(content.getLanguage())) {
            this.language = content.getLanguage();
        }
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<PictureApiVo> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureApiVo> pictures) {
        this.pictures = pictures;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
