package cn.com.evo.admin.manage.service;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.DictClassify;

public interface DictClassifyService extends BaseService<DictClassify, String> {

    DictClassify findByCode(String code);

}
