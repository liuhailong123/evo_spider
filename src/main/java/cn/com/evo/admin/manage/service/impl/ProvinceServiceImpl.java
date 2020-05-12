package cn.com.evo.admin.manage.service.impl;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.domain.entity.ProvinceConstant;
import cn.com.evo.admin.manage.repository.ProvinceRepository;
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
public class ProvinceServiceImpl extends AbstractBaseService<Province, String> implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private ProvinceConstantService provinceConstantService;


    @Override
    protected BaseRepository<Province, String> getBaseRepository() {
        return provinceRepository;
    }

    @Override
    public void save(Province entity) {
        setDataEnable(entity);

        super.save(entity);

        setConstantFactory();
    }

    @Override
    public void update(Province entity) {
        setDataEnable(entity);

        super.update(entity);

        setConstantFactory();
    }

    /**
     * 设置启用状态,至保留唯一一个启用状态的数据
     *
     * @param entity
     */
    protected void setDataEnable(Province entity) {
        if (entity.getEnable() == 1) {
            Province province = provinceRepository.getByEnable(1);
            if (province != null) {
                province.setEnable(0);
                provinceRepository.save(province);
            }
        }
    }

    @Override
    public Province getByEnable(Integer enable) {
        return provinceRepository.getByEnable(enable);
    }

    @Override
    public String getNowProviceCode() {
        Province entity = getByEnable(1);
        if (entity != null) {
            return entity.getCode();
        } else {
            return null;
        }
    }

    @Override
    public Province getByCode(String code) {
        return provinceRepository.getByCode(code);
    }

    private void setConstantFactory() {
        try {
            List<ProvinceConstant> provinceConstants = provinceConstantService.findConstants();

            ConstantFactory.map.clear();

            for (ProvinceConstant provinceConstant : provinceConstants) {
                ConstantFactory.map.put(provinceConstant.getConstantKey(), provinceConstant.getConstantValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
