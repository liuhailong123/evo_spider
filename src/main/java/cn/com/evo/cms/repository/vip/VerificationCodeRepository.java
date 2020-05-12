package cn.com.evo.cms.repository.vip;

import java.util.List;

import com.frameworks.core.repository.BaseRepository;

import cn.com.evo.cms.domain.entity.vip.VerificationCode;

public interface VerificationCodeRepository extends BaseRepository<VerificationCode, String> {
	List<VerificationCode> findByPhone(String phone);
	
	List<VerificationCode> findByPhoneAndCodeOrderByCreateDateDesc(String phone,String code);
}
