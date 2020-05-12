package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.constants.WebConsts;

import cn.com.evo.admin.manage.domain.entity.Area;
import cn.com.evo.admin.manage.repository.AreaRepository;
import cn.com.evo.admin.manage.service.AreaService;

@Service
@Transactional
public class AreaServiceImpl extends AbstractBaseService<Area, String> implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Override
    protected BaseRepository<Area, String> getBaseRepository() {
        return areaRepository;
    }

    @Override
    public void update(Area entity) {
        Area area = this.findById(entity.getId());
        if (entity.getLevel() == WebConsts.DEFAULT_ROOT_LEVEL) {
            entity.setParent(null);
        }
        entity.setChildren(area.getChildren());
        entity.setCreateDate(area.getCreateDate());
        super.update(entity);
    }

    @Override
    public List<Area> findByParentId(String parentId) {
        return areaRepository.findByParentId(parentId);
    }

}
