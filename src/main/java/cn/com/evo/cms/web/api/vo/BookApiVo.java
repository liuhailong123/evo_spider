package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;

import java.io.Serializable;
import java.util.List;

public class BookApiVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String contentId;

    private String number;//书号

    private String ageTag;//适合年龄段

    private String author;//作者

    private String clcClassify;//中图法分类

    private String engineer;//编辑

    private String info;//简介

    private String name;//书名

    private String publishDate;//出版日期

    private String smallClassify;//细分类别

    private String suitGroup;//适用群体

    private String supplier;//供应商

    private String tags; //图书标签

    private String qrCode;//图书二维码

    /**
     * 图书二维码url
     */
    private String qrCodeUrl;
    /**
     * 海报List
     */
    private List<PictureApiVo> pictures;

    public BookApiVo(BookInfo bookInfo, CatalogueRelation catalogueRelation, List<PictureApiVo> pictures) {
        this.contentId = bookInfo.getId();
        this.id = catalogueRelation.getId();
        this.number = bookInfo.getNumber();
        this.ageTag = bookInfo.getAgeTag();
        this.author = bookInfo.getAuthor();
        this.clcClassify = bookInfo.getClcClassify();
        this.engineer = bookInfo.getEngineer();
        this.info = bookInfo.getInfo();
        this.name = bookInfo.getName();
        this.publishDate = bookInfo.getPublishDate();
        this.smallClassify = bookInfo.getSmallClassify();
        this.suitGroup = bookInfo.getSuitGroup();
        this.supplier = bookInfo.getSupplier();
        this.tags = bookInfo.getTags();
        this.pictures = pictures;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public List<PictureApiVo> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureApiVo> pictures) {
        this.pictures = pictures;
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

    public String getAgeTag() {
        return ageTag;
    }

    public void setAgeTag(String ageTag) {
        this.ageTag = ageTag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClcClassify() {
        return clcClassify;
    }

    public void setClcClassify(String clcClassify) {
        this.clcClassify = clcClassify;
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSmallClassify() {
        return smallClassify;
    }

    public void setSmallClassify(String smallClassify) {
        this.smallClassify = smallClassify;
    }

    public String getSuitGroup() {
        return suitGroup;
    }

    public void setSuitGroup(String suitGroup) {
        this.suitGroup = suitGroup;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
