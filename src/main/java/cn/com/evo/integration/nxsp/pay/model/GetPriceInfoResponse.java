package cn.com.evo.integration.nxsp.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author rf
 * @date 2019/5/23
 */
public class GetPriceInfoResponse {
    private Integer ret;

    private String ret_msg;

    /**
     * 0 未购买   1-已购买
     */
    private Integer is_purchased;

    private Buy buy;

    private Integer total;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ret", ret)
                .append("ret_msg", ret_msg)
                .append("is_purchased", is_purchased)
                .append("buy", buy)
                .append("total", total)
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

    public Integer getIs_purchased() {
        return is_purchased;
    }

    public void setIs_purchased(Integer is_purchased) {
        this.is_purchased = is_purchased;
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
