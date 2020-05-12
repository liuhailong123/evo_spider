package cn.com.evo.cms.service.impl.vip;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.cms.domain.entity.vip.VerificationCode;
import cn.com.evo.cms.repository.vip.VerificationCodeRepository;
import cn.com.evo.cms.service.vip.VerificationCodeService;

@Service
@Transactional
public class VerificationCodeServiceImpl extends AbstractBaseService<VerificationCode, String> implements VerificationCodeService {
    @Autowired
    private VerificationCodeRepository vipVerificationCodeRepository;

    @Override
    protected BaseRepository<VerificationCode, String> getBaseRepository() {
        return this.vipVerificationCodeRepository;
    }

    @Override
    public void saveCode(VerificationCode vipVerificationCode) {
        List<VerificationCode> entitys = vipVerificationCodeRepository.findByPhone(vipVerificationCode.getPhone());
        if (entitys.size() > 0) {
            vipVerificationCode.setId(entitys.get(0).getId());
            this.save(vipVerificationCode);
        } else {
            this.save(vipVerificationCode);
        }
    }

    @Override
    public List<VerificationCode> findByPhoneAndCode(String phone, String code) {

        List<VerificationCode> entitys = vipVerificationCodeRepository.findByPhoneAndCodeOrderByCreateDateDesc(phone, code);
        return entitys;
    }

    @Override
    public List<VerificationCode> findByPhone(String phone) {

        return vipVerificationCodeRepository.findByPhone(phone);
    }

    @Override
    public String getCode(String phone) {
        String result = "";
        List<VerificationCode> verificationCodes = this.findByPhone(phone);
        if (verificationCodes.size() > 0) {
            Long time1 = System.currentTimeMillis();
            Long time2 = verificationCodes.get(0).getInvalid().longValue();
            Long time3 = time2 - time1;
            if (time3 > 0) {
                result = verificationCodes.get(0).getCode();
            } else {
                int max = 9999;
                int min = 1000;
                Random random = new Random();
                int s = random.nextInt(max) % (max - min + 1) + min;
                result = String.valueOf(s);
            }
        } else {
            int max = 9999;
            int min = 1000;
            Random random = new Random();
            int s = random.nextInt(max) % (max - min + 1) + min;
            result = String.valueOf(s);
        }
        return String.valueOf(result);
    }

    @Override
    public Integer judgeCodeOK(String phone, String code) {
        Integer result = 0;//验证码正确
        List<VerificationCode> vipVerificationCodes = this.findByPhoneAndCode(phone, code);
        if (vipVerificationCodes.size() > 0) {
            String code2 = vipVerificationCodes.get(0).getCode();
            if (code.equals(code2)) {// 验证码正确判断是否过期(验证码有效时间2分钟)
                Long time1 = System.currentTimeMillis();
                Long time2 = vipVerificationCodes.get(0).getInvalid().longValue();
                Long time3 = time2 - time1;
                if (time3 > 0) {// 没过期
                } else {// 过期
                    result = 2;//验证码过期
                }
            } else {
                result = 3;//验证码错误
            }
        } else {
            result = 3;//验证码错误
        }
        return result;
    }

}
