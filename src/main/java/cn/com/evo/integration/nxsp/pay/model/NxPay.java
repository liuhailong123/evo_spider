package cn.com.evo.integration.nxsp.pay.model;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/23
 */
public class NxPay {
    private String accesstoken;

    private Integer orderid;

    private Integer paymentmode;

    private Paymentinfo paymentinfo;


    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(Integer paymentmode) {
        this.paymentmode = paymentmode;
    }

    public Paymentinfo getPaymentinfo() {
        return paymentinfo;
    }

    public void setPaymentinfo(Paymentinfo paymentinfo) {
        this.paymentinfo = paymentinfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accesstoken", accesstoken)
                .append("orderid", orderid)
                .append("paymentmode", paymentmode)
                .append("paymentinfo", paymentinfo)
                .toString();
    }
}
