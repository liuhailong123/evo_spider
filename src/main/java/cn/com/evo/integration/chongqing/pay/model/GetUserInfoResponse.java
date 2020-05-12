package cn.com.evo.integration.chongqing.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author rf
 * @date 2019/6/11
 */
public class GetUserInfoResponse {
    /**
     * 返回码 200-成功  其他失败
     */
    private String code;
    /**
     * 详细信息
     */
    private String msg;

    /**
     * 用户信息
     */
    private CustInfo custInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("msg", msg)
                .append("custInfo", custInfo)
                .toString();
    }

    public CustInfo getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(CustInfo custInfo) {
        this.custInfo = custInfo;
    }
}
