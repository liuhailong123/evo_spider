package cn.com.evo.integration.chongqing.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 重庆已购信息类
 * @author rf
 * @date 2019/6/11
 */
public class ProductOrder {
    /**
     * 套餐编号
     */
    private String offerId;
    /**
     * 套餐名称
     */
    private String offerName;
    /**
     * 直播或点播  line-直播  point-点播
     */
    private String lineOrPoint;
    /**
     * 套餐实例Id
     */
    private String offerInstId;
    /**
     * 产品信息列表
     */
    private List<Product> productLs;

    public List<Product> getProductLs() {
        return productLs;
    }

    public void setProductLs(List<Product> productLs) {
        this.productLs = productLs;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getLineOrPoint() {
        return lineOrPoint;
    }

    public void setLineOrPoint(String lineOrPoint) {
        this.lineOrPoint = lineOrPoint;
    }

    public String getOfferInstId() {
        return offerInstId;
    }

    public void setOfferInstId(String offerInstId) {
        this.offerInstId = offerInstId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("offerId", offerId)
                .append("offerName", offerName)
                .append("lineOrPoint", lineOrPoint)
                .append("offerInstId", offerInstId)
                .append("productLs", productLs)
                .toString();
    }
}
