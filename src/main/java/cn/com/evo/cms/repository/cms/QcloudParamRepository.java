package cn.com.evo.cms.repository.cms;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.cms.QcloudParam;

public interface QcloudParamRepository extends BaseRepository<QcloudParam, String> {
	List<QcloudParam> findByAuthId(String authId);
	
	QcloudParam getByAuthIdAndEnable(String authId,Integer enable);
}
