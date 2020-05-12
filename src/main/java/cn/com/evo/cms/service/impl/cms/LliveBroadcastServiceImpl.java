package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.IndexConfChild;
import cn.com.evo.cms.domain.entity.cms.LliveBroadcast;
import cn.com.evo.cms.repository.cms.IndexConfChildRepository;
import cn.com.evo.cms.repository.cms.LliveBroadcastRepository;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.LliveBroadcastService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LliveBroadcastServiceImpl extends AbstractBaseService<LliveBroadcast, String> implements LliveBroadcastService {

    @Autowired
    private LliveBroadcastRepository lliveBroadcastRepository;
    @Autowired
    private IndexConfChildRepository indexConfChildRepository;
    @Autowired
    private CatalogueRelationService catalogueRelationService;


    @Override
    protected BaseRepository<LliveBroadcast, String> getBaseRepository() {
        return lliveBroadcastRepository;
    }

    @Override
    public void deleteById(String id) {
        // 判断内容是否挂载至栏目
        List<CatalogueRelation> list = catalogueRelationService.findByBId(id);
        if (list.size() > 0) {
            throw new RuntimeException("该内容已被挂载至栏目，请先断开相关关系后，再进行删除！！！");
        }

        // 检测内容是否被挂载至首页
        List<IndexConfChild> indexConfChildren = indexConfChildRepository.findByContentId(id);
        if (indexConfChildren.size() > 0) {
            throw new RuntimeException(" 该内容已被挂载至首页！请先断开相关关系后，再进行删除！！！");
        }

        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }
}
