package cn.com.evo.integration.nxsp.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/24
 */
public class CreateOrderResponse {
    private Integer ret;

    private String ret_msg;

    private String order_id;

    private String pay_order_id;

    private String price;

    private String message;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ret", ret)
                .append("ret_msg", ret_msg)
                .append("order_id", order_id)
                .append("pay_order_id", pay_order_id)
                .append("price", price)
                .append("message", message)
                .toString();
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPay_order_id() {
        return pay_order_id;
    }

    public void setPay_order_id(String pay_order_id) {
        this.pay_order_id = pay_order_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
