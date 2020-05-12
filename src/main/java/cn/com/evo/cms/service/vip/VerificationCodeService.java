package cn.com.evo.cms.service.vip;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.vip.VerificationCode;

public interface VerificationCodeService extends BaseService<VerificationCode, String> {

    void saveCode(VerificationCode vipVerificationCode);

    List<VerificationCode> findByPhoneAndCode(String phone, String code);

    List<VerificationCode> findByPhone(String phone);

    /**
     * 生成验证码
     *
     * @param phone
     * @return
     */
    String getCode(String phone);

    /**
     * 判断验证码是否正确或过期
     * @param phone
     * @param code
     * @return
     */
    Integer judgeCodeOK(String phone, String code);
}
