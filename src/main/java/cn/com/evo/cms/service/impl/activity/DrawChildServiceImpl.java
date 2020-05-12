package cn.com.evo.cms.service.impl.activity;

import cn.com.evo.cms.domain.entity.activity.DrawChild;
import cn.com.evo.cms.repository.activity.DrawChildRepository;
import cn.com.evo.cms.service.activity.DrawChildService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DrawChildServiceImpl extends AbstractBaseService<DrawChild, String> implements DrawChildService {

    @Autowired
    private DrawChildRepository drawChildRepository;

    @Override
    protected BaseRepository<DrawChild, String> getBaseRepository() {
        return drawChildRepository;
    }

    @Override
    public void save(DrawChild entity) {
        entity.setNowCount(entity.getCount());
        super.save(entity);
    }

    @Override
    public List<DrawChild> findByDrawIdAndEnable(String drawId, Integer enable) {
        return drawChildRepository.findByDrawIdAndEnableOrderBySort(drawId, enable);
    }
}
