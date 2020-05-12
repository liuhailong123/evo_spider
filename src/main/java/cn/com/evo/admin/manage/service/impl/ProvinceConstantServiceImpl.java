package cn.com.evo.admin.manage.service.impl;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.domain.entity.ProvinceConstant;
import cn.com.evo.admin.manage.repository.ProvinceConstantRepository;
import cn.com.evo.admin.manage.service.ProvinceConstantService;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.integration.common.ConstantFactory;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceConstantServiceImpl extends AbstractBaseService<ProvinceConstant, String> implements ProvinceConstantService {
    @Autowired
    private ProvinceConstantRepository provinceConstantRepository;
    @Autowired
    private ProvinceService provinceService;


    @Override
    protected BaseRepository<ProvinceConstant, String> getBaseRepository() {
        return provinceConstantRepository;
    }

    @Override
    public List<ProvinceConstant> findConstants() {
        Province province = provinceService.getByEnable(1);
        if (province == null) {
            throw new RuntimeException("无可用省网配置");
        }
        return provinceConstantRepository.findByProvinceIdAndEnable(province.getId(), 1);
    }

    @Override
    public void save(ProvinceConstant entity) {
        super.save(entity);

        // 更新常量配置信息
        ConstantFactory.map.put(entity.getConstantKey(), entity.getConstantValue());
    }

    @Override
    public void update(ProvinceConstant entity) {
        super.update(entity);

        // 更新常量配置信息
        ConstantFactory.map.put(entity.getConstantKey(), entity.getConstantValue());
    }

    @Override
    public void deleteById(String id) {
        ProvinceConstant provinceConstant = this.findById(id);
        String key = provinceConstant.getConstantKey();

        super.deleteById(id);

        ConstantFactory.map.remove(key);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }
}
