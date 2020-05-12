package cn.com.evo.admin.manage.repository;

import cn.com.evo.admin.manage.domain.entity.Province;
import com.frameworks.core.repository.BaseRepository;

public interface ProvinceRepository extends BaseRepository<Province, String> {
    Province getByEnable(Integer enable);

    Province getByCode(String code);
}
