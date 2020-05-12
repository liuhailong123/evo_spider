package cn.com.evo.integration.xjTvos.content.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class XinJiangPayRsp implements Serializable {

    private static final long serialVersionUID = 1L;
    private String retCode;
    private String retMsg;

    private String orderNo;
    private String tradeType;
    private String prePayId;
    private String qrcodeUrl;
    private String qrcode;

    public static XinJiangPayRsp getWeiXinRsp(String result) {
        XinJiangPayRsp rsp = new XinJiangPayRsp();
        JSONObject jo = JSONObject.parseObject(result);
        rsp.setRetCode(jo.getString("retCode"));
        rsp.setRetMsg(jo.getString("retMsg"));
        if ("SUCCESS".equals(jo.getString("retCode"))) {
            JSONObject rows = jo.getJSONObject("rows");
            rsp.setOrderNo(rows.getString("orderNo"));
            rsp.setTradeType(rows.getString("tradeType"));
            rsp.setPrePayId(rows.getString("prePayId"));
            rsp.setQrcodeUrl(rows.getString("qrcodeUrl"));
            rsp.setQrcode(rows.getString("qrcode"));
        }
        return rsp;
    }


    public static XinJiangPayRsp getAliRsp(String result) {
        XinJiangPayRsp rsp = new XinJiangPayRsp();
        JSONObject jo = JSONObject.parseObject(result);
        rsp.setRetCode(jo.getString("retCode"));
        rsp.setRetMsg(jo.getString("retMsg"));
        if ("0".equals(jo.getString("retCode"))) {
            JSONObject rows = jo.getJSONObject("rows");
            rsp.setOrderNo(rows.getString("orderNo"));
            rsp.setQrcodeUrl(rows.getString("qrcodeUrl"));
            rsp.setQrcode(rows.getString("qrcode"));
        }
        return rsp;
    }

    public static XinJiangPayRsp getBossRsp(String result) {
        XinJiangPayRsp rsp = new XinJiangPayRsp();
        JSONObject jo = JSONObject.parseObject(result);
        rsp.setRetCode(jo.getString("retCode"));
        rsp.setRetMsg(jo.getString("retMsg"));
        return rsp;
    }

    public static XinJiangPayRsp getSpRsp(String result) {
        XinJiangPayRsp rsp = new XinJiangPayRsp();
        JSONObject jo = JSONObject.parseObject(result);
        rsp.setRetCode(jo.getString("retCode"));
        rsp.setRetMsg(jo.getString("retMsg"));
        return rsp;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrePayId() {
        return prePayId;
    }

    public void setPrePayId(String prePayId) {
        this.prePayId = prePayId;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }


    @Override
    public String toString() {
        return "XinJiangPayRsp [retCode=" + retCode + ", retMsg=" + retMsg + ", orderNo=" + orderNo + ", tradeType="
                + tradeType + ", prePayId=" + prePayId + ", qrcodeUrl=" + qrcodeUrl + ", qrcode=" + qrcode + "]";
    }

}
