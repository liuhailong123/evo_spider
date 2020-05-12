package cn.com.evo.integration.nxsp.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class Image implements Serializable {

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
    private String updateNum = "1";

    /**
     * 实体文件名称
     */
    private String fileName;

    /**
     * 实体文件大小byte
     */
    private String fileSize;

    /**
     * 实体文件md5值
     */
    private String mD5CheckSum;

    /**
     * 实体文件的存放地址
     * 说明当删除文件时此地址可为空，或者填写删除文件前真实合法地址，
     * 例如：：ftp:// sihuatech:sihuatech @192.168.2.211:21/data/bian3.ts。
     * 不允许类似：ftp://1.1.1.1等随意书写地址
     * 文件的绝对地址、ftp/file
     */
    private String transferContentURL;

    /**
     * 压缩方式
     * bmp：bmp、 jpg：jpg、 png：png、gif:gif
     */
    private String imageEncodingProfile;

    /**
     * 时间描述类
     */
    private AssetLifetime assetLifetime;

    /**
     * 标题
     */
    private String caption;

    /**
     * 媒体类型（枚举说明中，空格前面是对应的MimeType，空格后面是对应的文件后缀）
     * 1－application/java-archive jar
     * 2－application/jwc jwc
     * 3－application/pdf pdf
     * 4－application/rtf rtf
     * 5－application/vnd.lotus-1-2-3 123
     * 6－application/vnd.ms-powerpoint ppt,pps,pot
     * 7－application/vnd.ms-project mpp
     * 8－application/x-gzip gz,x-gzip
     * 9－application/xhtml+xml xhtml,xhtm,xht
     * 10－application/x-javascript js
     * 11－application/x-shockwave-flash swf,swfl
     * 12－application/zip zip,nar
     * 13－audio/midi mid,midi
     * 14－audio/voxware vox
     * 15－audio/x-imy imy
     * 16－audio/x-mpeg mp2, mp3
     * 17－audio/x-mpegurl m3u
     * 18－audio/x-ms-wma wma
     * 19－audio/x-pn-realaudio ra, ram, rm
     * 20－audio/x-wav wav
     * 21－image/bmp bmp
     * 22－image/gif gif,ifm
     * 23－image/ief ief
     * 24－image/ifs ifs
     * 25－image/jpeg jpeg,jpg,jpe,jpz
     * 26－image/png png,pnz
     * 27－image/vnd.wap.wbmp wbmp
     * 28－image/x-icon ico
     * 29－image/x-pcx pcx
     * 30－text/css css
     * 31－text/html htm,html
     * 32－text/plain txt,asc
     * 33－text/richtext rtx
     * 34－text/tab-separated-values tsv
     * 35－text/vnd.sun.j2me.app-descriptor jad
     * 36－text/vnd.wap.wml wml
     * 37－text/xml xml,xsl
     * 38－video/3gpp 3gp
     * 39－video/isivideo fvi
     * 40－video/mpeg mpeg,mpg,mpe
     * 41－video/msvideo avi
     * 42－video/x-ms-wmv wmv
     * 43－video/mp4 mp4,mpg4
     * 44－Video/QuickTime
     * 45 application/x-mpegurl .m3u8
     * 46 application/octet-stream .ts
     * 47  video/x-flv flv
     * 48  video/mp4 f4v
     * 49 - application/vnd.rn-realmedia rm
     * 50 - audio/vnd.rn-realaudio rm
     * 51 - audio/x-realaudio rm
     * 52 - audio/x-pm-realaudio-plugin rm
     * 53 - audio/3gpp 3gp
     * 54 - audio/3gpp2 3gp
     * 55 - video/3gpp2 3gp
     * 56 - video/x-quicktime mov
     * 57 - image/mov mov
     * 58 - audio/aiff mov
     * 59 - audio/x-midi mov
     * 60 - video/av mov
     * 61 - audio/mpeg mp3
     * 62 - audio/mp3 mp3
     * 63 - audio/x-mp3 mp3
     * 64 - audio/mpeg3 mp3
     * 65 - audio/x-mpeg3 mp3
     * 66 - audio/mpg mp3
     * 67 - audio/x-mpg mp3
     * 68 - audio/x-mpegaudio mp3
     * 98 - video/mpeg2
     * 99 - video/x-h264
     */
    private String mimeType;

    /**
     * 文件类型
     * 1：视频,2：音频,3：图片, 程序=4,BREW=5,应用=6, 文本=7
     */
    private String fileType;

    /**
     * 内容的应用属性
     * wap视频
     * 宽带视频
     * IPTV视频
     * IPONE视频
     * Android视频
     * WAP和Android视频
     * Android和宽带视频
     * 8、视频炫铃9、IVVR视频
     * 10、互联网电视视频
     * 11、STB
     * 12、OTT
     */
    private String serviceType;

    /**
     * 节目的分类(多个二级分类以英文逗号,分隔)
     * 电影,电视剧,新闻,栏目,资讯，专题，音乐，游戏，人物。不列出，具体内容查看宁夏sp文档：homed媒资导入_媒资类型补充说明
     */
    private String colorType;

    /**
     * 文件用途
     * 0: 缩略图   2: 剧照  3: 图标  4: 标题图   5: 广告图，6：海报, 7: 草图   8: 背景图 9：宣传画  10: 频道图片
     * 11: 频道黑白图片   12: 频道Logo  13: 频道名字图片   14：正片图片 15：大海报 16：小海报 17：大图片 18：小图片 
     * 99: 其他
     */
    private String usage;

    /**
     * 码率（单位bps）
     */
    private ImagePixels imagePixels;

    public Image() {
    }

    public Image(String providerID, String assetID, String updateNum, String fileName, String fileSize, String mD5CheckSum, String transferContentURL, String imageEncodingProfile, AssetLifetime assetLifetime, String caption, String mimeType, String fileType, String serviceType, String colorType, String usage, ImagePixels imagePixels) {
        this.providerID = providerID;
        this.assetID = assetID;
        this.updateNum = updateNum;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mD5CheckSum = mD5CheckSum;
        this.transferContentURL = transferContentURL;
        this.imageEncodingProfile = imageEncodingProfile;
        this.assetLifetime = assetLifetime;
        this.caption = caption;
        this.mimeType = mimeType;
        this.fileType = fileType;
        this.serviceType = serviceType;
        this.colorType = colorType;
        this.usage = usage;
        this.imagePixels = imagePixels;
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

    @XmlAttribute(name = "imageEncodingProfile")
    public String getImageEncodingProfile() {
        return imageEncodingProfile;
    }

    public void setImageEncodingProfile(String imageEncodingProfile) {
        this.imageEncodingProfile = imageEncodingProfile;
    }

    @XmlElement(name = "adi:AssetLifetime")
    public AssetLifetime getAssetLifetime() {
        return assetLifetime;
    }

    public void setAssetLifetime(AssetLifetime assetLifetime) {
        this.assetLifetime = assetLifetime;
    }

    @XmlElement(name = "vod:Caption")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @XmlElement(name = "vod:MimeType")
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @XmlElement(name = "vod:FileType")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @XmlElement(name = "vod:ServiceType")
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @XmlElement(name = "vod:ColorType")
    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    @XmlElement(name = "vod:Usage")
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @XmlElement(name = "vod:ImagePixels")
    public ImagePixels getImagePixels() {
        return imagePixels;
    }

    public void setImagePixels(ImagePixels imagePixels) {
        this.imagePixels = imagePixels;
    }
}
