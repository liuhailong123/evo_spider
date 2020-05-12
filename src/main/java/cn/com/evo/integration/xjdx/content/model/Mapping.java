package cn.com.evo.integration.xjdx.content.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author rf
 * @date 2019/6/5
 */
public class Mapping {
    /**
     * 唯一id
     */
    private String ID;
    /**
     * 操作类型
     */
    private String Action;
    /**
     * 父元素类型
     */
    private String ParentType;
    /**
     * 元素类型
     */
    private String ElementType;
    /**
     * 父id
     */
    private String ParentID;
    /**
     * 元素id
     */
    private String ElementID;
    /**
     * 父元素code
     */
    private String ParentCode;
    /**
     * 元素code
     */
    private String ElementCode;

    /**
     * property集合
     */
    private List<Property> properties;

    @XmlElement(name = "Property")
    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
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
    @XmlAttribute(name = "ParentType")
    public String getParentType() {
        return ParentType;
    }

    public void setParentType(String parentType) {
        ParentType = parentType;
    }
    @XmlAttribute(name = "ElementType")
    public String getElementType() {
        return ElementType;
    }

    public void setElementType(String elementType) {
        ElementType = elementType;
    }
    @XmlAttribute(name = "ParentID")
    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }
    @XmlAttribute(name = "ElementID")
    public String getElementID() {
        return ElementID;
    }

    public void setElementID(String elementID) {
        ElementID = elementID;
    }
    @XmlAttribute(name = "ParentCode")
    public String getParentCode() {
        return ParentCode;
    }

    public void setParentCode(String parentCode) {
        ParentCode = parentCode;
    }
    @XmlAttribute(name = "ElementCode")
    public String getElementCode() {
        return ElementCode;
    }

    public void setElementCode(String elementCode) {
        ElementCode = elementCode;
    }
}
