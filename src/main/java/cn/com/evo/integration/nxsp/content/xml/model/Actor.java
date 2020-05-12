package cn.com.evo.integration.nxsp.content.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class Actor implements Serializable {
    /**
     * Actor男主演员、Actress女主演员、CoActress女配角、CoActor男配角
     */
    private String type;

    /**
     * 全名
     */
    private String lastNameFirst;

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "vod:LastNameFirst")
    public String getLastNameFirst() {
        return lastNameFirst;
    }

    public void setLastNameFirst(String lastNameFirst) {
        this.lastNameFirst = lastNameFirst;
    }

    public Actor(String type, String lastNameFirst) {

        this.type = type;
        this.lastNameFirst = lastNameFirst;
    }

    public Actor() {

    }
}
