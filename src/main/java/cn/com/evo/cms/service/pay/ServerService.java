package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.Server;
import com.frameworks.core.service.BaseService;

public interface ServerService extends BaseService<Server, String> {
    Server getByCode(String code);
}
