package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.cms.domain.entity.cms.Active;
import cn.com.evo.cms.domain.entity.cms.ActiveApply;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.repository.cms.ActiveApplyRepository;
import cn.com.evo.cms.repository.cms.ActiveRepository;
import cn.com.evo.cms.service.cms.ActiveApplyService;
import cn.com.evo.cms.service.cms.ActiveService;
import cn.com.evo.cms.service.cms.SourceRelService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActiveApplyServiceImpl extends AbstractBaseService<ActiveApply, String> implements ActiveApplyService {

    @Autowired
    private ActiveApplyRepository activeApplyRepository;

    @Override
    protected BaseRepository<ActiveApply, String> getBaseRepository() {
        return activeApplyRepository;
    }


    @Override
    public ActiveApply getByActiveIdAndUserId(String activeId, String userId) {
        return activeApplyRepository.getByActiveIdAndUserId(activeId, userId);
    }
}
