package cn.com.evo.admin.manage.repository;

import cn.com.evo.admin.manage.domain.entity.FlowManage;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface FlowManageRepository extends BaseRepository<FlowManage, String> {

    /**
     * 获取某省网流程配置（按照sort排序）
     *
     * @param provinceId 省网id
     * @param type       流程类型；1-播放流程；2-查询产品信息流程
     * @return
     */
    List<FlowManage> findByProvinceIdAndTypeOrderBySort(String provinceId, Integer type);
}
