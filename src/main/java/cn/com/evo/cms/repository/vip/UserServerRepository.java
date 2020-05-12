package cn.com.evo.cms.repository.vip;

import cn.com.evo.cms.domain.entity.vip.UserServer;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface UserServerRepository extends BaseRepository<UserServer, String> {

    UserServer getByUserIdAndAppIdAndServerCode(String userId, String appId, String serverCode);

    List<UserServer> findByUserIdAndAppIdAndServerCode(String userId, String appId, String serverCode);

    List<UserServer> findByUserIdAndAppIdOrderByMaturityTimeDesc(String userId, String appId);
}
