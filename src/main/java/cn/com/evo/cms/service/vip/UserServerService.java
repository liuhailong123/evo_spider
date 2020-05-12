package cn.com.evo.cms.service.vip;

import cn.com.evo.cms.domain.entity.vip.UserServer;
import cn.com.evo.cms.domain.vo.vip.UserServerSearchVo;
import cn.com.evo.cms.domain.vo.vip.UserServerVo;
import com.frameworks.core.service.BaseService;
import com.frameworks.core.web.page.Pager;

import java.util.List;

public interface UserServerService extends BaseService<UserServer, String> {

    /**
     * 服务开通
     *
     * @param userId    用户id
     * @param appId     应用Id
     * @param productId 产品id
     */
    List<UserServer> openServer(String userId, String appId, String productId);

    /**
     * 服务开通
     *
     * @param userId    用户id
     * @param appId     应用Id
     * @param productId 产品id
     * @param beginTime 服务开始时间
     * @param endTime   服务结束时间
     */
    void openServer(String userId, String appId, String productId, String beginTime, String endTime);

    /**
     * 服务关闭
     *
     * @param userId    用户id
     * @param appId     应用id
     * @param productId 产品id
     */
    void closeServer(String userId, String appId, String productId);

    /**
     * 检测用户服务是否到期
     *
     * @param userId
     * @param serverCode
     * @param appId
     * @return
     */
    boolean checkUserServer(String userId, String serverCode, String appId);

    /**
     * 根据用户id 应用id 获取用户服务到期时间
     *
     * @param userId
     * @param appId
     * @return
     */
    String getMaturityTimeByUserIdAndAppId(String userId, String appId);


    /**
     * 根据用户id、应用id、服务编码 获取用户服务对象
     *
     * @param userId 用户id
     * @param appId  应用id
     * @return
     */
    List<UserServer> findByUserIdAndAppId(String userId, String appId);

    /**
     * 根据用户id，应用id，服务编码 获取用户开通的服务
     *
     * @param userId
     * @param appId
     * @param serverCode
     * @return
     */
    UserServer getByUserIdAndAppIdAndServerCode(String userId, String appId, String serverCode);

    /**
     * 判断用户是否有未到期服务
     *
     * @param userId
     * @param appId
     * @return
     */
    Boolean checkUserServerIsExpire(String userId, String appId);

    /**
     * view搜索
     *
     * @param webPage
     * @param userServerSearchVo
     * @return
     */
    List<UserServerVo> findByPage(Pager webPage, UserServerSearchVo userServerSearchVo);
}
