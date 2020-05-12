package cn.com.evo.cms.web.api;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.constant.ProvinceCodeEnum;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.integration.chongqing.pay.CQAuthServiceSDK;
import cn.com.evo.integration.chongqing.pay.model.CustInfo;
import cn.com.evo.integration.chongqing.pay.model.GetUserInfoResponse;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.result.AuthResponse;
import cn.com.evo.integration.jinanyouxian.common.ConstantEnum;
import cn.com.evo.integration.jinanyouxian.sdk.AuthSDK;
import cn.com.evo.integration.nxsp.common.NxConstantEnum;
import cn.com.evo.integration.nxsp.pay.NxAuthSDK;
import cn.com.evo.integration.nxsp.pay.model.GetAuthorityInfoResponse;
import cn.com.evo.integration.xjTvos.XJAuthServiceImpl;
import cn.com.evo.integration.xjnt.XjntAuthServiceImpl;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/check")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CheckApi extends BaseController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRelService productRelService;
    @Autowired
    private UserServerService userServerService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private LimitFreeService limitFreeService;

    @Autowired
    private XjntAuthServiceImpl xjntAuthService;

    @Autowired
    private XJAuthServiceImpl xjAuthService;

    /**
     * 服务鉴权
     *
     * @param cardNo      必填 智能卡号
     * @param serverCode  必填 服务码
     * @param appId       必填 应用id
     * @param stbNo       非必填 机顶盒号 新疆广电必填
     * @param stbIp       非必填 机顶盒ip 新疆广电必填
     * @param accesstoken 非必填 用户登录标识 济南有线必填
     * @return
     */
    @RequestMapping(value = "/serverAuth", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI serverAuth(@RequestParam("cardNo") String cardNo,
                                       @RequestParam("serverCode") String serverCode,
                                       @RequestParam("appId") String appId,
                                       @RequestParam(value = "stbNo", required = false) String stbNo,
                                       @RequestParam(value = "stbIp", required = false) String stbIp,
                                       @RequestParam(value = "accesstoken", required = false) String accesstoken) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            if (StringUtils.isBlank(cardNo)) {
                logger.error("智能卡号为空!!!");
                result.put("serverFlag", true);
                result.put("serverMsg", "已过期-智能卡号为空");
            } else {
                // 应用是否存在限时免费
                boolean isFree = limitFreeService.isFree(appId, appId);
                if (isFree) {
                    // 应用限免，返回限免通过
                    result.put("serverFlag", false);
                    result.put("serverMsg", "未过期-应用已配置限时免费");
                } else {
                    //用户
                    UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
                    if (userAccount != null) {
                        //根据serverCode、userId查询服务开通表，判断是否到期
                        boolean flag = userServerService.checkUserServer(userAccount.getUserId(), serverCode, appId);
                        result.put("serverFlag", flag);
                        if (flag) {
                            result.put("serverFlag", flag);
                            result.put("serverMsg", "已过期");

                            Province province = provinceService.getByEnable(1);
                            if (province == null) {
                                throw new RuntimeException("没有可用的省网配置!!!");
                            }

                            // 省网code枚举
                            ProvinceCodeEnum provinceCodeEnum = ProvinceCodeEnum.getByName(province.getCode());
                            switch (provinceCodeEnum) {
                                case XJTvos:
                                    AuthResponse xjTvosAuthResponse = new AuthResponse();
                                    /**
                                     * 该方法调用SP订购鉴权接口，因该接口返回有问题。故
                                     * 依旧先调用查询Boss订购鉴权接口
                                     */
                                    xjAuthService.queryOfflineOrder2(appId, cardNo, null, xjTvosAuthResponse);
                                    System.out.println("xjTvosAuthResponse" + xjTvosAuthResponse.toString());
                                    if (xjTvosAuthResponse.getRetCode() >= 0) {
                                        flag = false;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "未过期");
                                        break;
                                    } else {
                                        flag = true;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "已过期" + xjTvosAuthResponse.getRetMsg());
                                    }
                                    break;
                                case XinJiang:
                                    AuthResponse authResponse = new AuthResponse();
                                    /**
                                     * 该方法调用SP订购鉴权接口，因该接口返回有问题。故
                                     * 依旧先调用查询Boss订购鉴权接口
                                     */
                                    //xjntAuthService.queryOfflineOrder1(appId, cardNo, stbNo, stbIp, null, authResponse);
                                    xjntAuthService.queryOfflineOrder2(appId, cardNo, null, authResponse);
                                    if (authResponse.getRetCode() >= 0) {
                                        flag = false;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "未过期");
                                        break;
                                    } else {
                                        flag = true;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "已过期" + authResponse.getRetMsg());
                                    }
                                    break;
                                case JinanYouxian:
                                    boolean jnflag = AuthSDK.vdieoAuth(accesstoken, ConstantFactory.map.get(ConstantEnum.video_id.getKey()));
                                    if (jnflag) {
                                        flag = false;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "未过期");
                                        break;
                                    } else {
                                        flag = true;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "已过期");
                                    }
                                    break;
                                case Wasu:
                                    // 栏目配置的BOSS产品列表
                                    List<Product> list2 = productRelService.findByBizIdAndType(appId, 2);
                                    if (list2.size() > 0) {
                                        Boolean flag1 = cn.com.evo.integration.wasu.sdk.AuthSDK.auth(cardNo, list2);
                                        if (flag1) {
                                            flag = false;
                                            result.put("serverFlag", flag);
                                            result.put("serverMsg", "未过期");
                                            break;
                                        } else {
                                            flag = true;
                                            result.put("serverFlag", flag);
                                            result.put("serverMsg", "已过期");
                                        }
                                    } else {
                                        logger.error("应用未配置可用产品");
                                        flag = true;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "已过期-应用未配置可用产品");
                                    }
                                    break;
                                case ChongQing:
                                    GetUserInfoResponse response = CQAuthServiceSDK.auth(cardNo, "U");
                                    if ("200".equals(response.getCode())) {
                                        CustInfo custInfo = response.getCustInfo();
                                        boolean cqflag = CQAuthServiceSDK.eqOfferIdAndStatus(custInfo);
                                        if (cqflag) {
                                            flag = false;
                                            result.put("serverFlag", flag);
                                            result.put("serverMsg", "已开通");
                                            break;
                                        } else {
                                            flag = true;
                                            result.put("serverFlag", flag);
                                            result.put("serverMsg", "未开通");
                                        }
                                    }else {
                                        logger.error("应用未配置可用产品");
                                        flag = true;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "已过期-应用未配置可用产品");
                                    }
                                    break;
                                case NingXia:
                                    GetAuthorityInfoResponse authorityInfo = NxAuthSDK.getAuthorityInfo(accesstoken, ConstantFactory.map.get(NxConstantEnum.PPVId.getKey()));
                                    if ("0".equals(authorityInfo.getRet())) {
                                        if (authorityInfo.getIs_purchased().equals(0)) {
                                            flag = false;
                                            result.put("serverFlag", flag);
                                            result.put("serverMsg", "已开通");
                                            break;
                                        } else {
                                            flag = true;
                                            result.put("serverFlag", flag);
                                            result.put("serverMsg", "未开通");
                                        }
                                    }else {
                                        logger.error("应用未配置可用产品");
                                        flag = true;
                                        result.put("serverFlag", flag);
                                        result.put("serverMsg", "已过期-应用未配置可用产品");
                                    }
                                    break;
                                default:
                                    // 其他省网均设置为已过期
                                    flag = true;
                                    result.put("serverFlag", flag);
                                    result.put("serverMsg", "已过期");
                                    break;
                            }
                        } else {
                            // TODO SP平台服务鉴权-未过期--查询相关产品信息
                            result.put("serverFlag", flag);
                            result.put("serverMsg", "未过期");
                        }
                    }
                }
            }
            dataRet.setData(result);
            dataRet.pushOk("服务鉴权接口调用成功！");
        } catch (Exception e) {
            dataRet.pushError("服务鉴权失败：" + e.getMessage());
            logger.error("服务鉴权异常！", e);
        }
        return dataRet;
    }

    /**
     * 栏目鉴权
     *
     * @param cardNo
     * @param columnsId
     * @param appId
     * @return
     */
    @RequestMapping(value = "/columnsAuth", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI columnsAuth(@RequestParam("cardNo") String cardNo,
                                        @RequestParam("columnsId") String columnsId,
                                        @RequestParam("appId") String appId,
                                        @RequestParam("serverCode") String serverCode) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            //用户
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
            if (userAccount != null) {
                // 栏目配置的产品列表
                List<Product> list = productRelService.findByBizId(columnsId);

                // 产品订购鉴权
                boolean orderFlag = orderService.orderAuth(serverCode, userAccount.getUserId(), appId, list);
                result.put("orderFlag", orderFlag);
                if (orderFlag) {
                    result.put("orderMsg", "已过期");
                } else {
                    result.put("orderMsg", "未过期");
                }
            }
            dataRet.setData(result);
            dataRet.pushOk("栏目鉴权接口调用成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("播放鉴权异常！", e);
        }
        return dataRet;
    }

    /**
     * 内容鉴权
     *
     * @param cardNo
     * @param contentId
     * @param appId
     * @return
     */
    @RequestMapping(value = "/contentAuth", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI contentAuth(@RequestParam("cardNo") String cardNo,
                                        @RequestParam("contentId") String contentId,
                                        @RequestParam("appId") String appId,
                                        @RequestParam("serverCode") String serverCode) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            //用户
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
            if (userAccount != null) {
                // 内容配置的产品列表
                List<Product> list = productRelService.findByBizId(contentId);

                // 产品订购鉴权
                boolean orderFlag = orderService.orderAuth(serverCode, userAccount.getUserId(), appId, list);
                result.put("orderFlag", orderFlag);
                if (orderFlag) {
                    result.put("orderMsg", "已过期");
                } else {
                    result.put("orderMsg", "未过期");
                }
            }
            dataRet.setData(result);
            dataRet.pushOk("内容鉴权接口调用成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("播放鉴权异常！", e);
        }
        return dataRet;
    }
}
