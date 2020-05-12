package cn.com.evo.cms.repository.spider;

import cn.com.evo.cms.domain.entity.spider.SpiderContent;
import com.frameworks.core.repository.BaseRepository;

/**
 * @author rf
 * @date 2020/4/28
 */
public interface SpiderContentRepository extends BaseRepository<SpiderContent, String> {
    SpiderContent getById(String sId);
}
