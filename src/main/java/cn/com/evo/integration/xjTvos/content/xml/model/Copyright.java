package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class Copyright implements Serializable {
    /**
     * 同groupAsset中的providerID
     */
    private String providerID;

    /**
     * 资产ID
     */
    private String assetID;

    /**
     * 默认值1
     */
    private String updateNum;

    /**
     * 时间类
     */
    private AssetLifetime assetLifetime;

    /**
     * 版权类型 0-无  1-有
     */
    private String licenseType;

    /**
     * 可再授权 0-否 1-是
     */
    private String transferAgain;

    /**
     * 版权内容类型
     * 1 影视, 2, 游戏, 3, 信息,4, 音乐,5, 电视剧,6, 星空动漫,7, 电视专栏
     */
    private String copyType;

    /**
     * 	源版权人
     */
    private String originalLicenseCompany;

    /**
     * 已转授3次及以上 0-否 1-是
     */
    private String transfer3rd;

    /**
     * 版权名称
     */
    private String copyName;

    /**
     * 发行/公映许可证号
     */
    private String publishNo;

    /**
     * 进口批准文号
     */
    private String authorserilNo;

    /**
     * 音像制品版号
     */
    private String videoLicense;

    /**
     * 版权备注
     */
    private String copyIdea;

    /**
     * 国家批转文号
     */
    private String countryNo;

    /**
     * 发布日期(YYYY-MM-DD)
     */
    private String publicationDate;

    /**
     * 版权书编号
     */
    private String copyLicense;

    /**
     * 授权序号
     */
    private String copySerilNo;

    /**
     * 授权单位
     */
    private String copyLicenser;

    /**
     * 转授权人
     */
    private String transferLicenseCompany;

    public Copyright() {
    }

    public Copyright(String providerID, String assetID, String updateNum, AssetLifetime assetLifetime, String licenseType, String transferAgain, String copyType, String originalLicenseCompany, String transfer3rd, String copyName, String publishNo, String authorserilNo, String videoLicense, String copyIdea, String countryNo, String publicationDate, String copyLicense, String copySerilNo, String copyLicenser, String transferLicenseCompany) {

        this.providerID = providerID;
        this.assetID = assetID;
        this.updateNum = updateNum;
        this.assetLifetime = assetLifetime;
        this.licenseType = licenseType;
        this.transferAgain = transferAgain;
        this.copyType = copyType;
        this.originalLicenseCompany = originalLicenseCompany;
        this.transfer3rd = transfer3rd;
        this.copyName = copyName;
        this.publishNo = publishNo;
        this.authorserilNo = authorserilNo;
        this.videoLicense = videoLicense;
        this.copyIdea = copyIdea;
        this.countryNo = countryNo;
        this.publicationDate = publicationDate;
        this.copyLicense = copyLicense;
        this.copySerilNo = copySerilNo;
        this.copyLicenser = copyLicenser;
        this.transferLicenseCompany = transferLicenseCompany;
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

    @XmlElement(name = "vod:LicenseType")
    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    @XmlElement(name = "vod:TransferAgain")
    public String getTransferAgain() {
        return transferAgain;
    }

    public void setTransferAgain(String transferAgain) {
        this.transferAgain = transferAgain;
    }

    @XmlElement(name = "vod:CopyType")
    public String getCopyType() {
        return copyType;
    }

    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    @XmlElement(name = "vod:OriginalLicenseCompany")
    public String getOriginalLicenseCompany() {
        return originalLicenseCompany;
    }

    public void setOriginalLicenseCompany(String originalLicenseCompany) {
        this.originalLicenseCompany = originalLicenseCompany;
    }

    @XmlElement(name = "vod:Transfer3rd")
    public String getTransfer3rd() {
        return transfer3rd;
    }

    public void setTransfer3rd(String transfer3rd) {
        this.transfer3rd = transfer3rd;
    }
    @XmlElement(name = "vod:CopyName")
    public String getCopyName() {
        return copyName;
    }

    public void setCopyName(String copyName) {
        this.copyName = copyName;
    }
    @XmlElement(name = "vod:PublishNo")
    public String getPublishNo() {
        return publishNo;
    }

    public void setPublishNo(String publishNo) {
        this.publishNo = publishNo;
    }
    @XmlElement(name = "vod:AuthorserilNo")
    public String getAuthorserilNo() {
        return authorserilNo;
    }

    public void setAuthorserilNo(String authorserilNo) {
        this.authorserilNo = authorserilNo;
    }
    @XmlElement(name = "vod:VideoLicense")
    public String getVideoLicense() {
        return videoLicense;
    }

    public void setVideoLicense(String videoLicense) {
        this.videoLicense = videoLicense;
    }
    @XmlElement(name = "vod:CopyIdea")
    public String getCopyIdea() {
        return copyIdea;
    }

    public void setCopyIdea(String copyIdea) {
        this.copyIdea = copyIdea;
    }
    @XmlElement(name = "vod:CountryNo")
    public String getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(String countryNo) {
        this.countryNo = countryNo;
    }
    @XmlElement(name = "vod:PublicationDate")
    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    @XmlElement(name = "vod:CopyLicense")
    public String getCopyLicense() {
        return copyLicense;
    }

    public void setCopyLicense(String copyLicense) {
        this.copyLicense = copyLicense;
    }
    @XmlElement(name = "vod:CopySerilNo")
    public String getCopySerilNo() {
        return copySerilNo;
    }

    public void setCopySerilNo(String copySerilNo) {
        this.copySerilNo = copySerilNo;
    }
    @XmlElement(name = "vod:CopyLicenser")
    public String getCopyLicenser() {
        return copyLicenser;
    }

    public void setCopyLicenser(String copyLicenser) {
        this.copyLicenser = copyLicenser;
    }
    @XmlElement(name = "vod:TransferLicenseCompany")
    public String getTransferLicenseCompany() {
        return transferLicenseCompany;
    }

    public void setTransferLicenseCompany(String transferLicenseCompany) {
        this.transferLicenseCompany = transferLicenseCompany;
    }
}
