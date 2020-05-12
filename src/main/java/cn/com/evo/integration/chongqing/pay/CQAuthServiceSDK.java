package cn.com.evo.integration.chongqing.pay;

import cn.com.evo.cms.utils.HttpUtil;
import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.chongqing.pay.model.CA;
import cn.com.evo.integration.chongqing.pay.model.CustInfo;
import cn.com.evo.integration.chongqing.pay.model.GetUserInfoResponse;
import cn.com.evo.integration.chongqing.pay.model.ProductOrder;
import cn.com.evo.integration.common.ConstantFactory;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author rf
 * @date 2019/6/11
 */
public class CQAuthServiceSDK {
    protected static Logger logger = LogManager.getLogger(CQAuthServiceSDK.class);

    /**
     * 查询终端用户信息。用作鉴权
     * @param card 智能卡号or MAC or 账户号
     * @param cardType 智能卡号:I or MAC:M or 客户号:C or 仅查询当前用户:U
     */
    public static GetUserInfoResponse auth(String card, String cardType){
        try {
            String url = ConstantFactory.map.get(CQConstantEnum.get_user_info.getKey()) + "?card=" +
                    card + "&cardType=" + cardType;
            // 调用局方接口
            String resultStr = HttpUtil.get(url, "调用局方接口获取视频鉴权信息");
            GetUserInfoResponse getUserInfoResponse = JSONObject.parseObject(resultStr, GetUserInfoResponse.class);
            System.out.println(getUserInfoResponse);
            return getUserInfoResponse;
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口获取视频鉴权信息异常：" + e.getMessage(), e);
        }
    }

//    public static void main(String[] args) {
//        String str = "{\"code\":\"200\",\"custInfo\":{\"accountId\":\"358033367\",\"address\":\"渝北区_北部旗龙路67号安全指挥中心综合楼1单元10-8\",\"balanceList\":[{\"balance\":\"861.28\",\"balanceId\":\"1021\",\"balanceName\":\"时间点业务\"},{\"balance\":\"0.00\",\"balanceId\":\"1002\",\"balanceName\":\"承诺性基本业务\"},{\"balance\":\"0.00\",\"balanceId\":\"0\",\"balanceName\":\"通用\"},{\"balance\":\"0.0\",\"balanceId\":\"1005\",\"balanceName\":\"互动基本业务\"}],\"custCode\":\"\",\"custId\":\"8941448\",\"custName\":\"北京玖扬博文文化发展有限公司\",\"mobile\":\"15196603789\",\"oweTotalFee\":\"22.72\",\"ownCorpOrg\":\"291\",\"phone\":\"010-88115906\",\"totleBalance\":\"861.28\",\"userList\":[{\"caId\":\"9950000002832715\",\"caModel\":\"SD\",\"caType\":\"1\",\"isMain\":1,\"prodInstId\":\"41888242\",\"productOrderList\":[{\"lineOrPoint\":\"point\",\"offerId\":\"800520180199\",\"offerInstId\":\"77283144\",\"offerName\":\"云游戏30元\\/月(首月前7天免费)\",\"productLs\":[{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800620160130\",\"productName\":\"云游戏开通产品\",\"status\":\"1\",\"validDate\":\"2019-08-02 11:30:01\"},{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800622003438\",\"productName\":\"云游戏30元\\/月按天扣费计费产品\",\"status\":\"1\",\"validDate\":\"2019-08-02 11:30:01\"}]},{\"lineOrPoint\":\"line\",\"offerId\":\"800520140469\",\"offerInstId\":\"71562841\",\"offerName\":\"直播78(主机)25元\\/月套餐\",\"productLs\":[{\"expireDate\":\"2099-12-31 00:00:00\",\"productId\":\"800620140542\",\"productName\":\"直播78(主机)25元\\/月产品\",\"status\":\"1\",\"validDate\":\"2018-08-29 10:40:13\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800520180024\",\"offerInstId\":\"76804092\",\"offerName\":\"优生活1个月15元(促销)\",\"productLs\":[{\"expireDate\":\"2019-08-09 14:47:28\",\"productId\":\"800622003088\",\"productName\":\"优生活1个月15元(促销)计费产品\",\"status\":\"0\",\"validDate\":\"2019-07-09 14:47:28\"},{\"expireDate\":\"2019-08-09 14:47:28\",\"productId\":\"800622003252\",\"productName\":\"优生活1个月产品\",\"status\":\"0\",\"validDate\":\"2019-07-09 14:47:28\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800520160178\",\"offerInstId\":\"77012620\",\"offerName\":\"掌世界电竞20元\\/月\",\"productLs\":[{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800622001142\",\"productName\":\"掌世界电竞开通产品\",\"status\":\"1\",\"validDate\":\"2019-07-23 15:21:15\"},{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800622001141\",\"productName\":\"掌世界电竞20元\\/月按月扣费套餐计费产品\",\"status\":\"1\",\"validDate\":\"2019-07-23 15:21:15\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800521000201\",\"offerInstId\":\"77275875\",\"offerName\":\"嘟嘟玩具1个月25元\",\"productLs\":[{\"expireDate\":\"2019-09-01 18:55:30\",\"productId\":\"800622005036\",\"productName\":\"嘟嘟玩具(1个月)\",\"status\":\"0\",\"validDate\":\"2019-08-01 18:55:30\"},{\"expireDate\":\"2019-09-01 18:55:30\",\"productId\":\"800693000146\",\"productName\":\"嘟嘟玩具1个月25元25.0元\\/月计费产品\",\"status\":\"0\",\"validDate\":\"2019-08-01 18:55:30\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800520160103\",\"offerInstId\":\"76820419\",\"offerName\":\"嗨皮游戏20元\\/月\",\"productLs\":[{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800622000826\",\"productName\":\"掌世界开通产品\",\"status\":\"1\",\"validDate\":\"2019-07-10 15:38:21\"},{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800622001040\",\"productName\":\"嗨皮游戏20元\\/月按月扣费套餐计费产品\",\"status\":\"1\",\"validDate\":\"2019-07-10 15:38:21\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800520170091\",\"offerInstId\":\"76627859\",\"offerName\":\"新东方全业务套餐39元\\/月\",\"productLs\":[{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800622002443\",\"productName\":\"新东方全业务套餐39元\\/月计费产品\",\"status\":\"1\",\"validDate\":\"2019-06-28 17:48:48\"},{\"expireDate\":\"2099-12-31 23:59:59\",\"productId\":\"800622002535\",\"productName\":\"新东方全业务套餐开通产品\",\"status\":\"1\",\"validDate\":\"2019-06-28 17:48:48\"}]}],\"status\":1}]},\"msg\":\"成功\"}";
//        GetUserInfoResponse getUserInfoResponse = JSONObject.parseObject(str, GetUserInfoResponse.class);
//        System.out.println(getUserInfoResponse);
//
//    }

    /**
     * 当产品鉴权失败。调用该接口去进行支付
     * @param qryType
     * @param qryValue
     * @param comboId
     * @param appId
     * @param returnUrl
     */
    public static void pay(String qryType, String qryValue, String comboId, String appId, String returnUrl){
        try {
            String url = ConstantFactory.map.get(CQConstantEnum.pay.getKey()) + "?qryType=" +
                    qryType + "&qryValue=" + qryValue + "&comboId=" + comboId + "&appId=" + appId + "&returnUrl=" + returnUrl;
            // 调用局方接口
            String resultStr = HttpUtil.get(url, "调用局方接口获取视频鉴权信息");
            JSONObject result = JSONObject.parseObject(resultStr);
            if(!"200".equals(result.getString("code"))){
                throw new RuntimeException("调用局方统一订购接口失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("调用局方统一订购接口失败：" + e.getMessage(), e);
        }
    }

    /**
     * 获取套餐状态
     * @param custInfo
     * @return
     */
    public static boolean eqOfferIdAndStatus(CustInfo custInfo){
        boolean flag = false;
        for (CA ca : custInfo.getUserList()) {
            for (ProductOrder productOrder : ca.getProductOrderList()) {
                if (productOrder.getOfferId().equals(ConstantFactory.map.get(CQConstantEnum.product_code.getKey()))){
                    for (cn.com.evo.integration.chongqing.pay.model.Product product : productOrder.getProductLs()) {
                        if("0".equals(product.getStatus())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
