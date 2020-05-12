package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class CategoryPath implements Serializable {

    private String providerID;

    private String assetID;

    private String updateNum;

    private AssetLifetime assetLifetime;

    /**
     * 类别分类(多个二级分类以英文逗号,分隔)
     */
    private String category;

    /**
     * 一级分类
     */
    private String classification;

    public CategoryPath() {
    }

    public CategoryPath(String providerID, String assetID, String updateNum, AssetLifetime assetLifetime, String category, String classification) {

        this.providerID = providerID;
        this.assetID = assetID;
        this.updateNum = updateNum;
        this.assetLifetime = assetLifetime;
        this.category = category;
        this.classification = classification;
    }

    @XmlAttribute(name = "providerID")
    public String getProviderID() {

        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }
    @XmlAttribute(name = "assetID")
    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }
    @XmlAttribute(name = "updateNum")
    public String getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(String updateNum) {
        this.updateNum = updateNum;
    }
    @XmlElement(name = "adi:AssetLifetime")
    public AssetLifetime getAssetLifetime() {
        return assetLifetime;
    }

    public void setAssetLifetime(AssetLifetime assetLifetime) {
        this.assetLifetime = assetLifetime;
    }
    @XmlElement(name = "vod:Category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @XmlElement(name = "vod:Classification")
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
