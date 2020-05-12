package cn.com.evo.integration.xjTvos.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/23
 */
public class Paymentinfo {
    private String sign;

    private String tradetype;

    private String spbillcreateipstring;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sign", sign)
                .append("tradetype", tradetype)
                .append("spbillcreateipstring", spbillcreateipstring)
                .toString();
    }

    public String getSign() {

        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getSpbillcreateipstring() {
        return spbillcreateipstring;
    }

    public void setSpbillcreateipstring(String spbillcreateipstring) {
        this.spbillcreateipstring = spbillcreateipstring;
    }
}
