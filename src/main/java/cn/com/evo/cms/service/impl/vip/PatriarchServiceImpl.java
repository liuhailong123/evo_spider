package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.cms.domain.entity.vip.Patriarch;
import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.repository.vip.PatriarchRepository;
import cn.com.evo.cms.repository.vip.UserRepository;
import cn.com.evo.cms.service.vip.PatriarchService;
import cn.com.evo.cms.service.vip.UserService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatriarchServiceImpl extends AbstractBaseService<Patriarch, String> implements PatriarchService {

	@Autowired
	private PatriarchRepository patriarchRepository;


	@Override
	protected BaseRepository<Patriarch, String> getBaseRepository() {
		return patriarchRepository;
	}



}
