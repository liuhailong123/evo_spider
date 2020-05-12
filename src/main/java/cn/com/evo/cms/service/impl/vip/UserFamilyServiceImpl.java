package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.cms.domain.entity.vip.UserFamily;
import cn.com.evo.cms.repository.vip.UserFamilyRepository;
import cn.com.evo.cms.service.vip.UserFamilyService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserFamilyServiceImpl extends AbstractBaseService<UserFamily, String> implements UserFamilyService {

    @Autowired
    private UserFamilyRepository userFamilyRepository;

    @Override
    protected BaseRepository<UserFamily, String> getBaseRepository() {
        return userFamilyRepository;
    }

    @Override
    public UserFamily getByUserId(String userId) {
        return userFamilyRepository.getByUserId(userId);
    }

    @Override
    public void saveOrUpdateEntity(UserFamily userFamily) {
        UserFamily temp = this.getByUserId(userFamily.getUserId());
        if (StringUtils.isBlank(userFamily.getId())) {
            if (temp == null) {
                super.save(userFamily);
            } else {
                throw new RuntimeException("已存在相关家属信息!!!");
            }
        } else {
            userFamily.setCreateDate(temp.getCreateDate());
            super.update(userFamily);
        }
    }
}
