package cn.com.evo.cms.service.impl.spider;

import cn.com.evo.cms.domain.entity.spider.SpiderConstant;
import cn.com.evo.cms.domain.entity.spider.SpiderContentChild;
import cn.com.evo.cms.repository.spider.SpiderConstantRepository;
import cn.com.evo.cms.service.spider.SpiderConstantService;
import cn.com.evo.cms.service.spider.SpiderContentChildService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rf
 * @date 2020/4/28
 */
@Service
@Transactional
public class SpiderConstantServiceImpl extends AbstractBaseService<SpiderConstant, String> implements SpiderConstantService {
    @Autowired
    private SpiderConstantRepository spiderConstantRepository;
    @Override
    protected BaseRepository<SpiderConstant, String> getBaseRepository() {
        return spiderConstantRepository;
    }
}
