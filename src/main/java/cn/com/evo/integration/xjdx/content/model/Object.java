package cn.com.evo.integration.xjdx.content.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author rf
 * @date 2019/6/3
 */
public class Object {
    /**
     * 元素的定义  Program节目信息、Movie媒体内容信息、Picture图片信息、Series剧集信息
     */
    private String ElementType;
    /**
     * 接口中的唯一标识、
     */
    private String ID;
    /**
     * 操作类型  REGIST、UPDATE、DELETE
     */
    private String Action;
    /**
     * 全局唯一标识
     */
    private String Code;

    /**
     * 属性
     */
    private List<Property> properties;

    @XmlElement(name = "Property")
    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {

        this.properties = properties;
    }
    @XmlAttribute(name = "ElementType")
    public String getElementType() {
        return ElementType;
    }

    public void setElementType(String elementType) {
        ElementType = elementType;
    }
    @XmlAttribute(name = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    @XmlAttribute(name = "Action")
    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
    @XmlAttribute(name = "Code")
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

}
