package cn.com.evo.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;

import cn.com.evo.admin.manage.domain.entity.DictData;
import cn.com.evo.admin.manage.repository.DictDataRepository;
import cn.com.evo.admin.manage.service.DictDataService;

@Service
@Transactional
public class DictDataServiceImpl extends AbstractBaseService<DictData, String> implements DictDataService {

    @Autowired
    private DictDataRepository dictDataRepository;

    @Override
    public List<DictData> findByDictClassifyId(String classifyId) {
        return dictDataRepository.findByDictClassifyId(classifyId);
    }

    @Override
    public List<DictData> findByDictClassifyCode(String classifyCode) {
        return dictDataRepository.findByDictClassifyCode(classifyCode);
    }

    @Override
    public void save(DictData entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(DictData entity) {
        DictData dictData = super.findById(entity.getId());
        entity.setCreateDate(dictData.getCreateDate());
        super.saveOrUpdate(entity);
    }

    @Override
    protected BaseRepository<DictData, String> getBaseRepository() {
        return dictDataRepository;
    }

}
