package cn.com.evo.cms.repository.cms;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.cms.ThirdPartySynchronous;

public interface ThirdPartySynchronousRepository extends BaseRepository<ThirdPartySynchronous, String> {

	ThirdPartySynchronous findByFidAndThirdPartyNameAndType(String fid, String thirdPartyName, String type);

}
