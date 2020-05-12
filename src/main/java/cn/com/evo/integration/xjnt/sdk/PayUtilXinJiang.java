package cn.com.evo.integration.xjnt.sdk;

import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.xjnt.common.ConstantEnum;
import cn.com.evo.integration.xjnt.sdk.dto.XinJiangBossIsOrderRsp;
import cn.com.evo.integration.xjnt.sdk.dto.XinJiangPayReq;
import cn.com.evo.integration.xjnt.sdk.dto.XinJiangPayRsp;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * 新疆广电支付接口工具类
 */
public class PayUtilXinJiang {

    public static XinJiangPayRsp weixinPayQrcode(XinJiangPayReq req) {
        XinJiangPayRsp rsp;
        try {
            rsp = new XinJiangPayRsp();
            String url = ConstantFactory.map.get(ConstantEnum.weixinUrl.getKey());

            JSONObject content = new JSONObject();
            content.put("authCode", req.getAuthCode());
            content.put("cardNo", req.getCardNo());
            content.put("stbNo", req.getStbNo());
            content.put("stbIp", req.getStbIp());
            content.put("bookId", req.getBookId());
            content.put("prodCode", req.getProdCode());
            content.put("contentCode", req.getContentCode());
            content.put("backUrl", req.getBackUrl());
            String result = post(url, content);
            if (!"".equals(result) && result != null) {
                rsp = XinJiangPayRsp.getWeiXinRsp(result);
            } else {
                rsp.setRetCode("-1");
                rsp.setRetMsg("接口异常");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rsp;
    }

    public static XinJiangPayRsp aliPayQrcode(XinJiangPayReq req) {
        XinJiangPayRsp rsp;
        try {
            rsp = new XinJiangPayRsp();
            String url = ConstantFactory.map.get(ConstantEnum.alipayUrl.getKey());

            JSONObject content = new JSONObject();
            content.put("authCode", req.getAuthCode());
            content.put("cardNo", req.getCardNo());
            content.put("stbNo", req.getStbNo());
            content.put("stbIp", req.getStbIp());
            content.put("bookId", req.getBookId());
            content.put("prodCode", req.getProdCode());
            content.put("contentCode", req.getContentCode());
            content.put("backUrl", req.getBackUrl());
            String result = post(url, content);
            if (!"".equals(result) && result != null) {
                rsp = XinJiangPayRsp.getAliRsp(result);
            } else {
                rsp.setRetCode("-1");
                rsp.setRetMsg("接口异常");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rsp;
    }

    public static XinJiangPayRsp bossPay(XinJiangPayReq req) {
        XinJiangPayRsp rsp;
        try {
            rsp = new XinJiangPayRsp();
            String url = ConstantFactory.map.get(ConstantEnum.bossUrl.getKey());

            JSONObject content = new JSONObject();
            content.put("authCode", req.getAuthCode());
            content.put("cardNo", req.getCardNo());
            content.put("stbNo", req.getStbNo());
            content.put("prodCode", req.getProdCode());
            content.put("mobile", req.getMobile());
            String result = post(url, content);
            if (!"".equals(result) && result != null) {
                rsp = XinJiangPayRsp.getBossRsp(result);
            } else {
                rsp.setRetCode("-1");
                rsp.setRetMsg("接口异常");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rsp;
    }

    public static XinJiangPayRsp spPay(XinJiangPayReq req) {
        XinJiangPayRsp rsp;
        try {
            rsp = new XinJiangPayRsp();
            String url = ConstantFactory.map.get(ConstantEnum.spUrl.getKey());

            JSONObject content = new JSONObject();
            content.put("authCode", req.getAuthCode());
            content.put("cardNo", req.getCardNo());
            content.put("stbNo", req.getStbNo());
            content.put("stbIp", req.getStbIp());
            content.put("prodCode", req.getProdCode());
            content.put("contentCode", req.getContentCode());
            content.put("mobile", req.getMobile());
            String result = post(url, content);
            if (!"".equals(result) && result != null) {
                rsp = XinJiangPayRsp.getSpRsp(result);
            } else {
                rsp.setRetCode("-1");
                rsp.setRetMsg("接口异常");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rsp;
    }

    public static XinJiangBossIsOrderRsp queryIsOrdered(String cardNo, String prodCode) {
        XinJiangBossIsOrderRsp rsp;
        try {
            rsp = new XinJiangBossIsOrderRsp();
            String url = ConstantFactory.map.get(ConstantEnum.queryOrderInfoUrl.getKey());

            JSONObject content = new JSONObject();
            content.put("cardNo", cardNo);
            content.put("prodCode", prodCode);
            content.put("authCode", ConstantFactory.map.get(ConstantEnum.authCode.getKey()));
            System.out.println("查询boss产品是否订购接口请求参数:" + content.toJSONString());

            String result = post(url, content);
            if (!"".equals(result) && result != null) {
                rsp = XinJiangBossIsOrderRsp.getQueryOrder(result);
            } else {
                rsp.setRetCode("-1");
                rsp.setRetMsg("接口异常");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rsp;
    }

    public static XinJiangBossIsOrderRsp queryIsOrdered(String cardNo, String stbNo, String stbIp, String ppvId) {
        XinJiangBossIsOrderRsp rsp;
        try {
            rsp = new XinJiangBossIsOrderRsp();
            String url = ConstantFactory.map.get(ConstantEnum.queryIsOrderedUrl.getKey());

            JSONObject content = new JSONObject();
            content.put("cardNo", cardNo);
            content.put("stbNo", stbNo);
            content.put("stbIp", stbIp);
            content.put("ppvId", ppvId);
            content.put("authCode", ConstantFactory.map.get(ConstantEnum.authCode.getKey()));
            System.out.println("查询boss产品是否订购4.14接口请求参数:" + content.toJSONString());


            String result = post(url, content);
            if (!"".equals(result) && result != null) {
                rsp = XinJiangBossIsOrderRsp.getQueryOrder(result);
            } else {
                rsp.setRetCode("-1");
                rsp.setRetMsg("接口异常");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rsp;
    }

    private static String post(String url, JSONObject content) {
        String result = "";
        try {
            HttpClient client = HttpClients.custom().build();
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity("params=" + content.toJSONString(), "UTF-8");
            entity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(entity);
            HttpResponse resp = client.execute(post);
            result = EntityUtils.toString(resp.getEntity(), "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        String cardNo = "1054723030";
        String prodCode = "100347";
        queryIsOrdered(cardNo, prodCode);
    }
}
