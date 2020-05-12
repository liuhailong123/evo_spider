package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the cms_content database table.
 */
@Entity
@Table(name = "cms_content")
@NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c")
public class Content extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String pId;

    private String classifyTags;

    private String actorTags;

    private String directorTags;

    private String areaTags;

    private Integer classify;

    private String code;

    private Integer enable;

    private String grade;

    private String info;

    private String name;

    private String nameSpellLong;

    private String nameSpellShort;

    private Integer sort;

    private String title;

    private String titleSpellLong;

    private String titleSpellShort;

    private String year;

    private String yearTags;

    private Integer sumNum;//总集数

    private String runTime;//时长

    private String language;//语言

    /**
     * 注入状态;0-未注入;1-注入中;2-已注入;3-注入失败
     */
    private Integer synType;

    @Transient
    private String relId;

    public Content() {
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getClassifyTags() {
        return classifyTags;
    }

    public void setClassifyTags(String classifyTags) {
        this.classifyTags = classifyTags;
    }

    public String getActorTags() {
        return actorTags;
    }

    public void setActorTags(String actorTags) {
        this.actorTags = actorTags;
    }

    public String getDirectorTags() {
        return directorTags;
    }

    public void setDirectorTags(String directorTags) {
        this.directorTags = directorTags;
    }

    public String getAreaTags() {
        return areaTags;
    }

    public void setAreaTags(String areaTags) {
        this.areaTags = areaTags;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
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

    public String getNameSpellLong() {
        return nameSpellLong;
    }

    public void setNameSpellLong(String nameSpellLong) {
        this.nameSpellLong = nameSpellLong;
    }

    public String getNameSpellShort() {
        return nameSpellShort;
    }

    public void setNameSpellShort(String nameSpellShort) {
        this.nameSpellShort = nameSpellShort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSpellLong() {
        return titleSpellLong;
    }

    public void setTitleSpellLong(String titleSpellLong) {
        this.titleSpellLong = titleSpellLong;
    }

    public String getTitleSpellShort() {
        return titleSpellShort;
    }

    public void setTitleSpellShort(String titleSpellShort) {
        this.titleSpellShort = titleSpellShort;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYearTags() {
        return yearTags;
    }

    public void setYearTags(String yearTags) {
        this.yearTags = yearTags;
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public Integer getSynType() {
        return synType;
    }

    public void setSynType(Integer synType) {
        this.synType = synType;
    }

}