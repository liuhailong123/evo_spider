package cn.com.evo.admin.manage.service.impl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.shiro.ShiroUser;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.Log;
import cn.com.evo.admin.manage.repository.LogRepository;
import cn.com.evo.admin.manage.service.LogService;

@Service
@Transactional
public class LogServiceImpl extends AbstractBaseService<Log, String> implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void save(Log entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void update(Log entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void logWriter(String operation, String opParams, String opResult, String hostIp, String logType) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        Account account = shiroUser.getAccount();
        String operator = account.getUsername() + "(" + account.getId() + ")";
        Date now = new Date();
        Log log = new Log(operator, operation, opParams, opResult, now, hostIp, logType);
        this.save(log);
    }

    @Override
    protected BaseRepository<Log, String> getBaseRepository() {
        return logRepository;
    }
}
