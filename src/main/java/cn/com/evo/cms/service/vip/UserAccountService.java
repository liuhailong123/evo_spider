package cn.com.evo.cms.service.vip;

import cn.com.evo.cms.domain.entity.vip.UserAccount;
import com.frameworks.core.service.BaseService;

public interface UserAccountService extends BaseService<UserAccount, String> {


    /**
     * 通过账号，账号类型获取用户账号设备对象
     *
     * @param accountNo   账号
     * @param accountType 1 手机号; 2 微信号; 3 机顶盒卡号
     * @param stbNo       机顶盒号
     * @return
     */
    UserAccount getByAccountNoAndAccountType(String accountNo, Integer accountType, String stbNo);

    /**
     * 通过用户id，账号类型获取用户账号设备对象
     *
     * @param userId
     * @param accountType 1 手机号; 2 微信号; 3 机顶盒卡号
     * @return
     */
    UserAccount getUserIdAndAccountType(String userId, int accountType);

    /**
     * 根据用户id删除相关用户账户数据
     *
     * @param userId
     */
    void deleteByUserId(String userId);
}
