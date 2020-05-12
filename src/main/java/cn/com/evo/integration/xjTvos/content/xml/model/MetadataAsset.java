package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class MetadataAsset implements Serializable {
    /**
     * 同groupAsset中的providerID
     */
    private String groupProviderID;

    /**
     * 同groupAsset中的assetID
     */
    private String groupAssetID;

    /**
     * 本规范中定义三种类型，Title基本信息、CategoryPath分类信息、Copyright版权信息
     */
    private String type;

    /**
     * 默认值VOD
     */
    private String product;

    /**
     * 基本信息
     */
    private Title title;

    /**
     * 分类信息
     */
    private CategoryPath categoryPath;

    /**
     * 版权信息
     */
    private Copyright copyright;

    public MetadataAsset() {
    }

    public MetadataAsset(String groupProviderID, String groupAssetID, String type, String product, Title title, CategoryPath categoryPath, Copyright copyright) {
        this.groupProviderID = groupProviderID;
        this.groupAssetID = groupAssetID;
        this.type = type;
        this.product = product;
        this.title = title;
        this.categoryPath = categoryPath;
        this.copyright = copyright;
    }

    @XmlElement(name = "vod:CategoryPath")
    public CategoryPath getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(CategoryPath categoryPath) {
        this.categoryPath = categoryPath;
    }

    @XmlElement(name = "vod:Copyright")
    public Copyright getCopyright() {
        return copyright;
    }

    public void setCopyright(Copyright copyright) {
        this.copyright = copyright;
    }

    @XmlElement(name = "vod:Title")
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @XmlAttribute(name = "groupProviderID")
    public String getGroupProviderID() {
        return groupProviderID;
    }

    public void setGroupProviderID(String groupProviderID) {
        this.groupProviderID = groupProviderID;
    }
    @XmlAttribute(name = "groupAssetID")
    public String getGroupAssetID() {
        return groupAssetID;
    }

    public void setGroupAssetID(String groupAssetID) {
        this.groupAssetID = groupAssetID;
    }
    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @XmlAttribute(name = "product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
