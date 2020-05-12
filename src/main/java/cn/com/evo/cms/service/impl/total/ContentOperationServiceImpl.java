package cn.com.evo.cms.service.impl.total;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.constant.ProvinceCodeEnum;
import cn.com.evo.cms.domain.entity.total.ContentOperation;
import cn.com.evo.cms.repository.total.ContentOperationRepository;
import cn.com.evo.cms.service.total.ContentOperationService;
import cn.com.evo.integration.scyd.content.ScydWebserviceSDK;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContentOperationServiceImpl extends AbstractBaseService<ContentOperation, String> implements ContentOperationService {
    @Autowired
    private ContentOperationRepository contentOperationRepository;
    @Autowired
    private ProvinceService provinceService;

    @Override
    protected BaseRepository<ContentOperation, String> getBaseRepository() {
        return contentOperationRepository;
    }

    @Override
    public List<ContentOperation> findByStatus(Integer status) {
        return contentOperationRepository.findByStatus(status);
    }

    @Override
    public ContentOperation getByCorrelateId(String correlateId) {
        return contentOperationRepository.getByCorrelateId(correlateId);
    }

    @Override
    public List<ContentOperation> findByContentIdOrderByCreateDateDesc(String contentId) {
        return contentOperationRepository.findByContentIdOrderByCreateDateDesc(contentId);
    }

    @Override
    public void send(String id, Province province) {
        // 省网code枚举
        ProvinceCodeEnum provinceCodeEnum = ProvinceCodeEnum.getByName(province.getCode());

        // 获取对象
        ContentOperation contentOperation = this.findById(id);
        if (contentOperation != null) {
            switch (provinceCodeEnum) {
                case SiChuanYiDong:
                    ScydWebserviceSDK.callCTMS(contentOperation.getCorrelateId(), contentOperation.getInfo());
                    contentOperation.setStatus(1);
                    super.update(contentOperation);
                    break;
                default:
                    break;
            }
        } else {
            throw new RuntimeException("找不到处理的内容注入单!!!");
        }
    }

    @Override
    public void sends(String[] ids) {
        Province province = provinceService.getByEnable(1);
        if (province == null) {
            throw new RuntimeException("找不到可用的省网配置");
        }

        for (String id : ids) {
            this.send(id, province);
        }
    }
}
