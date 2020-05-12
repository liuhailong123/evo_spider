package cn.com.evo.cms.service.cms;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.cms.QcloudParam;

public interface QcloudParamService extends BaseService<QcloudParam, String> {
	void changeMain(String id);
	/**
	 * 获取腾讯云基础参数配置
	 * @return
	 */
	QcloudParam getParam();
	
	QcloudParam getParam(String authId);
}
