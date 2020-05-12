package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 目录内容中间表
 * The persistent class for the cms_catalogue_relation database table.
 */
@Entity
@Table(name = "cms_catalogue_relation")
@NamedQuery(name = "CatalogueRelation.findAll", query = "SELECT c FROM CatalogueRelation c")
public class CatalogueRelation extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    private String aId;

    /**
     * 内容id
     */
    private String bId;

    /**
     * 1 角色-站点  2 栏目-内容
     */
    private Integer type;

    /**
     * 是否发布；1-是；0-否
     */
    private Integer publish;

    /**
     * 内容类型 ContentTypeEnum
     */
    private Integer contentType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 业务类型， 1-普通；2-推荐
     */
    private Integer businessType;

    /**
     * 是否热门， 1-是；0-否
     */
    private Integer isHot;

    /**
     * 免费集数
     */
    private Integer freeNum;

    /**
     * 同步状态（0-未同步；1-已同步）
     * 暂时 使用与龙江网络和四川移动的统一搜索数据*注入
     */
    @Column(columnDefinition = "int(10) default 0 comment '同步状态'")
    private Integer synStatus;

    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CatalogueRelation() {
    }

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public Integer getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(Integer freeNum) {
        this.freeNum = freeNum;
    }

    public Integer getSynStatus() {
        return synStatus;
    }

    public void setSynStatus(Integer synStatus) {
        this.synStatus = synStatus;
    }
}