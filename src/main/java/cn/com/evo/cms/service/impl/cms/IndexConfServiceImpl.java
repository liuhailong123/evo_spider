package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.IndexConf;
import cn.com.evo.cms.domain.entity.cms.IndexConfChild;
import cn.com.evo.cms.repository.cms.IndexConfRepository;
import cn.com.evo.cms.service.cms.IndexConfChildService;
import cn.com.evo.cms.service.cms.IndexConfService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndexConfServiceImpl extends AbstractBaseService<IndexConf, String> implements IndexConfService {

    @Autowired
    private IndexConfRepository indexConfRepository;

    @Autowired
    private IndexConfChildService indexConfChildService;

    @Override
    protected BaseRepository<IndexConf, String> getBaseRepository() {
        return indexConfRepository;
    }

    @Override
    public void deleteById(String id) {
        List<IndexConfChild> indexConfChildrens = indexConfChildService.findByIndexConfId(id);
        if (indexConfChildrens != null) {
            if (indexConfChildrens.size() > 0) {
                throw new RuntimeException("给首页配置下有子配置，请先删除子配置");
            }
        }
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            this.deleteById(ids[i]);
        }
    }

    @Override
    public IndexConf getByAppId(String appId) {
        return indexConfRepository.getByAppId(appId);
    }
}
