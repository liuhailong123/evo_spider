package cn.com.evo.integration.longjiang.common;

/**
 * @Author ZhangLiJie
 * @Description 龙江接口配置类
 * @Date: Created in 16:12 26/3/18
 * @Modified By:
 */
public class Config {
    /**
     * 是否开发环境
     */
    public static boolean isDev = false;

    /**
     * 获取域名
     *
     * @return
     */
    public static String getVoiceSyn() {
        String host;
        if (isDev) {
            //测试语音数据同步接口地址
            host = "http://10.177.5.114:2017";
        } else {
            host = "http://10.177.5.40:2017";
        }
        return host;
    }

    /**
     * 获取域名
     *
     * @return
     */
    public static String getHost() {
        String host;
        if (isDev) {
            host = "http://10.177.4.79:8080";
        } else {
            host = "http://10.177.4.79:8080";
        }
        return host;
    }

    /**
     * 业务端生成二维码支付接口回调地址
     */
    public static String notify_url = "http://10.177.3.31:8081/cmstest/api/app/productApi/payNotify";

    /**
     * 业务端生成二维码支付接口apk回调地址
     */
    public static String notifyApk_url = "";

    /**
     * 获取用户信息接口地址
     */
    public static String userInfo_url = getHost() + "/LJWL_BOSSINTER_SP/service/QC/queryCustomerInfo";

    /**
     * 可订购产品信息查询接口
     */
    public static String queryCanOrderProducts_url = getHost() + "/LJWL_BOSSINTER_SP/service/QC/queryCanOrderProducts";

    /**
     * 查询产品订购价格接口
     */
    public static String queryProductPrice_url = getHost() + "/LJWL_BOSSINTER_SP/service/QC/queryProductPrice";

    /**
     * 订购产品接口
     */
    public static String productOrderSp_url = getHost() + "/LJWL_BOSSINTER_SP/service/QC/productOrderForSp";

    /**
     * 按次扣费接口
     */
    public static String deductFeeForVodDemand_url = getHost() + "/LJWL_BOSSINTER_SP/service/QC/deductFeeForVodDemand";

    /**
     * 支付宝扫码支付
     */
    public static String webPayAction_url = getHost() + "/LJWL_PAYMENT/webPayAction.action";

    /**
     * 微信扫码支付
     */
    public static String wxPayAction_url = getHost() + "/LJWL_PAYMENT/wxPayAction.action";

    /**
     * 手机web快捷支付接口
     */
    public static String phoneWepPayAction_url = getHost() + "/LJWL_PAYMENT/phoneWepPayAction.action";

    /**
     * 微信公众号支付
     */
    public static String wxPay_url = getHost() + "/LJWL_PAYMENT/service/WX/pay";

    /**
     * 业务端生成二维码支付接口
     */
    public static String servicePay_url = "http://10.177.4.41/LJWL_PAYMENT/service/pay";

    /**
     * 银联网关支付
     */
    public static String unionPay_url = getHost() + "/LJWL_PAYMENT/unionPay";

    /**
     * 支付宝网关支付
     */
    public static String pcPayActionPay_url = getHost() + "/LJWL_PAYMENT/pcPayAction.action";

    /**
     * 语音搜索数据同步服务接口地址
     */
    public static String voiceSynUrl = getVoiceSyn() + "/vod";
}
