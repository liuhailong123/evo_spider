package cn.com.evo.cms.web.api;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.constant.Constant;
import cn.com.evo.cms.constant.ProvinceCodeEnum;
import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.ServerVoucher;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.domain.entity.vip.UserFamily;
import cn.com.evo.cms.domain.entity.vip.VerificationCode;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.ServerVoucherService;
import cn.com.evo.cms.service.vip.MessageParamService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserFamilyService;
import cn.com.evo.cms.service.vip.VerificationCodeService;
import cn.com.evo.cms.utils.QrCodeUtils;
import cn.com.evo.cms.web.api.vo.UserProductDetailApiVo;
import cn.com.evo.cms.web.voService.ProductVoService;
import cn.com.evo.integration.chongqing.CqgdAuthServiceImpl;
import cn.com.evo.integration.chongqing.common.CqgdAuthRequest;
import cn.com.evo.integration.chongqing.pay.CQAuthServiceSDK;
import cn.com.evo.integration.chongqing.pay.model.GetUserInfoResponse;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.dto.ProdDto;
import cn.com.evo.integration.common.result.AuthResponse;
import cn.com.evo.integration.jinanyouxian.common.ConstantEnum;
import cn.com.evo.integration.jinanyouxian.sdk.AuthSDK;
import cn.com.evo.integration.xjTvos.XJAuthServiceImpl;
import cn.com.evo.integration.xjnt.XjntAuthServiceImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author shilinxiao
 */
@Controller
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserApi extends BaseController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private MessageParamService messageParamService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserFamilyService userFamilyService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private ServerVoucherService serverVoucherService;

    @Autowired
    private ProductVoService productVoService;
    @Autowired
    private XjntAuthServiceImpl xjntAuthService;
    @Autowired
    private XJAuthServiceImpl xjAuthService;
    @Autowired
    private CqgdAuthServiceImpl cqgdAuthService;

    @RequestMapping(value = "/verificationCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI verificationCode(@RequestParam("phone") String phone) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            JSONObject result = new JSONObject();
            // 生成验证码
            String code = verificationCodeService.getCode(phone);
            // 发送短信验证码
            Boolean sendMsgResult = messageParamService.sendMsg(phone, code, 0);
            // 发送成功
            if (sendMsgResult) {
                // 获取失效时间
                Long invalid = getInvalid();
                VerificationCode vipVerificationCode = new VerificationCode();
                vipVerificationCode.setCode(code);
                vipVerificationCode.setPhone(phone);
                vipVerificationCode.setInvalid(BigInteger.valueOf(invalid));
                verificationCodeService.saveCode(vipVerificationCode);
                result.put("retCode", "0");
                result.put("retMsg", "短信已发送");
            } else {
                result.put("retCode", "-1");
                result.put("retMsg", "短信发送失败");
            }
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("系统发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 获取用户已订购套餐列表
     *
     * @return
     */
    @RequestMapping(value = "setMeallist", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI setMeallist(@RequestParam("cardNo") String cardNo,
                                        @RequestParam("appId") String appId,
                                        @RequestParam(value = "accesstoken", required = false) String accesstoken
    ) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
            List<Order> orders = orderService.findByUserIdAndOrderTypeAndAppIdOrderByCreateDateDesc(userAccount.getUserId(), 1, appId);
            if (orders.size() > 0) {
                List<UserProductDetailApiVo> lvos = Lists.newArrayList();
                UserProductDetailApiVo productDetailApivo = productVoService.transProductData(orders.get(0));
                if (productDetailApivo != null) {
                    lvos.add(productDetailApivo);
                    result.put("retCode", "1");
                    result.put("retMsg", "获取成功");
                } else {
                    result.put("retCode", "0");
                    result.put("retMsg", "暂无数据");
                }
                result.put("products", lvos);
            } else {
                Province province = provinceService.getByEnable(1);
                if (province == null) {
                    throw new RuntimeException("没有可用的省网配置!!!");
                }

                // 省网code枚举
                ProvinceCodeEnum provinceCodeEnum = ProvinceCodeEnum.getByName(province.getCode());
                switch (provinceCodeEnum) {
                    case JinanYouxian:
                        boolean flag = AuthSDK.vdieoAuth(accesstoken, ConstantFactory.map.get(ConstantEnum.video_id.getKey()));
                        if (flag) {
                            result.put("products", new JSONArray());
                            result.put("retCode", "1");
                            result.put("retMsg", "已订购");
                        } else {
                            result.put("products", new JSONArray());
                            result.put("retCode", "0");
                            result.put("retMsg", "无订购产品");
                        }
                        break;
                    case XinJiang:
                        AuthResponse response = new AuthResponse();
                        xjntAuthService.queryOfflineOrder2(appId, cardNo, null, response);
                        if (response.getRetCode() == 1) {
                            // 鉴权通过
                            List<UserProductDetailApiVo> lvos = response.getProds().stream().map(prodDto -> {
                                UserProductDetailApiVo vo = productVoService.transProductData(prodDto.getProdCode(), prodDto.getEndTime());
                                return vo;
                            }).collect(Collectors.toList());
                            result.put("products", lvos);
                            result.put("retCode", "1");
                            result.put("retMsg", "已订购");
                        }else{
                            result.put("products", new JSONArray());
                            result.put("retCode", "0");
                            result.put("retMsg", "无订购产品");
                        }
                        break;
                    case XJTvos:
                        AuthResponse tvosResponse = new AuthResponse();
                        xjAuthService.queryOfflineOrder2(appId, cardNo, null, tvosResponse);
                        if (tvosResponse.getRetCode() == 1) {
                            // 鉴权通过
                            List<UserProductDetailApiVo> lvos = tvosResponse.getProds().stream().map(prodDto -> {
                                UserProductDetailApiVo vo = productVoService.transProductData(prodDto.getProdCode(), prodDto.getEndTime());
                                return vo;
                            }).collect(Collectors.toList());
                            result.put("products", lvos);
                            result.put("retCode", "1");
                            result.put("retMsg", "已订购");
                        }else{
                            result.put("products", new JSONArray());
                            result.put("retCode", "0");
                            result.put("retMsg", "无订购产品");
                        }
                        break;
                    case ChongQing:
                        CqgdAuthRequest authRequest = new CqgdAuthRequest();
                        authRequest.setAppId(appId);
                        authRequest.setCardNo(cardNo);
                        GetUserInfoResponse infoResponse = CQAuthServiceSDK.auth(authRequest.getCardNo(), "U");
                        boolean cqFlag = cqgdAuthService.eqOfferIdAndStatus(authRequest, infoResponse.getCustInfo());
                        if(cqFlag){
                            result.put("products", new ArrayList<>());
                            result.put("retCode", "1");
                            result.put("retMsg", "已订购");
                        }else {
                            result.put("products", new JSONArray());
                            result.put("retCode", "0");
                            result.put("retMsg", "无订购产品");
                        }
                    default:
                        result.put("products", new JSONArray());
                        result.put("retCode", "0");
                        result.put("retMsg", "无订购产品");
                        break;
                }
            }
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取用户已订购套餐时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 获取服务凭证
     *
     * @return
     */
    @RequestMapping(value = "getServerVoucher", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI getServerVoucher(@RequestParam("cardNo") String cardNo,
                                             @RequestParam("appId") String appId,
                                             @RequestParam("width") Integer width,
                                             @RequestParam("height") Integer height) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            JSONObject result = new JSONObject();
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
            List<Order> orders = orderService.findByUserIdAndOrderTypeAndAppIdOrderByCreateDateDesc(userAccount.getUserId(), 1, appId);
            if (orders.size() > 0) {
                // 线上订单
                try {
                    //创建服务凭证（线上的方式）
                    ServerVoucher voucher = serverVoucherService.createVoucher(orders.get(0));
                    if (voucher == null) {
                        result.put("qrCode", "");
                        result.put("qrCodeUrl", "");
                        result.put("retCode", "0");
                        result.put("retMsg", "无服务凭证");
                    } else {
                        String qrCodeUrl = Constant.WXxcxPath + "redirect?code=004&params=" + URLEncoder.encode(voucher.getCode(), "utf-8");
                        String qrCode = QrCodeUtils.base64EncodeForQR(qrCodeUrl, width, height);

                        result.put("qrCode", qrCode);
                        result.put("qrCodeUrl", qrCodeUrl);
                        result.put("retCode", "1");
                        result.put("retMsg", "存在服务凭证");
                    }
                } catch (Exception e) {
                    result.put("qrCode", "");
                    result.put("qrCodeUrl", "");
                    result.put("retCode", "0");
                    result.put("retMsg", "无服务凭证");
                    logger.error("创建服务凭证（线上的方式）异常：" + e.getMessage(), e);
                }
            } else {
                Province province = provinceService.getByEnable(1);
                if (province == null) {
                    throw new RuntimeException("没有可用的省网配置!!!");
                }

                // 省网code枚举
                ProvinceCodeEnum provinceCodeEnum = ProvinceCodeEnum.getByName(province.getCode());
                switch (provinceCodeEnum) {
                    case XJTvos:
                    case XinJiang:
                        AuthResponse response = new AuthResponse();
                        xjntAuthService.queryOfflineOrder2(appId, cardNo, null, response);
                        if (response.getRetCode() == 1) {
                            // 创建服务凭证（线下的方式）
                            try {
                                for (ProdDto prodDto : response.getProds()) {
                                    ServerVoucher voucher = serverVoucherService.createVoucher(userAccount.getUserId(), cardNo,
                                            response.getProds().get(0).getProdCode(), prodDto.getBeginTime());
                                    if (voucher == null) {
                                        result.put("qrCode", "");
                                        result.put("qrCodeUrl", "");
                                        result.put("retCode", "0");
                                        result.put("retMsg", "无服务凭证");
                                    } else {
                                        String qrCodeUrl = Constant.WXxcxPath + "redirect?code=004&params=" + URLEncoder.encode(voucher.getCode(), "utf-8");
                                        String qrCode = QrCodeUtils.base64EncodeForQR(qrCodeUrl, width, height);

                                        result.put("qrCode", qrCode);
                                        result.put("qrCodeUrl", qrCodeUrl);
                                        result.put("retCode", "1");
                                        result.put("retMsg", "存在服务凭证");
                                    }
                                }
                            } catch (Exception e) {
                                result.put("qrCode", "");
                                result.put("qrCodeUrl", "");
                                result.put("retCode", "0");
                                result.put("retMsg", "无服务凭证");
                                logger.error("创建服务凭证（线下的方式）异常：" + e.getMessage(), e);
                            }
                        } else {
                            result.put("qrCode", "");
                            result.put("qrCodeUrl", "");
                            result.put("retCode", "0");
                            result.put("retMsg", "无服务凭证");
                        }
                        break;
                    default:
                        result.put("qrCode", "");
                        result.put("qrCodeUrl", "");
                        result.put("retCode", "0");
                        result.put("retMsg", "无服务凭证");
                        break;
                }
            }
            dataRet.pushOk("查询成功");
            dataRet.setData(result);
        } catch (Exception e) {
            dataRet.pushError("获取服务凭证失败：" + e.getMessage());
            logger.error("获取服务凭证时，发生异常！", e);
        }
        return dataRet;
    }



    /**
     * 获取用户信息接口
     *
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/findUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI findUserInfo(@RequestParam("cardNo") String cardNo,
                                         @RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            // 获取用户信息
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);

            // 获取用户家属信息
            UserFamily userFamily = userFamilyService.getByUserId(userAccount.getUserId());
            if (userFamily == null) {
                result.put("userFamily", new JSONObject());
            } else {
                result.put("userFamily", userFamily);
            }

            // 获取用户已订购产品
            List<Order> orders = orderService.findByUserIdAndOrderTypeAndAppIdOrderByCreateDateDesc(userAccount.getUserId(), 1, appId);
            if (orders.size() > 0) {
                List<UserProductDetailApiVo> lvos = Lists.newArrayList();
                for (Order order : orders) {
                    UserProductDetailApiVo productDetailApivo = productVoService.transProductData(order);
                    if (productDetailApivo != null) {
                        lvos.add(productDetailApivo);
                    }
                    break;
                }
                result.put("products", lvos);
            } else {
                result.put("products", new JSONArray());
            }

            result.put("retCode", "1");
            result.put("retMsg", "获取成功");
            result.put("userInfo", userAccount);
            dataRet.setData(result);
            dataRet.pushOk("成功！");

        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取用户信息时，发生异常！", e);
        }
        return dataRet;
    }


    /**
     * 获取用户家属信息
     *
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/findUserFamily", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI findUserFamily(@RequestParam("cardNo") String cardNo) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            JSONObject result = new JSONObject();
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
            UserFamily userFamily = userFamilyService.getByUserId(userAccount.getUserId());

            result.put("userFamily", userFamily);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("系统发生异常！", e);
        }
        return dataRet;
    }


    /**
     * 保存或更新用户家属信息
     *
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/saveUserFamily", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI saveUserFamily(@RequestParam("cardNo") String cardNo,
                                           @RequestParam("type") Integer type,
                                           @RequestParam("sex") Integer sex,
                                           @RequestParam("birthday") String birthday,
                                           @RequestParam(value = "id", required = false) String id) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);

            UserFamily userFamily = userFamilyService.getByUserId(userAccount.getUserId());
            if (userFamily == null) {
                userFamily = new UserFamily();
            }
            userFamily.setUserId(userAccount.getUserId());
            if (type != null) {
                userFamily.setType(type);
            }
            if (sex != null) {
                userFamily.setSex(sex);
            }
            if (StringUtils.isNotBlank(birthday)) {
                userFamily.setBirthday(birthday);
            }
            if (StringUtils.isNotBlank(id)) {
                userFamily.setId(id);
            }

            userFamilyService.saveOrUpdateEntity(userFamily);

            JSONObject json = new JSONObject();
            json.put("retCode", "0");
            json.put("retMsg", "成功");
            dataRet.setData(json);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("系统发生异常！", e);
        }
        return dataRet;
    }


    /**
     * 获取失效时间
     *
     * @return
     */
    private Long getInvalid() {
        Long time1 = System.currentTimeMillis();
        Long time2 = 1000 * 60 * 5L;
        Long invalid = time1 + time2;
        return invalid;
    }

}