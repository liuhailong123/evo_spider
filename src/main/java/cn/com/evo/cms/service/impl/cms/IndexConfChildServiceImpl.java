package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.IndexConf;
import cn.com.evo.cms.domain.entity.cms.IndexConfChild;
import cn.com.evo.cms.repository.cms.IndexConfChildRepository;
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
public class IndexConfChildServiceImpl extends AbstractBaseService<IndexConfChild, String> implements IndexConfChildService {

    @Autowired
    private IndexConfChildRepository indexConfChildRepository;

    @Override
    protected BaseRepository<IndexConfChild, String> getBaseRepository() {
        return indexConfChildRepository;
    }


    @Override
    public List<IndexConfChild> findByIndexConfId(String indexConfId) {
        return indexConfChildRepository.findByIndexConfIdOrderByPositionAsc(indexConfId);
    }

    @Override
    public void save(IndexConfChild entity) {
        if (entity.getUpPx() == null) {
            entity.setUpPx(0);
        }
        if (entity.getDownPx() == null) {
            entity.setDownPx(0);
        }
        if (entity.getLeftPx() == null) {
            entity.setLeftPx(0);
        }
        if (entity.getRightPx() == null) {
            entity.setRightPx(0);
        }
        super.save(entity);
    }
}
