package cn.com.evo.integration.nxsp.content.ws;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author rf
 * @date 2019/8/23
 */
public class QueryStateResponse {
    private String LSPID;

    private String AMSID;

    private String sequence;

    private String playId;

    private Integer stateCode;

    private String stateMsg;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("LSPID", LSPID)
                .append("AMSID", AMSID)
                .append("sequence", sequence)
                .append("playId", playId)
                .append("stateCode", stateCode)
                .append("stateMsg", stateMsg)
                .toString();
    }

    public QueryStateResponse(String LSPID, String AMSID, String sequence, String playId, Integer stateCode, String stateMsg) {
        this.LSPID = LSPID;
        this.AMSID = AMSID;
        this.sequence = sequence;
        this.playId = playId;
        this.stateCode = stateCode;
        this.stateMsg = stateMsg;
    }

    public QueryStateResponse() {

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

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateMsg() {
        return stateMsg;
    }

    public void setStateMsg(String stateMsg) {
        this.stateMsg = stateMsg;
    }
}
