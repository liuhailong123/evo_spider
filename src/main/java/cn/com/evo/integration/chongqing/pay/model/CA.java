package cn.com.evo.integration.chongqing.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 重庆鉴权用户类
 * @author rf
 * @date 2019/6/11
 */
public class CA {
    /**
     * 用户编号
     */
    private String prodInstId;
    /**
     * 终端编号
     */
    private String caId;
    /**
     * 终端状态
     * 1：正常
     * 2：异常
     */
    private String caType;
    /**
     * 终端类型 1-正常  2-异常
     */
    private String status;
    /**
     * ca是高清还是标清
     * HD：高清
     * SD：标清
     */
    private String caModel;
    /**
     * 主机副机标识（订购直播套餐时传入）
     * 0：主机
     * 1：副机
     */
    private String isMain;

    /**
     * 已购明细
     */
    private List<ProductOrder> productOrderList;

    public String getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(String prodInstId) {
        this.prodInstId = prodInstId;
    }

    public String getCaId() {
        return caId;
    }

    public void setCaId(String caId) {
        this.caId = caId;
    }

    public String getCaType() {
        return caType;
    }

    public void setCaType(String caType) {
        this.caType = caType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaModel() {
        return caModel;
    }

    public void setCaModel(String caModel) {
        this.caModel = caModel;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("prodInstId", prodInstId)
                .append("caId", caId)
                .append("caType", caType)
                .append("status", status)
                .append("caModel", caModel)
                .append("isMain", isMain)
                .append("productOrderList", productOrderList)
                .toString();
    }
}
