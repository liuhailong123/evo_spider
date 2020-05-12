package cn.com.evo.integration.nxsp.pay.model;

/**
 * @author rf
 * @date 2019/5/27
 */
public class ProductInfoResponse {
    private Integer code;

    private String productId;

    private String name;

    private String content;

    private String currentPrice;

    private String priceCycleDate;

    public ProductInfoResponse() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getPriceCycleDate() {
        return priceCycleDate;
    }

    public void setPriceCycleDate(String priceCycleDate) {
        this.priceCycleDate = priceCycleDate;
    }
}
