package cn.com.evo.cms.service.spider;

import cn.com.evo.cms.domain.entity.spider.Spider;
import cn.com.evo.cms.domain.entity.spider.SpiderContent;
import cn.com.evo.cms.web.api.vo.ArticleApiVo;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.BaseService;

/**
 * @author rf
 * @date 2020/4/28
 */
public interface SpiderContentService extends BaseService<SpiderContent, String> {
    SpiderContent getById(String sId);

}
