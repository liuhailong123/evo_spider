package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class Director implements Serializable {
    /**
     * 全名
     */
    private String LastNameFirst;

    public Director(String lastNameFirst) {
        LastNameFirst = lastNameFirst;
    }

    public Director() {
    }

    @XmlElement(name = "vod:LastNameFirst")
    public String getLastNameFirst() {

        return LastNameFirst;
    }

    public void setLastNameFirst(String lastNameFirst) {
        LastNameFirst = lastNameFirst;
    }
}
