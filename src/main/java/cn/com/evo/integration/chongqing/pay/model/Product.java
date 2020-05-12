package cn.com.evo.integration.chongqing.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 已购套餐下的产品
 * @author rf
 * @date 2019/6/11
 */
public class Product {
    /**
     * 产品编号
     */
    private String productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品状态
     * 0正常 ，1不正常
     */
    private String status;
    /**
     * 生效时间
     */
    private String validDate;
    /**
     * 失效时间
     */
    private String expireDate;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("productId", productId)
                .append("productName", productName)
                .append("status", status)
                .append("validDate", validDate)
                .append("expireDate", expireDate)
                .toString();
    }
}
