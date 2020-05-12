package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class AssociateGroup implements Serializable {

    /**
     * 生效日期 暂不处理
     */
    private String effectiveDate;

    /**
     * 同groupAsset中的providerID
     */
    private String groupProviderID;

    /**
     * CPID，同groupAsset中的providerID
     */
    private String providerID;

    /**
     * 对应实体内容ContentAsset的assetID
     */
    private String assetID;

    /**
     * 原GroupAsset的assetID
     */
    private String sourceGroupAssetID;

    /**
     * 目标GroupAsset的assetID
     */
    private String targetGroupAssetID;

    public AssociateGroup() {
    }

    public AssociateGroup(String effectiveDate, String groupProviderID, String providerID, String assetID, String sourceGroupAssetID, String targetGroupAssetID) {

        this.effectiveDate = effectiveDate;
        this.groupProviderID = groupProviderID;
        this.providerID = providerID;
        this.assetID = assetID;
        this.sourceGroupAssetID = sourceGroupAssetID;
        this.targetGroupAssetID = targetGroupAssetID;
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

    @XmlAttribute(name = "sourceGroupAssetID")
    public String getSourceGroupAssetID() {
        return sourceGroupAssetID;
    }

    public void setSourceGroupAssetID(String sourceGroupAssetID) {
        this.sourceGroupAssetID = sourceGroupAssetID;
    }

    @XmlAttribute(name = "targetGroupAssetID")
    public String getTargetGroupAssetID() {
        return targetGroupAssetID;
    }

    public void setTargetGroupAssetID(String targetGroupAssetID) {
        this.targetGroupAssetID = targetGroupAssetID;
    }
}
