package cn.com.evo.integration.nxsp.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/24
 */
public class GetInfoResponse {
    /**
     * 返回码，返回 0 表示成功，返回其他值表示失败。
     */
    private Integer ret;
    /**
     * 返回码对应的描述信息
     */
    private String ret_msg;
    /**
     * 节目是否已购买，0 表示未购买，1 表示已购买。
     * 当节目不在套餐中或者套餐定价为 0 时，返回 1
     */
    private Integer is_purchased;

    /**
     * 节目授权信息
     */
    private String play_token;
    /**
     * 点播地址列表
     */
    private String[] demand_url;
    /**
     * 清晰度
     */
    private String[] rate_list;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ret", ret)
                .append("ret_msg", ret_msg)
                .append("is_purchased", is_purchased)
                .append("play_token", play_token)
                .append("demand_url", demand_url)
                .append("rate_list", rate_list)
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

    public String getPlay_token() {
        return play_token;
    }

    public void setPlay_token(String play_token) {
        this.play_token = play_token;
    }

    public String[] getDemand_url() {
        return demand_url;
    }

    public void setDemand_url(String[] demand_url) {
        this.demand_url = demand_url;
    }

    public String[] getRate_list() {
        return rate_list;
    }

    public void setRate_list(String[] rate_list) {
        this.rate_list = rate_list;
    }
}
