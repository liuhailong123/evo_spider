package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class ImagePixels implements Serializable {

    /**
     * 图像宽度
     */
    private String horizontalPixels;

    /**
     * 图像高度
     */
    private String verticalPixels;

    public ImagePixels() {
    }

    public ImagePixels(String horizontalPixels, String verticalPixels) {

        this.horizontalPixels = horizontalPixels;
        this.verticalPixels = verticalPixels;
    }

    @XmlAttribute(name = "horizontalPixels")
    public String getHorizontalPixels() {

        return horizontalPixels;
    }

    public void setHorizontalPixels(String horizontalPixels) {
        this.horizontalPixels = horizontalPixels;
    }

    @XmlAttribute(name = "verticalPixels")
    public String getVerticalPixels() {
        return verticalPixels;
    }

    public void setVerticalPixels(String verticalPixels) {
        this.verticalPixels = verticalPixels;
    }
}
