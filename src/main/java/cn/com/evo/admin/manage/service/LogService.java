package cn.com.evo.admin.manage.service;

import com.frameworks.core.service.BaseService;

import cn.com.evo.admin.manage.domain.entity.Log;

public interface LogService extends BaseService<Log, String> {
    void logWriter(String operation, String opParams, String opResult, String hostIp, String logType);
}
