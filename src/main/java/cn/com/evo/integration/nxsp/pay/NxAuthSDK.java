package cn.com.evo.integration.nxsp.pay;

import cn.com.evo.cms.utils.HttpUtil;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.nxsp.common.NxConstantEnum;
import cn.com.evo.integration.nxsp.pay.model.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

/**
 * @author rf
 * @date 2019/5/22
 */
public class NxAuthSDK {
    protected static Logger logger = LogManager.getLogger(NxAuthSDK.class);

    /**
     * 调用局方接口套餐鉴权信息
     *
     * @param accesstoken 用户令牌
     * @return boolean ture-鉴权通过， false-鉴权不通过
     */
    public static GetAuthorityInfoResponse getAuthorityInfo(String accesstoken, String videoId) {
        try {
            String url = ConstantFactory.map.get(NxConstantEnum.get_authority_url.getKey()) +
                    "?accesstoken=" + accesstoken + "&packageid=" + videoId;
            // 调用局方接口
            String resultStr = HttpUtil.get(url, "调用局方接口套餐鉴权信息");
            System.out.println(resultStr);
            return JSONObject.parseObject(resultStr, GetAuthorityInfoResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口获取视频鉴权信息异常：" + e.getMessage(), e);
        }
    }

    public static ProductInfoResponse getProductInfo(String productType, String regionId, String channelType, String carfNo, String productId){
        try {
            String url = ConstantFactory.map.get(NxConstantEnum.get_product_info.getKey()) +
                    "?productType=" + productType + "&regionId=" + regionId + "&channelType=" + channelType + "&carfNo=" + carfNo
                    + "&productId=" + productId;
            String resultStr = HttpUtil.get(url, "调用局方获取套餐信息接口");
            ProductInfoResponse response = JSONObject.parseObject(resultStr, ProductInfoResponse.class);
            if(response.getCode() == 200){
                return response;
            }else{
                throw new RuntimeException("请求局方获取套餐接口失败，异常：" + response);
            }
        } catch (Exception e){
            throw new RuntimeException("请求局方获取套餐接口失败，异常：" + e.getMessage(), e);
        }
    }

    /**
     * 获取视频信息
     * @param accesstoken
     * @param videoId
     * @return
     */
    public static String getInfo(String accesstoken, String videoId){
        try {
            String url = ConstantFactory.map.get(NxConstantEnum.get_info.getKey()) +
                    "?accesstoken=" + accesstoken + "&videoid=" + videoId;
            String resultStr = HttpUtil.get(url, "调用茁壮接口获取视频定价");
            GetInfoResponse response = JSONObject.parseObject(resultStr, GetInfoResponse.class);
            System.out.println(response);
            if (response.getRet() == 0) {
                if(response.getIs_purchased() == 1){
                    String[] demand_url = response.getDemand_url();
                    //接口调用成功
                    String playUrl = demand_url[0] + "?playtype=demand&protocol=http&accesstoken=" + accesstoken +
                            "&programid=" + videoId + "&playtoken=" + response.getPlay_token() + "&auth=no";
                    logger.error(playUrl);
                    return playUrl;
                } else {
                    return "调用茁壮接口获取视频鉴权异常";
                }
            } else {
                return "调用茁壮接口获取视频鉴权异常";
            }
        } catch (Exception e) {
            return "调用茁壮接口获取视频鉴权异常";
        }
    }

    /**
     * 询价接口
     * @param
     * @return
     */
    public static GetPriceInfoResponse getPriceInfo(String accesstoken, String videoId){
        try {
            String url = ConstantFactory.map.get(NxConstantEnum.get_price_info.getKey()) + "?accesstoken=" +
                    accesstoken + "&programid=" + videoId + "&programtype=2";
            String resultStr = HttpUtil.get(url, "调用茁壮接口获取节目价格接口");
            System.out.println(resultStr);
            return JSONObject.parseObject(resultStr, GetPriceInfoResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("调用茁壮接口获取节目价格接口异常：" + e.getMessage(), e);
        }
    }

    /**
     * 创建订单
     * @param createOrder
     */
    public static CreateOrderResponse createOrder(CreateOrder createOrder) {
        try {
            String url = ConstantFactory.map.get(NxConstantEnum.create_order.getKey());
            String resultStr = HttpUtil.post(url, JSONObject.parseObject(createOrder.toString()), "调用茁壮接口创建接口");
            return JSONObject.parseObject(resultStr, CreateOrderResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("调用茁壮接口获取节目价格接口异常：" + e.getMessage(), e);
        }
    }

    /**
     * 支付
     * @param pay
     * @return
     */
    public static JSONObject pay(NxPay pay){
        try {
            String url = ConstantFactory.map.get(NxConstantEnum.pay.getKey());
            String resultStr = HttpUtil.post(url, JSONObject.parseObject(pay.toString()), "调用茁壮接口获取视频定价");
            return JSONObject.parseObject(resultStr);
        } catch (Exception e) {
            throw new RuntimeException("调用茁壮接口获取视频定价异常：" + e.getMessage(), e);
        }
    }

}
