package cn.com.evo.cms.service.impl.spider;

import cn.com.evo.cms.domain.entity.spider.SpiderContentChild;
import cn.com.evo.cms.repository.spider.SpiderContentChildRepository;
import cn.com.evo.cms.service.spider.SpiderContentChildService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rf
 * @date 2020/4/28
 */
@Service
@Transactional
public class SpiderContentChildServiceImpl extends AbstractBaseService<SpiderContentChild, String> implements SpiderContentChildService {
    @Autowired
    private SpiderContentChildRepository spiderContentChildRepository;
    @Override
    protected BaseRepository<SpiderContentChild, String> getBaseRepository() {
        return spiderContentChildRepository;
    }
}
