package cn.com.evo.cms.repository.vip;

import cn.com.evo.cms.domain.entity.vip.NoticeConf;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface NoticeConfRepository extends BaseRepository<NoticeConf, String>{

    List<NoticeConf> findByStatus(int status);
}
