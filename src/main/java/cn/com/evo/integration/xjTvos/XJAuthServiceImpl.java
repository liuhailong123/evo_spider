package cn.com.evo.integration.xjTvos;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.web.api.vo.VideoApiVo;
import cn.com.evo.cms.web.voService.VideoVoService;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.common.dto.ProdDto;
import cn.com.evo.integration.common.result.AuthResponse;
import cn.com.evo.integration.jinanyouxian.common.ConstantEnum;
import cn.com.evo.integration.xjTvos.common.XJConstantEnum;
import cn.com.evo.integration.xjTvos.common.XJTvosAuthRequest;
import cn.com.evo.integration.xjTvos.content.PayUtilXinJiang;
import cn.com.evo.integration.xjTvos.content.XJWebserviceSDK;
import cn.com.evo.integration.xjTvos.content.dto.*;
import cn.com.evo.integration.xjTvos.pay.XJAuthSDK;
import cn.com.evo.integration.xjTvos.pay.model.GetPriceInfoResponse;
import cn.com.evo.integration.xjTvos.pay.model.ProductInfoResponse;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rf
 * @date 2019/5/16
 */
@Service
@Transactional
public class XJAuthServiceImpl {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private LimitFreeService limitFreeService;
    @Autowired
    private ProductRelService productRelService;
    @Autowired
    private UserServerService userServerService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ColumnService columnService;

    @Autowired
    private VideoVoService videoVoService;

    /**
     * 获取播放地址
     *
     * @param accesstoken
     * @param videoId
     * @return
     */
    public String getPlayUrl(String accesstoken, String videoId) {
        try {
            String playUrl = XJWebserviceSDK.getVideoAuthorityInfo(accesstoken, videoId);
            return playUrl;
        } catch (Exception e) {
            throw new RuntimeException("获取播放地址异常" + e.getMessage(), e);
        }
    }

    //鉴权
    public AuthResponse playAuth(XJTvosAuthRequest authRequest) {
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
                // 根据应用id获取来源
                Column app = columnService.findById(authRequest.getAppId());
                // 获取内容对应播放地址
                List<VideoApiVo> videos = videoVoService.findVideos(authRequest.getContentId(),
                        authRequest.getChildContentId(), app.getPlatform());
                for (VideoApiVo video : videos) {
                    // 调用局方接口返回真实播放地址
                    String playUrl = getPlayUrl(authRequest.getAccesstoken(), video.getUrl());
                    video.setUrl(playUrl);
                }

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
                            // 服务未开通，新疆tvos广电特殊逻辑，调用局方询价接口
                            queryOfflineOrder(authRequest, response, videos);
                            return response;
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
            throw new RuntimeException("新疆tvos播放鉴权逻辑异常：" + e.getMessage(), e);
        }
    }

    private void queryOfflineOrder(XJTvosAuthRequest authRequest, AuthResponse response, List<VideoApiVo> videos) {
        queryOfflineOrder2(authRequest.getAppId(), authRequest.getCardNo(), videos, response);
    }

    public void queryOfflineOrder2(String appId, String cardNo, List<VideoApiVo> videos, AuthResponse response) {
        // 栏目配置的BOSS产品列表
        List<Product> list = productRelService.findByBizIdAndType(appId, 2);
        if (list.size() > 0) {
            for (Product product : list) {
                // 根据appId获取对应产品list上面的对应三方boss产品编码。
                String prodCode = product.getThirdPartyCode();
                if (prodCode != null) {
                    // 通过上述三方boss产品编码调用boss接口，判断产品查询是否订购
                    XinJiangBossIsOrderRsp rsp = PayUtilXinJiang.queryIsOrdered(cardNo, prodCode);
                    if ("0".equals(rsp.getRetCode())) {
                        response.pushAuthSuccess(videos);
                        /**
                         * 如果有订购产品信息则返回数据
                         */
                        List<ProdDto> prods = Lists.newArrayList();
                        for (cn.com.evo.integration.xjTvos.content.dto.XinJiangBossIsOrderProd xinJiangBossIsOrderProd : rsp.getRows()) {
                            prods.add(new ProdDto(xinJiangBossIsOrderProd.getProdName(), product.getCode(),
                                    xinJiangBossIsOrderProd.getBusiValidDate(), xinJiangBossIsOrderProd.getBusiExpireDate()));
                        }
                        response.setProds(prods);
                        break;
                    } else {
                        response.pushServerAuthFail(videos);
                    }
                } else {
                    // 未配置三方boss产品编码，跳过验证
                    response.pushServerAuthFail(videos);
                }
            }
        } else {
            response.pushServerAuthFail(videos);
        }
    }

    //获取定价信息
    private AuthResponse queryPriceInfo(AuthResponse response, List<VideoApiVo> videos, XJTvosAuthRequest authRequest) {
        GetPriceInfoResponse priceInfo = XJAuthSDK.getPriceInfo(authRequest.getAccesstoken(), ConstantFactory.map.get(ConstantEnum.video_id.getKey()));
        if (priceInfo.getRet() == 0) {
            if (priceInfo.getIs_purchased() == 1) {
                response.pushAuthSuccess(videos);
            } else {
                //产品未订购，获取套餐信息返回前台，供调用线上营业厅接口
                ProductInfoResponse res = XJAuthSDK.getProductInfo("3", "1015", "tv",
                        authRequest.getCardNo(), XJConstantEnum.PPVId.getKey());
                Map map = new HashMap<>();
                map.put("product", res);
                response.setExtra(map);
            }
        } else {
            response.pushOrderAuthFail("", videos);
        }
        return response;
    }

}
