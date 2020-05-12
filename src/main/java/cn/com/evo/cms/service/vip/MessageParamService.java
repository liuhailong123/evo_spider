package cn.com.evo.cms.service.vip;

import cn.com.evo.cms.domain.entity.vip.MessageParam;
import com.frameworks.core.service.BaseService;

public interface MessageParamService extends BaseService<MessageParam, String> {
    /**
     * 发送短信方法
     *
     * @param phone
     * @param code
     * @param contentType
     * @return
     */
    Boolean sendMsg(String phone, String code, int contentType);
}
