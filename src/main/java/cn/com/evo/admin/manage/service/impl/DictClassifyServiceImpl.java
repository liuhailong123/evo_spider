package cn.com.evo.admin.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.exception.ExistedException;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.admin.manage.domain.entity.DictClassify;
import cn.com.evo.admin.manage.repository.DictClassifyRepository;
import cn.com.evo.admin.manage.service.DictClassifyService;

@Service
@Transactional
public class DictClassifyServiceImpl extends AbstractBaseService<DictClassify, String> implements DictClassifyService {

    @Autowired
    private DictClassifyRepository dictClassifyRepository;

    @Override
    public DictClassify findByCode(String code) {
        return dictClassifyRepository.findByCode(code);
    }

    @Override
    public void save(DictClassify entity) {
        DictClassify classify = this.findByCode(entity.getCode());
        if (null != classify) {
            throw new ExistedException("字典分类编码已存在!");
        }
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(DictClassify entity) {
        DictClassify classify = super.findById(entity.getId());
        entity.setCreateDate(classify.getCreateDate());
        entity.setDictData(classify.getDictData());
        super.saveOrUpdate(entity);
    }

    @Override
    protected BaseRepository<DictClassify, String> getBaseRepository() {
        return dictClassifyRepository;
    }

}
