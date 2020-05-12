package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.cms.domain.entity.vip.NoticeConf;
import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.repository.vip.NoticeConfRepository;
import cn.com.evo.cms.repository.vip.UserRepository;
import cn.com.evo.cms.service.vip.NoticeConfService;
import cn.com.evo.cms.service.vip.UserService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeConfServiceImpl extends AbstractBaseService<NoticeConf, String> implements NoticeConfService {

	@Autowired
	private NoticeConfRepository noticeConfRepository;


	@Override
	protected BaseRepository<NoticeConf, String> getBaseRepository() {
		return noticeConfRepository;
	}


	@Override
	public List<NoticeConf> findByStatus(int status) {
		return noticeConfRepository.findByStatus(status);
	}
}
