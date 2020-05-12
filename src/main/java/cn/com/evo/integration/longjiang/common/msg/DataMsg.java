package cn.com.evo.integration.longjiang.common.msg;

/**
 * @Author ZhangLiJie
 * @Description //数据返回的统一实体
 * @Date: Created in 15:44 26/3/18
 * @Modified By:
 */
public class DataMsg {

    /**
     * 状态码
     */
    private String returnCode;
    /**
     * 状态信息
     */
    private String returnMsg;
    /**
     * 返回数据集合
     */
    private Object returnData;
    /**
     * 数据
     */
    private Integer rowTotal;

    public DataMsg() {

    }

    public DataMsg(String returnCode, String returnMsg, Object returnData) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.returnData = returnData;
    }

    public DataMsg(String returnCode, String returnMsg, Object returnData, Integer rowTotal) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.returnData = returnData;
        this.rowTotal = rowTotal;
    }

    /**
     * 错误返回
     *
     * @param errorMsg
     * @param returnData
     * @return
     */
    public static DataMsg pushError(String errorMsg, Object... returnData) {
        DataMsg dataMsg = new DataMsg(DataMsgCode.ERROR.toString(), errorMsg, returnData);
        return dataMsg;
    }

    /**
     * 成功返回
     *
     * @param successMsg
     * @param returnData
     * @return
     */
    public static DataMsg pushOK(String successMsg, Object returnData) {
        DataMsg dataMsg = new DataMsg(DataMsgCode.SUCCESS.toString(), successMsg, returnData);
        return dataMsg;
    }

    /**
     * 超时返回
     *
     * @param errorMsg
     * @param returnData
     * @return
     */
    public static DataMsg pushTimeOut(String errorMsg, Object... returnData) {
        DataMsg dataMsg = new DataMsg(DataMsgCode.TIME_OUT.toString(), errorMsg, returnData);
        return dataMsg;
    }

    /**
     * 失败返回
     *
     * @param failMsg
     * @param returnData
     * @return
     */
    public static DataMsg pushFail(String failMsg, Object... returnData) {
        DataMsg dataMsg = new DataMsg(DataMsgCode.FAIL.toString(), failMsg, returnData, 0);
        return dataMsg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    public Integer getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(Integer rowTotal) {
        this.rowTotal = rowTotal;
    }

    @Override
    public String toString() {
        return "DataMsg{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", returnData=" + returnData +
                ", rowTotal=" + rowTotal +
                '}';
    }
}
