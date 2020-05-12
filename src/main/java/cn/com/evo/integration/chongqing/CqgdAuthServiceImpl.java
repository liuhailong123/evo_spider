package cn.com.evo.integration.chongqing;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.pay.ProductServerRel;
import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.service.vip.UserService;
import cn.com.evo.cms.web.api.vo.VideoApiVo;
import cn.com.evo.cms.web.voService.VideoVoService;
import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.chongqing.common.CqgdAuthRequest;
import cn.com.evo.integration.chongqing.pay.CQAuthServiceSDK;
import cn.com.evo.integration.chongqing.pay.model.CA;
import cn.com.evo.integration.chongqing.pay.model.CustInfo;
import cn.com.evo.integration.chongqing.pay.model.GetUserInfoResponse;
import cn.com.evo.integration.chongqing.pay.model.ProductOrder;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.result.AuthResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 重庆鉴权和支付相关逻辑
 * @author rf
 * @date 2019/6/11
 */
@Service
@Transactional
public class CqgdAuthServiceImpl {
    protected Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private LimitFreeService limitFreeService;
    @Autowired
    private ProductRelService productRelService;
    @Autowired
    private UserServerService userServerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Autowired
    private VideoVoService videoVoService;


    public AuthResponse playAuth(CqgdAuthRequest authRequest) {
        AuthResponse response = new AuthResponse();
        try {
            // 获取用户信息
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(authRequest.getCardNo(),
                    3, null);
            if (userAccount == null) {
                // 用户不存在或创建失败，返回应用鉴权失败
                response.pushUserAuthFail();
                return response;
            } else {
                Column app = columnService.findById(authRequest.getAppId());
                // 获取内容对应播放地址
                List<VideoApiVo> videos = videoVoService.findVideos(authRequest.getContentId(),
                        authRequest.getChildContentId(), app.getPlatform());

                // 判断内容是否是剧集如果是剧集则判断是否前三集免费
                if (StringUtils.isNotBlank(authRequest.getChildContentId())) {
                    // 鉴权内容是剧集
                    CatalogueRelation rel = catalogueRelationService.getByAIdAndBIdAndType(authRequest.getColumnId(),
                            authRequest.getContentId(), 2);
                    if (rel != null) {
                        Content child = contentService.findById(authRequest.getChildContentId());
                        if (rel.getFreeNum() != null) {
                            if (child.getSort() <= rel.getFreeNum()) {
                                // 剧集前N集免费
                                response.pushLimitFreeSuccess(videos);
                                return response;
                            }
                        }
                    }
                }

                // 栏目是否存在限时免费
                boolean isFree = limitFreeService.isFree(authRequest.getColumnId(), authRequest.getAppId());
                if (isFree) {
                    // 限免，返回限免通过
                    response.pushLimitFreeSuccess(videos);
                    return response;
                } else {
                    // 非限免
                    // 栏目是否配置产品，如果配置产品则判断产品订购状态
                    // 获取有效期内的产品
                    List<Product> products = productRelService.findByBizId(authRequest.getColumnId());
                    if (products.size() == 0) {
                        // 栏目未配置产品定价,开始判断应用服务开通情况
                        // 根据serverCode、userId查询服务开通表，判断是否到期
                        boolean flag = userServerService.checkUserServer(userAccount.getUserId(),
                                "10001", authRequest.getAppId());
                        if (!flag) {
                            // 服务已开通并且未过期,返回鉴权通过
                            response.pushAuthSuccess(videos);
                            return response;
                        } else {
                            // 服务未开通，开始重庆特殊逻辑，查询boss接口判断是否存在线下订购行为
                            return queryAuth(authRequest, response, videos);
                        }
                    } else {
                        // 栏目已配置产品定价,
                        // 调用底层逻辑判断产品订购情况
                        boolean flag = orderService.orderAuth("10001", userAccount.getUserId(),
                                authRequest.getAppId(), products);
                        if (!flag) {
                            response.pushAuthSuccess(videos);
                        } else {
                            response.pushOrderAuthFail(products.get(0).getId(), videos);
                        }
                    }
                }
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException("重庆有线鉴权逻辑异常：" + e.getMessage(), e);
        }
    }

    /**
     * 订购信息上报接口
     * 用于客户端鉴权通过后，上报用户订购信息。由后台逻辑上判断是否增加order表数据。
     *
     */
    public void insertOrderInfo(String cardNo,
                                String orderDate,
                                String productCode,
                                String code,
                                String appId) {
        try {
            // 获取用户
            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 3, null);
            if (userAccount != null) {
                User user = userService.findById(userAccount.getUserId());

                // 获取订购时间 2019-08-09 06:00:00
                Date overDate = DateUtil.stringToDate(orderDate);

                // 根据局方产品编码获取sp产品编码
                Product spProduct = productService.getByThirdPartyCodeAndCode(productCode, code);
                if (spProduct != null) {
                    // 判断订单是否存在
                    Order order = orderService.getByUserIdAndProductCodeAndOrderTypeAndOverDateAndAppId(user.getId(), spProduct.getCode(),
                            1, overDate, appId);
                    if (order == null) {
                        Column app = columnService.findById(appId);
                        order = new Order();
                        order.setUser(user);
                        order.setUserAccount(userAccount);
                        order.setOverDate(overDate);
                        order.setApp(app);
                        // 已支付
                        order.setOrderType(1);
                        order.setProduct(spProduct);

                        orderService.save(order);

                        logger.error("该订单已记录完成");
                    } else {
                        logger.error("该订单已记录，无需重复记录");
                    }
                } else {
                    logger.error("未配置局方产品编码对应的产品");
                }
            } else {
                logger.error("用户不存在");
            }
        } catch (Exception e) {
            logger.error("订购信息上报接口异常：" + e.getMessage(), e);
        }
    }

    //重庆特殊鉴权逻辑
    private AuthResponse queryAuth(CqgdAuthRequest authRequest, AuthResponse response, List<VideoApiVo> videos) {
        GetUserInfoResponse result = CQAuthServiceSDK.auth(authRequest.getCardNo(), "U");
//        GetUserInfoResponse result = JSONObject.parseObject("{\"code\":\"200\",\"custInfo\":{\"accountId\":\"14603434\",\"address\":\"渝北区_北部银桦路39号太阳园5栋1单元9-13\",\"balanceList\":[{\"balance\":\"232.92\",\"balanceId\":\"1021\",\"balanceName\":\"时间点业务\"},{\"balance\":\"0.0\",\"balanceId\":\"1002\",\"balanceName\":\"承诺性基本业务\"},{\"balance\":\"6.52\",\"balanceId\":\"0\",\"balanceName\":\"通用\"},{\"balance\":\"0.0\",\"balanceId\":\"1005\",\"balanceName\":\"互动基本业务\"}],\"custCode\":\"\",\"custId\":\"4893372\",\"custName\":\"曹佰中\",\"mobile\":\"15826091395\",\"oweTotalFee\":\"0.00\",\"ownCorpOrg\":\"291\",\"phone\":\"\",\"totleBalance\":\"239.44\",\"userList\":[{\"caId\":\"9950000000603261\",\"caModel\":\"HD\",\"caType\":\"1\",\"isMain\":0,\"prodInstId\":\"7152648\",\"productOrderList\":[{\"lineOrPoint\":\"point\",\"offerId\":\"800520180020\",\"offerInstId\":\"78550172\",\"offerName\":\"优生活1个月29元\",\"productLs\":[{\"expireDate\":\"2019-12-11 11:16:15\",\"productId\":\"800622003084\",\"productName\":\"优生活1个月29元计费产品\",\"status\":\"0\",\"validDate\":\"2019-11-11 11:16:15\"},{\"expireDate\":\"2019-12-11 11:16:15\",\"productId\":\"800622003252\",\"productName\":\"优生活1个月产品\",\"status\":\"0\",\"validDate\":\"2019-11-11 11:16:15\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800521000219\",\"offerInstId\":\"78598027\",\"offerName\":\"健康新主张一个月25元\",\"productLs\":[{\"expireDate\":\"2019-12-15 10:36:35\",\"productId\":\"800622005179\",\"productName\":\"健康新主张(1个月)\",\"status\":\"0\",\"validDate\":\"2019-11-15 10:36:35\"},{\"expireDate\":\"2019-12-15 10:36:35\",\"productId\":\"800693000163\",\"productName\":\"健康新主张一个月25元25.0元\\/月计费产品\",\"status\":\"0\",\"validDate\":\"2019-11-15 10:36:35\"}]},{\"lineOrPoint\":\"line\",\"offerId\":\"800520120093\",\"offerInstId\":\"20640383\",\"offerName\":\"直播高清主机开通(一年承诺)套餐\",\"productLs\":[{\"expireDate\":\"2099-12-31 00:00:00\",\"productId\":\"800620120093\",\"productName\":\"数字基本普通(主机)25元\\/月(一年承诺)\",\"status\":\"0\",\"validDate\":\"2012-08-20 14:07:08\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800520180001\",\"offerInstId\":\"78627565\",\"offerName\":\"芒果TV1个月20元\",\"productLs\":[{\"expireDate\":\"2020-04-18 00:00:00\",\"productId\":\"800622003065\",\"productName\":\"芒果TV1个月20元计费产品\",\"status\":\"0\",\"validDate\":\"2019-11-18 10:42:48\"},{\"expireDate\":\"2020-04-18 00:00:00\",\"productId\":\"800622003198\",\"productName\":\"芒果TV1个月20元\",\"status\":\"0\",\"validDate\":\"2019-11-18 10:42:48\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800521000340\",\"offerInstId\":\"78552041\",\"offerName\":\"R幸福健身团\",\"productLs\":[{\"expireDate\":\"2019-12-11 12:47:24\",\"productId\":\"800693000267\",\"productName\":\"R幸福健身团16.0元\\/月计费产品\",\"status\":\"0\",\"validDate\":\"2019-11-11 12:47:24\"},{\"expireDate\":\"2019-12-11 12:47:24\",\"productId\":\"800622003263\",\"productName\":\"幸福健身团1个月产品\",\"status\":\"0\",\"validDate\":\"2019-11-11 12:47:24\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800521000355\",\"offerInstId\":\"78601937\",\"offerName\":\"QQ音乐\",\"productLs\":[{\"expireDate\":\"2019-12-15 14:21:48\",\"productId\":\"800693000281\",\"productName\":\"QQ音乐16.0元\\/月计费产品\",\"status\":\"0\",\"validDate\":\"2019-11-15 14:21:48\"},{\"expireDate\":\"2019-12-15 14:21:48\",\"productId\":\"800622003202\",\"productName\":\"QQ音乐1个月\",\"status\":\"0\",\"validDate\":\"2019-11-15 14:21:48\"}]},{\"lineOrPoint\":\"point\",\"offerId\":\"800521000307\",\"offerInstId\":\"78632768\",\"offerName\":\"亲多多19元包月签约代扣\",\"productLs\":[{\"expireDate\":\"2019-12-18 15:14:10\",\"productId\":\"800622005180\",\"productName\":\"亲多多(1个月)\",\"status\":\"0\",\"validDate\":\"2019-11-18 15:14:10\"},{\"expireDate\":\"2019-12-18 15:14:10\",\"productId\":\"800693000235\",\"productName\":\"亲多多19元包月签约代扣19.0元\\/月计费产品\",\"status\":\"0\",\"validDate\":\"2019-11-18 15:14:10\"}]}],\"status\":1}]},\"msg\":\"成功\"}",
//                GetUserInfoResponse.class);
        if ("200".equals(result.getCode())) {
            Map<String, Object> extra = response.getExtra();
            CustInfo custInfo = result.getCustInfo();
            boolean flag = eqOfferIdAndStatus(authRequest, custInfo);
            if(flag){
                response.pushAuthSuccess(videos);
                return response;
            } else {
                extra.put("qryType", "I");
                extra.put("qryValue", authRequest.getCardNo());
                extra.put("comboId", ConstantFactory.map.get(CQConstantEnum.comboid.getKey()));
                extra.put("appId", ConstantFactory.map.get(CQConstantEnum.app_id.getKey()));
                response.setExtra(extra);
                response.pushServerAuthFail(videos);
                return response;
            }
        } else {
            response.setVideos(null);
            response.setContent(null);
            response.pushError1("当前卡号错误，请确认！");
            return response;
        }
    }

    /**
     * 获取套餐状态
     *
     * @param authRequest
     * @param custInfo
     * @return
     */
    public boolean eqOfferIdAndStatus(CqgdAuthRequest authRequest, CustInfo custInfo){
        System.out.println("=====================");
        System.out.println(JSONObject.toJSONString(custInfo));
        System.out.println("=====================");
        boolean flag = false;
        List<Product> products = productService.findByAppId(authRequest.getAppId());
        for (CA ca : custInfo.getUserList()) {
            for (ProductOrder productOrder : ca.getProductOrderList()) {
                for (Product pro : products) {
                    if (productOrder.getOfferId().equals(pro.getThirdPartyCode())){
                        System.out.println("产品id对比：" + productOrder.getOfferId() + "局<->我" + pro.getThirdPartyCode());
                        for (cn.com.evo.integration.chongqing.pay.model.Product product : productOrder.getProductLs()) {
                            //通过套餐时间计算是包年还是包季
                            Long validDate = DateUtil.parse(product.getValidDate(), DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
                            Long expireDate = DateUtil.parse(product.getExpireDate(), DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
                            long count = (expireDate - validDate) / 86400000;
                            //当前套餐状态为已订购，则通过鉴权
                            if("0".equals(product.getStatus())){
                                //添加服务
                                UserAccount user = userAccountService.getByAccountNoAndAccountType(authRequest.getCardNo(), 3, null);
                                if(user != null){
                                    String validDate1 = product.getValidDate();
                                    String expireDate1 = product.getExpireDate();
                                    System.out.println(validDate1 + "===添加服务==="  +expireDate1);
                                    userServerService.openServer(user.getUserId(), authRequest.getAppId(), pro.getId(),
                                            validDate1,
                                            expireDate1);
                                }
                                //借阅订单添加逻辑
                                if(count > 92 && pro.getCode().equals("200015")){
                                    insertOrderInfo(authRequest.getCardNo(), product.getValidDate(), pro.getThirdPartyCode(), pro.getCode(), authRequest.getAppId());
                                    //少儿绘本包年套餐
                                    //订购成功后新增借书套餐绑定逻辑
                                    System.out.println("新增绘本包年借阅参数打印：\n卡号" + authRequest.getCardNo() + "\n三方产品编码" + product.getValidDate() +
                                            "\nsp产品编码" + pro.getCode() + "\n应用id" + authRequest.getAppId());
                                }else if(count < 93 && count > 30 && pro.getCode().equals("200014")){
                                    insertOrderInfo(authRequest.getCardNo(), product.getValidDate(), pro.getThirdPartyCode(), pro.getCode(), authRequest.getAppId());
                                    //少儿绘本包年套餐
                                    //订购成功后新增借书套餐绑定逻辑
                                    System.out.println("新增绘本包季借阅参数打印：\n卡号" + authRequest.getCardNo() + "\n三方产品编码" + product.getValidDate() +
                                            "\nsp产品编码" + pro.getCode() + "\n应用id" + authRequest.getAppId());
                                }else if(pro.getCode().equals("200012")){
                                    insertOrderInfo(authRequest.getCardNo(), product.getValidDate(), pro.getThirdPartyCode(), pro.getCode(), authRequest.getAppId());
                                    //少儿绘本包年套餐
                                    //订购成功后新增借书套餐绑定逻辑
                                    System.out.println("新增亲多多包月点播参数打印：\n卡号" + authRequest.getCardNo() + "\n三方产品编码" + product.getValidDate() +
                                            "\nsp产品编码" + pro.getCode() + "\n应用id" + authRequest.getAppId());
                                }else if(pro.getCode().equals("200013")){
                                    insertOrderInfo(authRequest.getCardNo(), product.getValidDate(), pro.getThirdPartyCode(), pro.getCode(), authRequest.getAppId());
                                    //少儿绘本包年套餐
                                    //订购成功后新增借书套餐绑定逻辑
                                    System.out.println("新增绘本包年点播参数打印：\n卡号" + authRequest.getCardNo() + "\n三方产品编码" + product.getValidDate() +
                                            "\nsp产品编码" + pro.getCode() + "\n应用id" + authRequest.getAppId());
                                }
                                flag = true;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public void pay(String qryType, String cardNo, String prodCode, String appId) {
        CQAuthServiceSDK.pay(qryType, cardNo, prodCode, appId, ConstantFactory.map.get(CQConstantEnum.pay_notify.getKey()));
    }
}
