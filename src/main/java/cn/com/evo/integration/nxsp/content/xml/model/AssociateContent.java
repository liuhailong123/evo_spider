package cn.com.evo.integration.nxsp.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class AssociateContent implements Serializable {

    /**
     * ContentAsset的类型,本规范中定义类型Image、Video、Audio、PhysicalChannel、Program、AssetProgram
     */
    private String type;

    /**
     * 生效日期,暂不处理
     */
    private String effectiveDate;

    /**
     * 同groupAsset中的providerID
     */
    private String groupProviderID;

    /**
     * 对应groupAsset中的assetID
     */
    private String groupAssetID;

    /**
     * CPID，同groupAsset中的providerID
     */
    private String providerID;

    /**
     * 对应实体内容ContentAsset的assetID
     */
    private String assetID;

    public AssociateContent() {
    }

    public AssociateContent(String type, String effectiveDate, String groupProviderID, String groupAssetID, String providerID, String assetID) {

        this.type = type;
        this.effectiveDate = effectiveDate;
        this.groupProviderID = groupProviderID;
        this.groupAssetID = groupAssetID;
        this.providerID = providerID;
        this.assetID = assetID;
    }


    @XmlAttribute(name = "type")
    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "effectiveDate")
    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
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
}
