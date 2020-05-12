package cn.com.evo.cms.domain.vo.cms;

public class CatalogueRelationVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String aId;

    private String bId;

    private Integer type;

    private Integer publish;

    private Integer contentType;

    private Integer sort;

    private Integer businessType;

    private Integer isHot;

    private Integer freeNum;

    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CatalogueRelationVo() {
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public String getAId() {
        return this.aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getBId() {
        return this.bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
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
}