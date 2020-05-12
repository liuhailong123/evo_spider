package cn.com.evo.cms.service.cms;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.cms.ThirdPartySynchronous;

public interface ThirdPartySynchronousService extends BaseService<ThirdPartySynchronous, String> {

	ThirdPartySynchronous findByFidAndThirdPartyNameAndType(String fid, String thirdPartyName, String type);

	void save(String contentId, String thirdPartyName, String contentType, String retCode, String retMsg);

}
