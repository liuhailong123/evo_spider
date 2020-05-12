package cn.com.evo.integration.xjTvos.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/24
 */
public class OrderDuration {
    private String value;

    private String unit;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("unit", unit)
                .toString();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
