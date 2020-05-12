package cn.com.evo.integration.nxsp.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/23
 */
public class Buy {
    private Integer targetid;

    private Integer programid;

    private Integer targettype;

    private Integer effectivetime;

    private Integer isreorder;

    private OrderDuration orderduration;

    private Integer priceid;

    private Recharge recharge;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("targetid", targetid)
                .append("programid", programid)
                .append("targettype", targettype)
                .append("effectivetime", effectivetime)
                .append("isreorder", isreorder)
                .append("orderduration", orderduration)
                .append("priceid", priceid)
                .append("recharge", recharge)
                .toString();
    }

    public Integer getTargetid() {
        return targetid;
    }

    public void setTargetid(Integer targetid) {
        this.targetid = targetid;
    }

    public Integer getProgramid() {
        return programid;
    }

    public void setProgramid(Integer programid) {
        this.programid = programid;
    }

    public Integer getTargettype() {
        return targettype;
    }

    public void setTargettype(Integer targettype) {
        this.targettype = targettype;
    }

    public Integer getEffectivetime() {
        return effectivetime;
    }

    public void setEffectivetime(Integer effectivetime) {
        this.effectivetime = effectivetime;
    }

    public Integer getIsreorder() {
        return isreorder;
    }

    public void setIsreorder(Integer isreorder) {
        this.isreorder = isreorder;
    }

    public OrderDuration getOrderduration() {
        return orderduration;
    }

    public void setOrderduration(OrderDuration orderduration) {
        this.orderduration = orderduration;
    }

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public Recharge getRecharge() {
        return recharge;
    }

    public void setRecharge(Recharge recharge) {
        this.recharge = recharge;
    }
}
