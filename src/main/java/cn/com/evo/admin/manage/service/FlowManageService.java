package cn.com.evo.admin.manage.service;

import cn.com.evo.admin.manage.domain.entity.FlowManage;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface FlowManageService extends BaseService<FlowManage, String> {

    List<FlowManage> findByProvinceIdAndType(String provinceId, Integer type);

    /**
     * 复制流程配置
     *
     * @param code 省网编码
     * @param ids  流程配置ids
     */
    void copy(String code, String[] ids);
}
