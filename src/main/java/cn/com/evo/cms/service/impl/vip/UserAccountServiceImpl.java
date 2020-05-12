package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.repository.vip.UserAccountRepository;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAccountServiceImpl extends AbstractBaseService<UserAccount, String> implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserService userService;

    @Override
    protected BaseRepository<UserAccount, String> getBaseRepository() {
        return userAccountRepository;
    }


    @Override
    public UserAccount getByAccountNoAndAccountType(String accountNo, Integer accountType, String stbNo) {
        List<UserAccount> userAccounts = userAccountRepository.findByAccountNoAndAccountType(accountNo, accountType);
        if (userAccounts.size() == 0) {
            UserAccount userAccount = new UserAccount();
            //创建用户
            User user = new User();
            user.setSex(0);//保密
            user.setStatus(1);//正常
            user.setQqNum(0);//初始化
            userService.save(user);
            //创建用户设备账号
            userAccount.setUserId(user.getId());
            userAccount.setAccountType(accountType);
            userAccount.setAccountNo(accountNo);
            //机顶盒
            userAccount.setEquipmentType(2);
            //机顶盒号
            userAccount.setEquipmentId(stbNo);
            this.save(userAccount);
            return userAccount;
        } else {
            return userAccounts.get(0);
        }
    }

    @Override
    public UserAccount getUserIdAndAccountType(String userId, int accountType) {
        return userAccountRepository.getByUserIdAndAccountType(userId, accountType);
    }

    @Override
    public void deleteByUserId(String userId) {
        List<UserAccount> accounts = userAccountRepository.findByUserId(userId);
        this.deleteByEntities(accounts);
    }
}
