package cn.com.evo.integration.shenzheng.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/31
 */
public class AssetResponse {
    private String ret;

    private String code;

    private String reason;

    private String debug;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ret", ret)
                .append("code", code)
                .append("reason", reason)
                .append("debug", debug)
                .toString();
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }
}
