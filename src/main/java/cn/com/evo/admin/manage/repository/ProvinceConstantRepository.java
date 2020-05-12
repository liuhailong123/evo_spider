package cn.com.evo.admin.manage.repository;

import cn.com.evo.admin.manage.domain.entity.ProvinceConstant;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface ProvinceConstantRepository extends BaseRepository<ProvinceConstant, String> {

    /**
     * 根据省网配置id和常量key获取配置
     *
     * @param key
     * @param provinceId
     * @return
     */
    ProvinceConstant getByConstantKeyAndProvinceId(String key, String provinceId);

    /**
     * 获取某省网启用常量配置
     *
     * @param provinceId
     * @param enable
     * @return
     */
    List<ProvinceConstant> findByProvinceIdAndEnable(String provinceId, Integer enable);
}
