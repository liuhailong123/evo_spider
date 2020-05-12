package cn.com.evo.integration.jinanyouxian;import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;import cn.com.evo.cms.domain.entity.cms.Column;import cn.com.evo.cms.domain.entity.cms.Content;import cn.com.evo.cms.domain.entity.pay.Product;import cn.com.evo.cms.domain.entity.vip.UserAccount;import cn.com.evo.cms.service.cms.CatalogueRelationService;import cn.com.evo.cms.service.cms.ColumnService;import cn.com.evo.cms.service.cms.ContentService;import cn.com.evo.cms.service.pay.LimitFreeService;import cn.com.evo.cms.service.pay.OrderService;import cn.com.evo.cms.service.pay.ProductRelService;import cn.com.evo.cms.service.vip.UserAccountService;import cn.com.evo.cms.service.vip.UserServerService;import cn.com.evo.cms.web.api.vo.VideoApiVo;import cn.com.evo.cms.web.voService.VideoVoService;import cn.com.evo.integration.common.ConstantFactory;import cn.com.evo.integration.common.dto.PlayAuthDto;import cn.com.evo.integration.common.result.AuthResponse;import cn.com.evo.integration.jinanyouxian.common.ConstantEnum;import cn.com.evo.integration.jinanyouxian.common.JnyxAuthRequest;import cn.com.evo.integration.jinanyouxian.sdk.AuthSDK;import cn.com.evo.integration.jinanyouxian.sdk.ContentSDK;import org.apache.commons.lang3.StringUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import javax.transaction.Transactional;import java.util.List;/** * @Description: 济南有线鉴权逻辑 * @author: lu.xin * @create: 2019-04-03 6:04 PM **/@Service@Transactionalpublic class JnyxAuthServiceImpl {    @Autowired    private LimitFreeService limitFreeService;    @Autowired    private UserAccountService userAccountService;    @Autowired    private CatalogueRelationService catalogueRelationService;    @Autowired    private ContentService contentService;    @Autowired    private UserServerService userServerService;    @Autowired    private ProductRelService productRelService;    @Autowired    private OrderService orderService;    @Autowired    private ColumnService columnService;    @Autowired    private VideoVoService videoVoService;    /**     * 播放鉴权     *     * @param authRequest     * @return     */    public AuthResponse playAuth(JnyxAuthRequest authRequest) {        AuthResponse response = new AuthResponse();        try {            // 获取用户信息            UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(authRequest.getCardNo(),                    3, null);            if (userAccount == null) {                // 用户不存在或创建失败，返回应用鉴权失败                response.pushUserAuthFail();                return response;            } else {                Column app = columnService.findById(authRequest.getAppId());                // 获取内容对应播放地址                List<VideoApiVo> videos = videoVoService.findVideos(authRequest.getContentId(),                        authRequest.getChildContentId(), app.getPlatform());                for (VideoApiVo video : videos) {                    // 调用局方接口返回真实播放地址                    String playUrl = getPlayUrl(authRequest.getAccesstoken(),                            video.getUrl());                    video.setUrl(playUrl);                }                // 判断内容是否是剧集如果是剧集则判断是否前三集免费                if (StringUtils.isNotBlank(authRequest.getChildContentId())) {                    // 鉴权内容是剧集                    CatalogueRelation rel = catalogueRelationService.getByAIdAndBIdAndType(authRequest.getColumnId(),                            authRequest.getContentId(), 2);                    if (rel != null) {                        Content child = contentService.findById(authRequest.getChildContentId());                        if (rel.getFreeNum() != null) {                            if (child.getSort() <= rel.getFreeNum()) {                                // 剧集前N集免费                                response.pushLimitFreeSuccess(videos);                                return response;                            }                        }                    }                }                // 栏目是否存在限时免费                boolean isFree = limitFreeService.isFree(authRequest.getColumnId(), authRequest.getAppId());                if (isFree) {                    // 限免，返回限免通过                    response.pushLimitFreeSuccess(videos);                    return response;                } else {                    // 非限免                    // 栏目是否配置产品，如果配置产品则判断产品订购状态                    // 获取有效期内的产品                    List<Product> products = productRelService.findByBizId(authRequest.getColumnId());                    if (products.size() == 0) {                        // 栏目未配置产品定价,开始判断应用服务开通情况                        // 根据serverCode、userId查询服务开通表，判断是否到期                        boolean flag = userServerService.checkUserServer(userAccount.getUserId(),                                "10001", authRequest.getAppId());                        if (!flag) {                            // 服务已开通并且未过期,返回鉴权通过                            response.pushAuthSuccess(videos);                            return response;                        } else {                            // 服务未开通，开始济南有线特殊逻辑，查询boss接口判断是否存在线下订购行为                            queryOfflineOrder(authRequest.getAccesstoken(), videos, response);                            return response;                        }                    } else {                        // 栏目已配置产品定价,                        // 调用底层逻辑判断产品订购情况                        boolean flag = orderService.orderAuth("10001", userAccount.getUserId(),                                authRequest.getAppId(), products);                        if (!flag) {                            response.pushAuthSuccess(videos);                        } else {                            response.pushOrderAuthFail(products.get(0).getId(), videos);                        }                    }                }            }            return response;        } catch (Exception e) {            throw new RuntimeException("济南有线播放鉴权逻辑异常：" + e.getMessage(), e);        }    }    /**     * 获取播放地址     *     * @param accesstoken     * @param videoId     * @return     */    public String getPlayUrl(String accesstoken, String videoId) {        try {            String playUrl = ContentSDK.getVideoAuthorityInfo(accesstoken, videoId);            return playUrl;        } catch (Exception e) {            throw new RuntimeException("获取播放地址异常" + e.getMessage(), e);        }    }    /**     * 济南有线线下查询逻辑     *     * @param accesstoken     */    private void queryOfflineOrder(String accesstoken, List<VideoApiVo> videos, AuthResponse response) {        Boolean flag = AuthSDK.vdieoAuth(accesstoken, ConstantFactory.map.get(ConstantEnum.video_id.getKey()));        if (flag) {            response.pushAuthSuccess(videos);        } else {            response.pushOrderAuthFail("", videos);        }    }    /**     * 济南有线获取播放地址逻辑     *     * @param dto     * @param response     */    public void findVideos(PlayAuthDto dto, AuthResponse response) {        try {            for (VideoApiVo video : dto.getVideos()) {                // 调用局方接口返回真实播放地址                String playUrl = getPlayUrl(dto.getAccesstoken(),                        video.getUrl());                video.setUrl(playUrl);            }        } catch (Exception e) {            throw new RuntimeException("新疆省网获取播放地址异常" + e.getMessage(), e);        }    }    /**     * 济南有线线下查询逻辑     *     * @param dto     */    public void queryOfflineOrder(PlayAuthDto dto, AuthResponse response) {        try {            Boolean flag = AuthSDK.vdieoAuth(dto.getAccesstoken(),                    ConstantFactory.map.get(ConstantEnum.video_id.getKey()));            if (flag) {                response.pushAuthSuccess(dto.getVideos());            } else {                response.pushServerAuthFail(dto.getVideos());            }        } catch (Exception e) {            throw new RuntimeException("查询新疆产品是否已订购异常：" + e.getMessage(), e);        }    }}