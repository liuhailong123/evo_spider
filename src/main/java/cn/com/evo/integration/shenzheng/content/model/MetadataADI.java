package cn.com.evo.integration.shenzheng.content.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author rf
 * @date 2019/5/30
 */
@XmlRootElement(name = "metadata")
public class MetadataADI {
    private List<ItemADI> item;

    @XmlElementWrapper
    @XmlElement(name = "item")
    public List<ItemADI> getItem() {
        return item;
    }

    public void setItem(List<ItemADI> item) {
        this.item = item;
    }
}
