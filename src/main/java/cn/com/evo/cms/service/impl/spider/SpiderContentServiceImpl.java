package cn.com.evo.cms.service.impl.spider;

import cn.com.evo.cms.domain.entity.spider.SpiderContent;
import cn.com.evo.cms.repository.spider.SpiderContentRepository;
import cn.com.evo.cms.service.spider.SpiderContentService;
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
public class SpiderContentServiceImpl extends AbstractBaseService<SpiderContent, String> implements SpiderContentService {
    @Autowired
    private SpiderContentRepository spiderContentRepository;
    @Override
    protected BaseRepository<SpiderContent, String> getBaseRepository() {
        return spiderContentRepository;
    }

    @Override
    public SpiderContent getById(String sId) {
        return spiderContentRepository.getById(sId);
    }
}
