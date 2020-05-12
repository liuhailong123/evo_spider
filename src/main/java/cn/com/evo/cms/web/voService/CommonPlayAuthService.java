package cn.com.evo.cms.web.voService;

import cn.com.evo.admin.manage.domain.entity.FlowManage;
import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.FlowManageService;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.domain.enums.ContentClassifyEnum;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.web.api.vo.VideoApiVo;
import cn.com.evo.integration.common.dto.PlayAuthDto;
import cn.com.evo.integration.common.result.AuthResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 通用播放鉴权流程化-service
 */
@Service
@Transactional
public class CommonPlayAuthService {
    protected Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private LimitFreeService limitFreeService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private FlowManageService flowManageService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserServerService userServerService;
    @Autowired
    private ProductRelService productRelService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ColumnService columnService;

    @Autowired
    private VideoVoService videoVoService;

    /**
     * 执行鉴权流程逻辑
     *
     * @param playAuthDto
     * @param request
     * @return
     */
    public AuthResponse invokeAuth(PlayAuthDto playAuthDto, HttpServletRequest request) {
        AuthResponse response = new AuthResponse();
        try {
            // 获取鉴权流程中使用的对象，放入dto中。
            this.getEntities(playAuthDto, response);
            if (response.getRetCode() != null) {
                // 系统报错直接返回response
                if (response.getRetCode() < 0) {
                    return response;
                }
            }

            // 默认设置服务鉴权不通过
            response.pushServerAuthFail(playAuthDto.getVideos());

            // 获取可用鉴权流程配置
            List<FlowManage> playAuthFlows = flowManageService.findByProvinceIdAndType(playAuthDto.getProvince().getId(), 1);
            WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            for (FlowManage flowManage : playAuthFlows) {
                // 获取配置的service的bean
                Object obj = wac.getBean(Class.forName(flowManage.getClassPath()));
                if (null == obj) {
                    logger.error("找不到配置的classPath");
                    throw new RuntimeException("找不到配置的classPath");
                } else {
                    // 执行配置的method
                    Method method = obj.getClass().getMethod(flowManage.getFuncName(),
                            PlayAuthDto.class, AuthResponse.class);
                    method.invoke(obj, playAuthDto, response);
                    if (response.getRetCode() >= 0) {
                        // 如果有返回值为鉴权通过则直接跳出循环
                        break;
                    }
                }
            }
            return response;
        } catch (Exception e) {
            logger.error("执行省网播放鉴权流程逻辑异常：" + e.getMessage(), e);
            throw new RuntimeException("执行省网播放鉴权流程逻辑异常:" + e.getMessage(), e);
        }
    }

    /**
     * 获取流程中需要的相关对象
     *
     * @param dto
     * @param authResponse
     */
    public void getEntities(PlayAuthDto dto, AuthResponse authResponse) {
        // 获取用户信息
        UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(dto.getCardNo(),
                3, null);
        if (userAccount == null) {
            // 用户不存在或创建失败，返回应用鉴权失败
            authResponse.pushUserAuthFail();
            return;
        } else {
            dto.setUserAccount(userAccount);
        }

        // 获取可用省网配置
        Province province = provinceService.getByEnable(1);
        if (province == null) {
            authResponse.pushError2("平台省网配置错误");
            return;
        } else {
            dto.setProvince(province);
        }

        CatalogueRelation catalogueRelation = catalogueRelationService.findById(dto.getContentId());
        // 获取内容关系对象
        if (catalogueRelation == null) {
            authResponse.pushError5("内容id有误");
            return;
        } else {
            dto.setCatalogueRelation(catalogueRelation);
        }

        // 获取内容对象
        Content content = contentService.findById(catalogueRelation.getBId());
        if (content == null) {
            authResponse.pushError5("内容id有误");
            return;
        } else {
            dto.setContent(content);
        }

        // 获取剧集子集对象
        if (StringUtils.isBlank(dto.getChildContentId())) {
            if (ContentClassifyEnum.episode.getIndex() == content.getClassify()) {
                Content child = contentService.findContentByNumber(dto.getContentId(), dto.getNumber());
                if (child == null) {
                    logger.error("无当前子集信息!!!");
                    authResponse.pushError4("无当前子集信息");
                    return;
                } else {
                    dto.setChildContentId(child.getId());
                    dto.setChild(child);
                }
            }
        }

        Column app = columnService.findById(dto.getAppId());
        // 获取内容对应播放地址
        List<VideoApiVo> videos = videoVoService.findVideos(dto.getContentId(), dto.getChildContentId(), app.getPlatform());
        dto.setVideos(videos);

    }

    /**
     * 系统流程1：
     * 判断剧集是否前N集免费
     *
     * @param dto          接口入参
     * @param authResponse 返回参数
     */
    public void episodeFreeNum(PlayAuthDto dto, AuthResponse authResponse) {
        try {
            // 判断内容是否是剧集如果是剧集则判断是否前三集免费
            if (dto.getChild() != null) {
                // 判断内容是否配置前N集免费
                if (dto.getCatalogueRelation().getFreeNum() != null) {
                    // 判断当前集与免费集之间的大小关系
                    if (dto.getChild().getSort() <= dto.getCatalogueRelation().getFreeNum()) {
                        authResponse.pushLimitFreeSuccess(dto.getVideos());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("判断剧集是否前N集免费异常：" + e.getMessage(), e);
            throw new RuntimeException("判断剧集是否前N集免费异常" + e.getMessage(), e);
        }
    }


    /**
     * 系统流程2：
     * 是否栏目限免
     *
     * @param dto          接口入参
     * @param authResponse 返回参数
     * @return
     */
    public void limitFree(PlayAuthDto dto, AuthResponse authResponse) {
        try {
            boolean flag = limitFreeService.isFree(dto.getCatalogueRelation().getAId(), dto.getAppId());
            if (flag) {
                authResponse.pushLimitFreeSuccess(dto.getVideos());
            } else {
                authResponse.pushServerAuthFail(dto.getVideos());
            }
        } catch (Exception e) {
            logger.error("判断是否限时免费异常：" + e.getMessage(), e);
            throw new RuntimeException("判断是否限时免费异常" + e.getMessage(), e);
        }
    }

    /**
     * 系统流程3：
     * 判断服务开通情况
     *
     * @param dto          接口入参
     * @param authResponse 返回参数
     */
    public void spAuth(PlayAuthDto dto, AuthResponse authResponse) {
        try {
            // 获取栏目配置有效期内的产品
            List<Product> products = productRelService.findByBizId(dto.getCatalogueRelation().getAId());
            if (products.size() == 0) {
                // 栏目未配置单独产品定价
                // 根据serverCode、userId查询应用服务开通表，判断是否到期
                boolean flag = userServerService.checkUserServer(dto.getUserAccount().getUserId(),
                        "10001", dto.getAppId());
                if (!flag) {
                    // 服务已开通并且未过期,返回鉴权通过
                    authResponse.pushAuthSuccess(dto.getVideos());
                } else {
                    authResponse.pushServerAuthFail(dto.getVideos());
                }
            } else {
                // 栏目已配置产品定价
                // 调用底层逻辑判断产品订购情况
                boolean flag = orderService.orderAuth("10001", dto.getUserAccount().getUserId(),
                        dto.getAppId(), products);
                if (!flag) {
                    authResponse.pushAuthSuccess(dto.getVideos());
                } else {
                    authResponse.pushOrderAuthFail(products.get(0).getId(), dto.getVideos());
                }
            }
        } catch (Exception e) {
            logger.error("判断服务是否开通异常：" + e.getMessage(), e);
            throw new RuntimeException("判断服务是否开通异常" + e.getMessage(), e);
        }
    }
}
