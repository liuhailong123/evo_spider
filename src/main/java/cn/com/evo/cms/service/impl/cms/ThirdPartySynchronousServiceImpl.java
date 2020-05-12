package cn.com.evo.cms.service.impl.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.cms.domain.entity.cms.ThirdPartySynchronous;
import cn.com.evo.cms.repository.cms.ThirdPartySynchronousRepository;
import cn.com.evo.cms.service.cms.ThirdPartySynchronousService;

@Service
@Transactional
public class ThirdPartySynchronousServiceImpl extends AbstractBaseService<ThirdPartySynchronous, String>
		implements ThirdPartySynchronousService {

	@Autowired
	private ThirdPartySynchronousRepository thirdPartySynchronousRepository;

	@Override
	protected BaseRepository<ThirdPartySynchronous, String> getBaseRepository() {
		return thirdPartySynchronousRepository;
	}

	@Override
	public ThirdPartySynchronous findByFidAndThirdPartyNameAndType(String fid, String thirdPartyName, String type) {
		return thirdPartySynchronousRepository.findByFidAndThirdPartyNameAndType(fid, thirdPartyName, type);
	}

	@Override
	public void save(String contentId, String thirdPartyName, String contentType, String retCode, String retMsg) {

		ThirdPartySynchronous thirdPartySynchronous = this.findByFidAndThirdPartyNameAndType(contentId, thirdPartyName,
				contentType);
		if (thirdPartySynchronous == null) {
			thirdPartySynchronous = new ThirdPartySynchronous();
		}
		thirdPartySynchronous.setFid(contentId);
		thirdPartySynchronous.setType(contentType);
		thirdPartySynchronous.setThirdPartyName(thirdPartyName);
		thirdPartySynchronous.setRetCode(retCode);
		thirdPartySynchronous.setRetMsg(retMsg);
		this.save(thirdPartySynchronous);
	}

}
