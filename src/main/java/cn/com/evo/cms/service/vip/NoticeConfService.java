package cn.com.evo.cms.service.vip;

import cn.com.evo.cms.domain.entity.vip.NoticeConf;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface NoticeConfService extends BaseService<NoticeConf, String> {


    List<NoticeConf> findByStatus(int status);
}
