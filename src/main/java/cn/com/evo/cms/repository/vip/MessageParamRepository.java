package cn.com.evo.cms.repository.vip;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.vip.MessageParam;

public interface MessageParamRepository extends BaseRepository<MessageParam, String> {

	List<MessageParam>  findByStatus(int i);
	
}
