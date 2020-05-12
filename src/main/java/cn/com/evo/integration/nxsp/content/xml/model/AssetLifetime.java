package cn.com.evo.integration.nxsp.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/8
 */
public class AssetLifetime implements Serializable {
    private String startDateTime;

    private String endDateTime;

    public AssetLifetime() {
    }

    public AssetLifetime(String startDateTime, String endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @XmlAttribute(name = "startDateTime")
    public String getStartDateTime() {

        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }
    @XmlAttribute(name = "endDateTime")
    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
