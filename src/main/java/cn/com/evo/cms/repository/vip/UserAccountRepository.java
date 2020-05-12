package cn.com.evo.cms.repository.vip;

import cn.com.evo.cms.domain.entity.vip.UserAccount;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface UserAccountRepository extends BaseRepository<UserAccount, String> {

    List<UserAccount> findByAccountNoAndAccountType(String accountNo, Integer accountType);

    UserAccount getByUserIdAndAccountType(String userId, int accountType);

    List<UserAccount> findByUserId(String userId);
}
