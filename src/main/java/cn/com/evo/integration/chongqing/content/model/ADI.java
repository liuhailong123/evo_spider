package cn.com.evo.integration.chongqing.content.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author rf
 * @date 2019/6/3
 */
@XmlRootElement(name = "ADI")
public class ADI {
    private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

    private List<Object> objects;

    private List<Mapping> mappings;

    private String bizDomain;

    private String priority;
    @XmlAttribute(name = "BizDomain")
    public String getBizDomain() {
        return bizDomain;
    }

    public void setBizDomain(String bizDomain) {
        this.bizDomain = bizDomain;
    }
    @XmlAttribute(name = "Priority")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @XmlAttribute(name = "xmlns:xsi")
    public String getXsi() {
        return xsi;
    }

    public void setXsi(String xsi) {
        this.xsi = xsi;
    }

    @XmlElementWrapper(name = "Objects")
    @XmlElement(name = "Object")
    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    @XmlElementWrapper(name = "Mappings")
    @XmlElement(name = "Mapping")
    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }
}
