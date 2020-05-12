package cn.com.evo.cms.domain.vo.cms;

public class ContentVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String classifyTags;

    private String actorTags;

    private String directorTags;

    private String areaTags;

    private int classify;

    private String code;

    private int enable;

    private String grade;

    private String info;

    private String name;

    private String nameSpellLong;

    private String nameSpellShort;

    private int sort;

    private String title;

    private String titleSpellLong;

    private String titleSpellShort;

    private String year;

    private String yearTags;

    private Integer sumNum;//总集数

    private String runTime;

    private String previewUrl;

    private String language;
    /**
     * 注入状态
     */
    private Integer synType;

    private Integer isVideoRel;

    private Integer isImageRel;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }

    public String getDirectorTags() {
        return directorTags;
    }

    public void setDirectorTags(String directorTags) {
        this.directorTags = directorTags;
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

    public String getAreaTags() {
        return areaTags;
    }

    public void setAreaTags(String areaTags) {
        this.areaTags = areaTags;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
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

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public Integer getSynType() {
        return synType;
    }

    public void setSynType(Integer synType) {
        this.synType = synType;
    }

    public Integer getIsVideoRel() {
        return isVideoRel;
    }

    public void setIsVideoRel(Integer isVideoRel) {
        this.isVideoRel = isVideoRel;
    }

    public Integer getIsImageRel() {
        return isImageRel;
    }

    public void setIsImageRel(Integer isImageRel) {
        this.isImageRel = isImageRel;
    }
}