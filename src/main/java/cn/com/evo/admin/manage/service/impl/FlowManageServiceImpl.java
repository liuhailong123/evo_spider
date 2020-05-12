package cn.com.evo.admin.manage.service.impl;

import cn.com.evo.admin.manage.domain.entity.FlowManage;
import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.repository.FlowManageRepository;
import cn.com.evo.admin.manage.service.FlowManageService;
import cn.com.evo.admin.manage.service.ProvinceService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class FlowManageServiceImpl extends AbstractBaseService<FlowManage, String> implements FlowManageService {
    @Autowired
    private FlowManageRepository flowManageRepository;
    @Autowired
    private ProvinceService provinceService;


    @Override
    protected BaseRepository<FlowManage, String> getBaseRepository() {
        return flowManageRepository;
    }

    @Override
    public List<FlowManage> findByProvinceIdAndType(String provinceId, Integer type) {
        return flowManageRepository.findByProvinceIdAndTypeOrderBySort(provinceId, type);
    }

    @Override
    public void copy(String code, String[] ids) {
        Province province = provinceService.getByCode(code);
        if (province == null) {
            throw new RuntimeException("省网不存在！！！");
        }

        for (String id : ids) {
            FlowManage entity = this.findById(id);

            FlowManage temp = new FlowManage();
            BeanUtils.copyProperties(entity, temp);

            temp.setId(null);
            temp.setProvince(province);
            this.save(temp);
        }
    }
}
