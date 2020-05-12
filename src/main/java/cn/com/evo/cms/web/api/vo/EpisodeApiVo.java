package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Content;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;


public class EpisodeApiVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String contentId;

    private String name = "";

    private String classify = "";

    private String actor = "";

    private String director = "";

    private String area = "";

    private String grade = "";

    private String info = "";

    private Integer sort;

    private String title = "";

    private String year = "";

    private String language = "";

    private Integer total;

    private Integer count;

    private Integer freeNum;

    private boolean isLimitFree;

    private List<PictureApiVo> pictures;

    private List<EpisodeChildApiVo> childs = Lists.newArrayList();

    public EpisodeApiVo(Content auxiliaryEpisode, CatalogueRelation catalogueRelation, List<PictureApiVo> pictures, Long total, boolean isLimitFree) {
        this.id = catalogueRelation.getId();
        this.contentId = auxiliaryEpisode.getId();
        if (StringUtils.isNotBlank(auxiliaryEpisode.getName())) {
            this.name = auxiliaryEpisode.getName();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getClassifyTags())) {
            this.classify = auxiliaryEpisode.getClassifyTags();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getActorTags())) {
            this.actor = auxiliaryEpisode.getActorTags();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getDirectorTags())) {
            this.director = auxiliaryEpisode.getDirectorTags();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getAreaTags())) {
            this.area = auxiliaryEpisode.getAreaTags();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getGrade())) {
            this.grade = auxiliaryEpisode.getGrade();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getInfo())) {
            this.info = auxiliaryEpisode.getInfo();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getTitle())) {
            this.title = auxiliaryEpisode.getTitle();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getYear())) {
            this.year = auxiliaryEpisode.getYear();
        }
        if (StringUtils.isNotBlank(auxiliaryEpisode.getLanguage())) {
            this.language = auxiliaryEpisode.getLanguage();
        }
        this.sort = auxiliaryEpisode.getSort();
        this.count = auxiliaryEpisode.getSumNum();
        this.total = Integer.valueOf(total + "");
        this.pictures = pictures;
        this.freeNum = catalogueRelation.getFreeNum() == null ? 0 : catalogueRelation.getFreeNum();
        this.isLimitFree = isLimitFree;
    }

    private List<EpisodeChildApiVo> getEpisodeChilds(List<ContentApiVo> contentApiVos) {
        List<EpisodeChildApiVo> vos = Lists.newArrayList();
        for (ContentApiVo content : contentApiVos) {
            EpisodeChildApiVo vo = new EpisodeChildApiVo(content);
            vos.add(vo);
        }
        return vos;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<EpisodeChildApiVo> getChilds() {
        return childs;
    }

    public void setChilds(List<EpisodeChildApiVo> childs) {
        this.childs = childs;
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

    public boolean isLimitFree() {
        return isLimitFree;
    }

    public void setLimitFree(boolean limitFree) {
        isLimitFree = limitFree;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(Integer freeNum) {
        this.freeNum = freeNum;
    }
}
