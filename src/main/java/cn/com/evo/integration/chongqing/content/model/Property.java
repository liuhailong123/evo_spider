package cn.com.evo.integration.chongqing.content.model;

import com.github.bingoohuang.patchca.random.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author rf
 * @date 2019/6/3
 */
@XmlType(propOrder = {
        "name",
        "value",
})
public class Property {
    private String name;

    private String value;

    public Property() {
    }

    public Property(String name, String value) {

        this.name = name;
        this.value = value;
    }

    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private static Property init(String name, String value){
        return new Property(name, value);
    }

    /**
     * property参数转换
     * @param obj
     * @return
     */
    public static List<Property> createPropertysForObj(java.lang.Object obj){
        List<Property> properties = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String value = (String) field.get(obj);
                if(StringUtils.isNotEmpty(value)){
                    properties.add(init(field.getName(), value));
                }/* else {
                    properties.add(init(field.getName(), value));
                }*/
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("value", value)
                .toString();
    }

    public static void main(String[] args) {
        ObjectPropertys programPropertys = new ObjectPropertys();
        programPropertys.setSourceType("1");
        List<Property> propertysForObj = createPropertysForObj(programPropertys);
        System.out.println(propertysForObj);
    }
}
