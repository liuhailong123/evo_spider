package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * 内容实体
 *
 * @author rf
 * @date 2019/5/8
 */
public class GroupAsset implements Serializable {

    /**
     * 默认值VODRelease
     */
    private String type;

    /**
     * 默认值VOD
     */
    private String product;

    private VODRelease vodRelease;

    public GroupAsset() {
    }

    public GroupAsset(String type, String product, VODRelease vodRelease) {
        this.type = type;
        this.product = product;
        this.vodRelease = vodRelease;
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
    @XmlElement(name = "vod:VODRelease")
    public VODRelease getVodRelease() {
        return vodRelease;
    }

    public void setVodRelease(VODRelease vodRelease) {
        this.vodRelease = vodRelease;
    }
}
