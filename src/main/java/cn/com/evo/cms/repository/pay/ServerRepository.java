package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.Server;
import com.frameworks.core.repository.BaseRepository;

public interface ServerRepository extends BaseRepository<Server, String> {
    Server getByCode(String code);
}
