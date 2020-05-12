package cn.com.evo.admin.manage.service;

import cn.com.evo.admin.manage.domain.entity.Province;
import com.frameworks.core.service.BaseService;

public interface ProvinceService extends BaseService<Province, String> {

    Province getByEnable(Integer enable);

    /**
     * 获取当前启用的省网编码
     *
     * @return
     */
    String getNowProviceCode();

    Province getByCode(String code);
}
