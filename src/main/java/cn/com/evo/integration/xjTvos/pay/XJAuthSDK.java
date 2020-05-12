package cn.com.evo.integration.xjTvos.pay;

import cn.com.evo.cms.utils.HttpUtil;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.jinanyouxian.common.ConstantEnum;
import cn.com.evo.integration.jinanyouxian.sdk.dto.GetAuthorityInfoResponse;
import cn.com.evo.integration.xjTvos.common.XJConstantEnum;
import cn.com.evo.integration.xjTvos.pay.model.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author rf
 * @date 2019/5/22
 */
public class XJAuthSDK {
    protected static Logger logger = LogManager.getLogger(XJAuthSDK.class);

    /**
     * 鉴权
     *
     * @param accesstoken 用户令牌
     * @return boolean ture-鉴权通过， false-鉴权不通过
     */
    public static String getAuthorityInfo(String accesstoken, String videoId) {
        try {
            String url = ConstantFactory.map.get(ConstantEnum.get_authority_url.getKey()) +
                    "?accesstoken=" + accesstoken + "&programid=" + videoId + "&playtype=demand";
            // 调用局方接口
            String resultStr = HttpUtil.get(url, "调用局方接口获取视频鉴权信息");
            GetAuthorityInfoResponse response = JSONObject.parseObject(resultStr, GetAuthorityInfoResponse.class);
            if (response.getRet() == 0) {
                //接口调用成功
                String playUrl = "http://=" + accesstoken + "&programid=" + videoId + "&playtype=demand&playtoken=" + response.getPlay_token() + "&protocol=http&auth=no";
                logger.error(playUrl);
                return playUrl;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口获取视频鉴权信息异常：" + e.getMessage(), e);
        }
    }

    public static ProductInfoResponse getProductInfo(String productType, String regionId, String channelType, String carfNo, String productId){
        try {
            String url = ConstantFactory.map.get(ConstantEnum.get_product_info.getKey()) +
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
            String url = ConstantFactory.map.get(XJConstantEnum.get_info.getKey()) +
                    "?accesstoken=" + accesstoken + "&videoid=" + videoId;
            String resultStr = HttpUtil.get(url, "调用茁壮接口获取视频定价");
            GetInfoResponse response = JSONObject.parseObject(resultStr, GetInfoResponse.class);
            if (response.getRet() == 0) {
                //接口调用成功
                String playUrl = "http://宁夏的域名地址/playurl?playtype=demand&protocol=http&accesstoken=" + accesstoken +
                        "&programid=" + videoId + "&playtoken=" + response.getPlay_token() + "&protocol=http&auth=no";
                logger.error(playUrl);
                return playUrl;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("调用茁壮接口获取视频鉴权异常：" + e.getMessage(), e);
        }
    }

    /**
     * 询价接口
     * @param
     * @return
     */
    public static GetPriceInfoResponse getPriceInfo(String accesstoken, String videoId){
        try {
            String url = ConstantFactory.map.get(XJConstantEnum.get_price_info.getKey()) + "?accesstoken=" +
                    accesstoken + "&programid=" + videoId + "&programtype=2";
            String resultStr = HttpUtil.get(url, "调用茁壮接口获取节目价格接口");

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
            String url = ConstantFactory.map.get(XJConstantEnum.create_order.getKey());
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
            String url = ConstantFactory.map.get(XJConstantEnum.pay.getKey());
            String resultStr = HttpUtil.post(url, JSONObject.parseObject(pay.toString()), "调用茁壮接口获取视频定价");
            return JSONObject.parseObject(resultStr);
        } catch (Exception e) {
            throw new RuntimeException("调用茁壮接口获取视频定价异常：" + e.getMessage(), e);
        }
    }

}
