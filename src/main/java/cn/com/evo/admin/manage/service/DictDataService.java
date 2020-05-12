package cn.com.evo.admin.manage.service;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.DictData;

public interface DictDataService extends BaseService<DictData, String> {

    List<DictData> findByDictClassifyId(String classifyId);

    List<DictData> findByDictClassifyCode(String classifyCode);
}
