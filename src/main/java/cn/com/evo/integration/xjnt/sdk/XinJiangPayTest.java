package cn.com.evo.integration.xjnt.sdk;

import cn.com.evo.integration.xjnt.sdk.dto.XinJiangPayReq;
import cn.com.evo.integration.xjnt.sdk.dto.XinJiangPayRsp;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class XinJiangPayTest {
    public static void main(String[] args) {
//		weixinPay();//微信
//		aliPay();//支付宝
//        bossPay();//BOSS电视互动产品订购
//        spPay();//增值业务产品订购（SP）
        isOrder();//查询产品是否已订购
    }

    private static void isOrder() {
        try {

            String url = "http://iocpay.xj96566.com/ioc/api/system/product/isOrder";
            String authCode = "A59E50CDC6120E7E848F1C67EBD4C5B2";// 应用授权编码
            String cardNo = "1049701559";// 智能卡号
            String prodCode = "100282";// 产品编码
            JSONObject content = new JSONObject();
            content.put("authCode", authCode);
            content.put("cardNo", cardNo);
            content.put("prodCode", prodCode);
            post(url, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void spPay() {
        try {
            String authCode = "A59E50CDC6120E7E848F1C67EBD4C5B2";// 应用授权编码
            String cardNo = "4264678666";// 智能卡号
            String stbNo = "08A910E01FFFF30FFFF0002431008888";// 机顶盒号
            String stbIp = "0.0.0.1";// 机顶盒ip
            String prodCode = "100154";// 产品编码
            String contentCode = "GY0498";// 产品内容编码
            String mobile = "13095051514";
            XinJiangPayReq req = new XinJiangPayReq(authCode, cardNo, stbNo, stbIp, 0, prodCode, contentCode, "", mobile);
            PayUtilXinJiang.spPay(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bossPay() {
        try {
            String authCode = "A59E50CDC6120E7E848F1C67EBD4C5B2";// 应用授权编码
            String cardNo = "4264678666";// 智能卡号
            String stbNo = "08A910E01FFFF30FFFF0002431008888";// 机顶盒号
            String prodCode = "100154";// 产品编码
            String mobile = "13095051514";
            XinJiangPayReq req = new XinJiangPayReq(authCode, cardNo, stbNo, "", 0, prodCode, "", "", mobile);
            PayUtilXinJiang.bossPay(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void aliPay() {
        try {
            String authCode = "A59E50CDC6120E7E848F1C67EBD4C5B2";// 应用授权编码
            String cardNo = "4264678666";// 智能卡号
            String stbNo = "08A910E01FFFF30FFFF0002431008888";// 机顶盒号
            String stbIp = "0.0.0.1";// 机顶盒ip
            Integer bookId = 12;// 账本编号
            String prodCode = "100154";// 产品编码
            String contentCode = "GY0498";// 产品内容编码
            String backUrl = "http://10.26.100.69:8080/vasp/api/tvCinema/iocToNewCinemaNotify";// 结果通知地址
            XinJiangPayReq req = new XinJiangPayReq(authCode, cardNo, stbNo, stbIp, bookId, prodCode, contentCode,
                    backUrl, "");
            XinJiangPayRsp rsp = PayUtilXinJiang.aliPayQrcode(req);
            System.out.println(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void weixinPay() {
        try {
            String authCode = "A59E50CDC6120E7E848F1C67EBD4C5B2";// 应用授权编码
            String cardNo = "4264678666";// 智能卡号
            String stbNo = "08A910E01FFFF30FFFF0002431008888";// 机顶盒号
            String stbIp = "0.0.0.1";// 机顶盒ip
            Integer bookId = 12;// 账本编号
            String prodCode = "100154";// 产品编码
            String contentCode = "GY0498";// 产品内容编码
            String backUrl = "http://10.26.100.69:8080/vasp/api/tvCinema/iocToNewCinemaNotify";// 结果通知地址
            XinJiangPayReq req = new XinJiangPayReq(authCode, cardNo, stbNo, stbIp, bookId, prodCode, contentCode,
                    backUrl, "");
            XinJiangPayRsp rsp = PayUtilXinJiang.weixinPayQrcode(req);
            System.out.println(rsp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
