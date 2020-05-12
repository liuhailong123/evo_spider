package cn.com.evo.integration.nxsp.content.ws;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/8/23
 */
public class PublishResponse {
    private String LSPID;

    private String AMSID;

    private String sequence;

    private Integer resultCode;

    private String resultMsg;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("LSPID", LSPID)
                .append("AMSID", AMSID)
                .append("sequence", sequence)
                .append("resultCode", resultCode)
                .append("resuleMsg", resultMsg)
                .toString();
    }

    public PublishResponse(String LSPID, String AMSID, String sequence, Integer resultCode, String resuleMsg) {
        this.LSPID = LSPID;
        this.AMSID = AMSID;
        this.sequence = sequence;
        this.resultCode = resultCode;
        this.resultMsg = resuleMsg;
    }

    public PublishResponse() {

    }

    public String getLSPID() {

        return LSPID;
    }

    public void setLSPID(String LSPID) {
        this.LSPID = LSPID;
    }

    public String getAMSID() {
        return AMSID;
    }

    public void setAMSID(String AMSID) {
        this.AMSID = AMSID;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResuletMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
