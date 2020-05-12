package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class ContentAsset implements Serializable {

    /**
     * 本规范中定义类型：
     * Image图片实体内容、
     * Video视频实体内容、
     * Audio音频实体内容、
     * PhysicalChannel物理频道实体内容、
     * Program程序实体内容、
     * AssetProgram节目单实体内容、
     * Text文本实体内容
     */
    private String type;

    /**
     * 默认值N
     */
    private String metadataOnly = "N";

    /**
     * 实体文件名称，如果对应实体文件没有些属性，可不要
     */
    private String fileName;

    /**
     * 实体文件大小（byte）如果对应实体文件没有些属性，可不要
     */
    private String fileSize;

    /**
     * 实体文件MD5值，如果对应实体文件没有些属性，可不要
     */
    private String mD5CheckSum;

    /**
     * 子内容原始子内容id
     */
    private String original_Asset_ID;

    /**
     * 子内容的顺序，数字。子内容生成方式为拆条编辑而来时，代表生成该子内容的拆条在原子内容所有拆条中的顺序
     * 在实例xml中没有该属性。但是在文档描述里面有，暂时添加。如有问题。尝试删除
     */
    private String order;

    /**
     * 图片信息
     */
    private Image image;

    /**
     * 视频信息
     */
    private Video video;

    public ContentAsset() {
    }

    public ContentAsset(String type, String metadataOnly, String fileName, String fileSize, String mD5CheckSum, String original_Asset_ID, String order, Image image, Video video) {

        this.type = type;
        this.metadataOnly = metadataOnly;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mD5CheckSum = mD5CheckSum;
        this.original_Asset_ID = original_Asset_ID;
        this.order = order;
        this.image = image;
        this.video = video;
    }

    @XmlAttribute(name = "type")
    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "metadataOnly")
    public String getMetadataOnly() {
        return metadataOnly;
    }

    public void setMetadataOnly(String metadataOnly) {
        this.metadataOnly = metadataOnly;
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

    @XmlAttribute(name = "original_Asset_ID")
    public String getOriginal_Asset_ID() {
        return original_Asset_ID;
    }

    public void setOriginal_Asset_ID(String original_Asset_ID) {
        this.original_Asset_ID = original_Asset_ID;
    }

    @XmlAttribute(name = "order")
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @XmlElement(name = "vod:Image")
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @XmlElement(name = "vod:Video")
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
