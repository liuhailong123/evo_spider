package cn.com.evo.cms.domain.vo.cms;

import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.entity.spider.SpiderContent;

import java.io.Serializable;
import java.util.Date;

public class PublishContentVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Date createDate;
    private Date modifyDate;
    private String name;
    private Integer type;// 1内容 9剧集
    private Integer enable;
    private Integer sort;
    private String previewUrl;
    private Integer businessType;
    private String contentId;
    private Integer isRecommend;
    private Integer columnId;
    private String columnName;

    public PublishContentVo(CatalogueRelation catalogueRelation, Content content, String previewUrl) {
        this.id = catalogueRelation.getId();
        this.contentId = content.getId();
        this.name = content.getName();
        this.type = catalogueRelation.getContentType();
        this.enable = catalogueRelation.getPublish();
        this.createDate = catalogueRelation.getCreateDate();
        this.modifyDate = catalogueRelation.getModifyDate();
        this.sort = catalogueRelation.getSort();
        this.businessType = catalogueRelation.getBusinessType();
        this.previewUrl = previewUrl;
    }

    public PublishContentVo(CatalogueRelation catalogueRelation, Content content) {
        this.id = catalogueRelation.getId();
        this.contentId = content.getId();
        this.name = content.getName();
        this.type = catalogueRelation.getContentType();
        this.enable = catalogueRelation.getPublish();
        this.createDate = catalogueRelation.getCreateDate();
        this.modifyDate = catalogueRelation.getModifyDate();
        this.sort = catalogueRelation.getSort();
        this.businessType = catalogueRelation.getBusinessType();
    }

    public PublishContentVo(CatalogueRelation catalogueRelation, Active active) {
        this.id = catalogueRelation.getId();
        this.contentId = active.getId();
        this.name = active.getName();
        this.type = catalogueRelation.getContentType();
        this.enable = catalogueRelation.getPublish();
        this.createDate = catalogueRelation.getCreateDate();
        this.modifyDate = catalogueRelation.getModifyDate();
        this.sort = catalogueRelation.getSort();
        this.businessType = catalogueRelation.getBusinessType();
    }

    public PublishContentVo(CatalogueRelation catalogueRelation, LliveBroadcast lliveBroadcast) {
        this.id = catalogueRelation.getId();
        this.contentId = lliveBroadcast.getId();
        this.name = lliveBroadcast.getName();
        this.type = catalogueRelation.getContentType();
        this.enable = catalogueRelation.getPublish();
        this.createDate = catalogueRelation.getCreateDate();
        this.modifyDate = catalogueRelation.getModifyDate();
        this.sort = catalogueRelation.getSort();
        this.businessType = catalogueRelation.getBusinessType();
    }

    public PublishContentVo(CatalogueRelation catalogueRelation, BookInfo bookInfo) {
        this.id = catalogueRelation.getId();
        this.contentId = bookInfo.getId();
        this.name = bookInfo.getName();
        this.type = catalogueRelation.getContentType();
        this.enable = catalogueRelation.getPublish();
        this.createDate = catalogueRelation.getCreateDate();
        this.modifyDate = catalogueRelation.getModifyDate();
        this.sort = catalogueRelation.getSort();
        this.businessType = catalogueRelation.getBusinessType();
    }

    public PublishContentVo(CatalogueRelation catalogueRelation, SpiderContent bookInfo) {
        this.id = catalogueRelation.getId();
        this.contentId = bookInfo.getId();
        this.name = bookInfo.getTitle();
        this.type = catalogueRelation.getContentType();
        this.enable = catalogueRelation.getPublish();
        this.createDate = catalogueRelation.getCreateDate();
        this.modifyDate = catalogueRelation.getModifyDate();
        this.sort = catalogueRelation.getSort();
        this.businessType = catalogueRelation.getBusinessType();
    }

    public PublishContentVo(CatalogueRelation catalogueRelation, Column column){
        this.id = catalogueRelation.getId();
        this.contentId = column.getId();
        this.name = column.getName();
        this.type = catalogueRelation.getContentType();
        this.enable = catalogueRelation.getPublish();
        this.createDate = catalogueRelation.getCreateDate();
        this.modifyDate = catalogueRelation.getModifyDate();
        this.sort = catalogueRelation.getSort();
        this.businessType = catalogueRelation.getBusinessType();
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
