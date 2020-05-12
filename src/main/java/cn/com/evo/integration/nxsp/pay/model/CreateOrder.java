package cn.com.evo.integration.nxsp.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/23
 */
public class CreateOrder {
    private String accesstoken;

    private Buy buy;

    public static CreateOrder initOrder(String accesstoken, Integer targetid, Integer programid, Integer targettype){
        CreateOrder createOrder = new CreateOrder();
        createOrder.setAccesstoken(accesstoken);
        Buy buy = new Buy();
        buy.setTargetid(targetid);
        buy.setProgramid(programid);
        buy.setTargettype(targettype);
        createOrder.setBuy(buy);
        return createOrder;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accesstoken", accesstoken)
                .append("buy", buy)
                .toString();
    }

}
