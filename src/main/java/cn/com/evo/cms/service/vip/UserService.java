package cn.com.evo.cms.service.vip;

import cn.com.evo.cms.domain.entity.vip.User;
import com.frameworks.core.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends BaseService<User, String> {

    /**
     * 会员信息excel导入
     *
     * @param files
     * @param type
     */
    void dataImport(MultipartFile[] files, Integer type);

    /**
     * 服务开通
     *
     * @param files
     */
    void serverOpen(MultipartFile[] files);

}
