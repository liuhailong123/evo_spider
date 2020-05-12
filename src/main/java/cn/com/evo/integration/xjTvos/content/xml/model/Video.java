package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class Video implements Serializable {

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
     * 实体文件名称
     */
    private String fileName;

    /**
     * 实体文件大小 byte
     */
    private String fileSize;

    /**
     * 实体文件MD5值
     */
    private String mD5CheckSum;

    /**
     * 实体文件的存放地址
     * 说明当删除文件时此地址可为空，或者填写删除文件前真实合法地址，
     * 例如：：ftp:// sihuatech:sihuatech @192.168.2.211:21/data/bian3.ts。
     * 不允许类似：ftp://1.1.1.1等随意书写地址
     */
    private String transferContentURL;

    /**
     * 编码参数
     * 对应各系统中维护的编码参数的代码，考虑到在线系统的兼容性，非必填，系统对接时根据实际情况约定是否需要传递此参数；
     */
    private String encodingProfile;

    /**
     * 编码code，暂代encodingProfile的作用
     */
    private String encodingCode;

    /**
     * 时间描述类
     */
    private AssetLifetime assetLifetime;

    /**
     * 宽高比例
     */
    private String aspectRatio;

    /**
     * 高清标识，高清：0 ；标清：1
     */
    private String hdFlag;

    /**
     * 亮度
     */
    private String brightness;

    /**
     * 帧高
     */
    private String frameHeight;

    /**
     * 是否加工完毕，如果不填写默认为成品（加工完毕）。
     * 1：成品（加工完成，经过加工）
     * 0：素材
     */
    private String isFinished;

    /**
     * 媒体类型
     */
    private String mimeType;

    /**
     * 是否粗编，如果不写默认1非粗编
     * 1：非粗编
     * 2：粗编
     */
    private String isEdit;

    /**
     * 内容的应用属性
     * 1、wap视频
     * 2、宽带视频
     * 3、IPTV视频
     * 4、IPONE视频
     * 5、Android视频
     * 6、WAP和Android视频
     * 7、Android和宽带视频
     * 8、视频炫铃9、IVVR视频
     * 10、互联网电视视频
     * 11、STB
     * 12、OTT
     */
    private String serviceType;

    /**
     * 父的资产ID
     * 专使用于关联Video类型的拆条，如果为空则默认用AssociateContent，不为空则使改拆条关联到parentAssetID的资产
     */
    private String parentAssetID;

    /**
     * 是否需要DRM
     * 1：不需要DRM
     * 2：需要DRM
     */
    private String needDRM;

    /**
     * 文件用途
     * 正片=1,宣传片=3,片花=4,花絮=5,海报=6,主程序=8,宣传画=9,其他=7
     */
    private String usage;

    /**
     * 帧宽
     */
    private String frameWidth;

    /**
     * 视频编码格式
     */
    private String videoCodec;

    /**
     * 帧数
     */
    private String numberofframes;

    /**
     * 音频编码格式
     */
    private String audioCodec;

    /**
     * 是否加密文件，如果不写默认1非加密
     * 1：非加密
     * 2：加密
     */
    private String isDRM;

    /**
     * 时长
     * （单位为秒）
     */
    private String duration;

    /**
     * 文件类型
     * 1：视频,2：音频,3：图片, 程序=4,BREW=5,应用=6, 文本=7
     */
    private String fileType;

    /**
     * 对比度
     */
    private String contrast;

    /**
     * 码率（单位bps）
     */
    private String bitrate;

    /**
     * 帧率
     */
    private String frameRate;

    public Video() {
    }

    public Video(String providerID, String assetID, String updateNum, String fileName, String fileSize, String mD5CheckSum, String transferContentURL, String encodingProfile, String encodingCode, AssetLifetime assetLifetime, String aspectRatio, String hdFlag, String brightness, String frameHeight, String isFinished, String mimeType, String isEdit, String serviceType, String parentAssetID, String needDRM, String usage, String frameWidth, String videoCodec, String numberofframes, String audioCodec, String isDRM, String duration, String fileType, String contrast, String bitrate, String frameRate) {

        this.providerID = providerID;
        this.assetID = assetID;
        this.updateNum = updateNum;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mD5CheckSum = mD5CheckSum;
        this.transferContentURL = transferContentURL;
        this.encodingProfile = encodingProfile;
        this.encodingCode = encodingCode;
        this.assetLifetime = assetLifetime;
        this.aspectRatio = aspectRatio;
        this.hdFlag = hdFlag;
        this.brightness = brightness;
        this.frameHeight = frameHeight;
        this.isFinished = isFinished;
        this.mimeType = mimeType;
        this.isEdit = isEdit;
        this.serviceType = serviceType;
        this.parentAssetID = parentAssetID;
        this.needDRM = needDRM;
        this.usage = usage;
        this.frameWidth = frameWidth;
        this.videoCodec = videoCodec;
        this.numberofframes = numberofframes;
        this.audioCodec = audioCodec;
        this.isDRM = isDRM;
        this.duration = duration;
        this.fileType = fileType;
        this.contrast = contrast;
        this.bitrate = bitrate;
        this.frameRate = frameRate;
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

    @XmlAttribute(name = "fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlAttribute(name = "fileSize")
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @XmlAttribute(name = "mD5CheckSum")
    public String getmD5CheckSum() {
        return mD5CheckSum;
    }

    public void setmD5CheckSum(String mD5CheckSum) {
        this.mD5CheckSum = mD5CheckSum;
    }

    @XmlAttribute(name = "transferContentURL")
    public String getTransferContentURL() {
        return transferContentURL;
    }

    public void setTransferContentURL(String transferContentURL) {
        this.transferContentURL = transferContentURL;
    }

    @XmlAttribute(name = "encodingProfile")
    public String getEncodingProfile() {
        return encodingProfile;
    }

    public void setEncodingProfile(String encodingProfile) {
        this.encodingProfile = encodingProfile;
    }

    @XmlAttribute(name = "encodingCode")
    public String getEncodingCode() {
        return encodingCode;
    }

    public void setEncodingCode(String encodingCode) {
        this.encodingCode = encodingCode;
    }

    @XmlElement(name = "adi:AssetLifetime")
    public AssetLifetime getAssetLifetime() {
        return assetLifetime;
    }

    public void setAssetLifetime(AssetLifetime assetLifetime) {
        this.assetLifetime = assetLifetime;
    }

    @XmlElement(name = "vod:AspectRatio")
    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    @XmlElement(name = "vod:HDFlag")
    public String getHdFlag() {
        return hdFlag;
    }

    public void setHdFlag(String hdFlag) {
        this.hdFlag = hdFlag;
    }

    @XmlElement(name = "vod:Brightness")
    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    @XmlElement(name = "vod:FrameHeight")
    public String getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(String frameHeight) {
        this.frameHeight = frameHeight;
    }

    @XmlElement(name = "vod:IsFinished")
    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    @XmlElement(name = "vod:MimeType")
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @XmlElement(name = "vod:IsEdit")
    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    @XmlElement(name = "vod:ServiceType")
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @XmlElement(name = "vod:parentAssetID")
    public String getParentAssetID() {
        return parentAssetID;
    }

    public void setParentAssetID(String parentAssetID) {
        this.parentAssetID = parentAssetID;
    }

    @XmlElement(name = "vod:NeedDRM")
    public String getNeedDRM() {
        return needDRM;
    }

    public void setNeedDRM(String needDRM) {
        this.needDRM = needDRM;
    }

    @XmlElement(name = "vod:Usage")
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @XmlElement(name = "vod:FrameWidth")
    public String getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(String frameWidth) {
        this.frameWidth = frameWidth;
    }

    @XmlElement(name = "vod:VideoCodec")
    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    @XmlElement(name = "vod:Numberofframes")
    public String getNumberofframes() {
        return numberofframes;
    }

    public void setNumberofframes(String numberofframes) {
        this.numberofframes = numberofframes;
    }

    @XmlElement(name = "vod:AudioCodec")
    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    @XmlElement(name = "vod:IsDRM")
    public String getIsDRM() {
        return isDRM;
    }

    public void setIsDRM(String isDRM) {
        this.isDRM = isDRM;
    }

    @XmlElement(name = "vod:Duration")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @XmlElement(name = "vod:FileType")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @XmlElement(name = "vod:Contrast")
    public String getContrast() {
        return contrast;
    }

    public void setContrast(String contrast) {
        this.contrast = contrast;
    }

    @XmlElement(name = "vod:Bitrate")
    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    @XmlElement(name = "vod:FrameRate")
    public String getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }
}
