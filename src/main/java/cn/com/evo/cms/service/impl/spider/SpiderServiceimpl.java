package cn.com.evo.cms.service.impl.spider;

import cn.com.evo.cms.domain.entity.spider.Spider;
import cn.com.evo.cms.repository.spider.SpiderRepository;
import cn.com.evo.cms.service.spider.SpiderService;
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
public class SpiderServiceimpl extends AbstractBaseService<Spider, String> implements SpiderService {
    @Autowired
    private SpiderRepository spiderRepository;
    @Override
    protected BaseRepository<Spider, String> getBaseRepository() {
        return spiderRepository;
    }
}
