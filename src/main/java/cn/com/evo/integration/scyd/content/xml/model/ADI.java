package cn.com.evo.integration.scyd.content.xml.model;import javax.xml.bind.annotation.XmlElement;import javax.xml.bind.annotation.XmlRootElement;import javax.xml.bind.annotation.XmlType;import java.util.List;/** * @Description: * @author: lu.xin * @create: 2019-03-05 4:08 PM **/@XmlRootElement(name = "ADI")@XmlType(propOrder = {        "objects",        "mappings"})public class ADI {    private Objects objects;    private Mappings mappings;    public ADI() {    }    public ADI(Objects objects, Mappings mappings) {        this.objects = objects;        this.mappings = mappings;    }    @XmlElement(name = "Objects")    public Objects getObjects() {        return objects;    }    public void setObjects(List<Object> objects) {        objects = objects;    }    @XmlElement(name = "Mappings")    public Mappings getMappings() {        return mappings;    }    public void setMappings(List<Mapping> mappings) {        mappings = mappings;    }}