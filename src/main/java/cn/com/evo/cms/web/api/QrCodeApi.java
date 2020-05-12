package cn.com.evo.cms.web.api;

import cn.com.evo.cms.constant.Constant;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.utils.QrCodeUtils;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.qinghai.common.ConstantEnum;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 获取二维码接口类
 */
@Controller
@RequestMapping("/api/qrCode")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QrCodeApi extends BaseController {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserServerService userServerService;



    /**
     * 获取设备绑定二维码
     *
     * @param cardNo
     * @param productCode
     * @param equipmentName
     * @param equipmentId
     * @param width
     * @param height
     * @return
     */
    @RequestMapping(value = "/equipmentBinding", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI equipmentBinding(@RequestParam("cardNo") String cardNo,
                                             @RequestParam("productCode") String productCode,
                                             @RequestParam("equipmentName") String equipmentName,
                                             @RequestParam("equipmentId") String equipmentId,
                                             @RequestParam("width") Integer width,
                                             @RequestParam("height") Integer height,
                                             @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
            if (userAccount != null) {
                String code = "002";
                //账号类型 1 卡号 2 手机号
                Integer accountType = 1;
                //账号
                String accountNo = cardNo;
                //设备类型 1 ott机顶盒 2 sp机顶盒 3 手机
                Integer equipmentType = 2;
                //设备名称
                String name = equipmentName;
                //设备标识
                String id = equipmentId;
                //渠道编码 1 互联网 2 广电 3 电信
                String channelCode = "2";
                Product product = productService.getByCode(productCode);
                //三方产品编码
                String tripartiteProductCode = product.getThirdPartyCode();
                //获取服务到期时间
                String maturityTime = userServerService.getMaturityTimeByUserIdAndAppId(userAccount.getUserId(), appId);
                String qrCodeUrl = Constant.WXxcxPath
                        + "bind?accountType=" + accountType
                        + "&accountNo=" + accountNo
                        + "&equipmentType=" + equipmentType
                        + "&equipmentName=" + name
                        + "&equipmentId=" + id
                        + "&channelCode=" + channelCode
                        + "&productCode=" + tripartiteProductCode
                        + "&maturityTime=" + maturityTime
                        + "&code=002";
                String qrCode = QrCodeUtils.base64EncodeForQR(qrCodeUrl, width, height);
                result.put("qrCode", qrCode);
                result.put("qrCodeUrl", qrCodeUrl);
                dataRet.setData(result);
                dataRet.pushOk("成功！");
            } else {
                dataRet.pushError("卡号不存在");
            }
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取设备绑定二维码时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 扫码播放二维码
     * 青海视开 项目使用
     *
     * @param contentId 内容id
     * @param number    集数，电影传1
     * @param width     二维码宽
     * @param height    二维码高
     * @param cardNo    智能卡号
     * @param appId     应用id
     * @return
     */
    @RequestMapping(value = "/play", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI play(@RequestParam("contentId") String contentId,
                                 @RequestParam("number") Integer number,
                                 @RequestParam("width") Integer width,
                                 @RequestParam("height") Integer height,
                                 @RequestParam("cardNo") String cardNo,
                                 @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            String url = ConstantFactory.map.get(ConstantEnum.web_play_url.getKey());
            String qrCodeUrl = url + "?" + "contentId=" + contentId + "&number=" + number + "&appId=" + appId + "&cardNo=" + cardNo;
            String qrCode = QrCodeUtils.base64EncodeForQR(qrCodeUrl, width, height);

            result.put("qrCode", qrCode);
            result.put("qrCodeUrl", qrCodeUrl);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            logger.error("获取扫码播放二维码异常:" + e.getMessage(), e);
            dataRet.pushError("获取扫码播放二维码异常:" + e.getMessage());
        }
        return dataRet;
    }
}
