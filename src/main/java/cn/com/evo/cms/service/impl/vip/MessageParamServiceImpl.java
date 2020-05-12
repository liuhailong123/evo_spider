package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.cms.domain.entity.vip.MessageParam;
import cn.com.evo.cms.repository.vip.MessageParamRepository;
import cn.com.evo.cms.service.vip.MessageParamService;
import cn.com.evo.cms.utils.message.SendMsgUtils;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageParamServiceImpl extends AbstractBaseService<MessageParam, String> implements MessageParamService {
    @Autowired
    private MessageParamRepository vipMessageParamRepository;

    @Override
    protected BaseRepository<MessageParam, String> getBaseRepository() {
        return this.vipMessageParamRepository;
    }

    @Override
    public Boolean sendMsg(String phone, String code, int contentType) {
        try {
            List<MessageParam> vipMessageParams = vipMessageParamRepository.findByStatus(1);
            if (vipMessageParams.size() != 0) {
                // 获取软件序列号和密码
                MessageParam vipMessageParam = vipMessageParams.get(0);
                // 软件序列号
                String sn = vipMessageParam.getSn();
                // 密码
                String pwd = vipMessageParam.getPassword();
                String content = "";
                if (contentType == 0) {
                    // 验证码
                    content = vipMessageParam.getMessage().replace("code", code);
                } else if (contentType == 1) {
                    // 密码
                    content = code;
                }
                Boolean result = SendMsgUtils.sendMsg(sn, pwd, phone, content);
                return result;
            } else {
                throw new RuntimeException("未配置短信服务器信息");
            }
        } catch (Exception e) {
            throw new RuntimeException("短信发送异常：" + e.getMessage(), e);
        }
    }


}
