package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/8
 */
public class VODRelease implements Serializable {
    /**
     * 内容提供商的ID（CPID）
     */
    private String providerID;
    /**
     * 内容提供商的类型
     */
    private String providerType;
    /**
     * 内容ID，全局唯一
     */
    private String assetID;
    /**
     * 更新数
     */
    private String updateNum;
    /**
     * 默认值Y
     */
    private String groupAsset;
    /**
     * 每次操作的序列号
     */
    private String serialNo;

    private AssetLifetime assetLifetime;

    public VODRelease() {
    }

    public VODRelease(String providerID, String providerType, String assetID, String updateNum, String groupAsset, String serialNo, AssetLifetime assetLifetime) {
        this.providerID = providerID;
        this.providerType = providerType;
        this.assetID = assetID;
        this.updateNum = updateNum;
        this.groupAsset = groupAsset;
        this.serialNo = serialNo;
        this.assetLifetime = assetLifetime;
    }

    @XmlAttribute(name = "providerID")
    public String getProviderID() {

        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }
    @XmlAttribute(name = "providerType")
    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
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
    @XmlAttribute(name = "groupAsset")
    public String getGroupAsset() {
        return groupAsset;
    }

    public void setGroupAsset(String groupAsset) {
        this.groupAsset = groupAsset;
    }
    @XmlAttribute(name = "serialNo")
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @XmlElement(name = "adi:AssetLifetime")
    public AssetLifetime getAssetLifetime() {
        return assetLifetime;
    }

    public void setAssetLifetime(AssetLifetime assetLifetime) {
        this.assetLifetime = assetLifetime;
    }
}
