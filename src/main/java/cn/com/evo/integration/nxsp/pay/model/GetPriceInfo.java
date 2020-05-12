package cn.com.evo.integration.nxsp.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/5/23
 */
public class GetPriceInfo {
    private String accesstoken;

    private String programid;

    private String programtype;

    private String businessplatform;

    private String apiVersion = "v2";

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accesstoken", accesstoken)
                .append("programid", programid)
                .append("programtype", programtype)
                .append("businessplatform", businessplatform)
                .append("apiVersion", apiVersion)
                .toString();
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public String getProgramtype() {
        return programtype;
    }

    public void setProgramtype(String programtype) {
        this.programtype = programtype;
    }

    public String getBusinessplatform() {
        return businessplatform;
    }

    public void setBusinessplatform(String businessplatform) {
        this.businessplatform = businessplatform;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}
