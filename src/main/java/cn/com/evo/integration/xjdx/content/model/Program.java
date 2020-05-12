package cn.com.evo.integration.xjdx.content.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/8/1
 */
public class Program {
    private String elementType;

    private String Id;

    private String pId;

    private String action;

    private String code;

    private String name;

    private String originalName;

    private String searchName;

    private String supplier;

    private String isCharge;

    private String contentType;

    private String showYear;

    private String director;

    private String addr;

    private String actor;

    private String language;

    private String imgName;

    private String sort;

    private String info;

    private String definitionFlag;

    private String picCode;

    private String picUrl;

    private Movie movie;


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Program() {
    }

    public Program(String elementType, String id, String action, String code, String name, String originalName, String searchName, String supplier, String isCharge, String contentType, String showYear, String director, String addr, String actor, String language, String imgName, String sort, String info, String definitionFlag, String picCode, String picUrl) {
        this.elementType = elementType;
        Id = id;
        this.action = action;
        this.code = code;
        this.name = name;
        this.originalName = originalName;
        this.searchName = searchName;
        this.supplier = supplier;
        this.isCharge = isCharge;
        this.contentType = contentType;
        this.showYear = showYear;
        this.director = director;
        this.addr = addr;
        this.actor = actor;
        this.language = language;
        this.imgName = imgName;
        this.sort = sort;
        this.info = info;
        this.definitionFlag = definitionFlag;
        this.picCode = picCode;
        this.picUrl = picUrl;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(String isCharge) {
        this.isCharge = isCharge;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getShowYear() {
        return showYear;
    }

    public void setShowYear(String showYear) {
        this.showYear = showYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDefinitionFlag() {
        return definitionFlag;
    }

    public void setDefinitionFlag(String definitionFlag) {
        this.definitionFlag = definitionFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("elementType", elementType)
                .append("Id", Id)
                .append("pId", pId)
                .append("action", action)
                .append("code", code)
                .append("name", name)
                .append("originalName", originalName)
                .append("searchName", searchName)
                .append("supplier", supplier)
                .append("isCharge", isCharge)
                .append("contentType", contentType)
                .append("showYear", showYear)
                .append("director", director)
                .append("addr", addr)
                .append("actor", actor)
                .append("language", language)
                .append("imgName", imgName)
                .append("sort", sort)
                .append("info", info)
                .append("definitionFlag", definitionFlag)
                .append("picCode", picCode)
                .append("picUrl", picUrl)
                .append("movie", movie)
                .toString();
    }
}
