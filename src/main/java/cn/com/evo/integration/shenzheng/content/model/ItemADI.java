package cn.com.evo.integration.shenzheng.content.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author rf
 * @date 2019/5/30
 */
public class ItemADI {
    private String begin;

    private String end;

    private String type;

    private String name;

    private String img;

    @XmlAttribute
    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }
    @XmlAttribute
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("begin", begin)
                .append("end", end)
                .append("type", type)
                .append("name", name)
                .append("img", img)
                .toString();
    }

    @XmlAttribute
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}