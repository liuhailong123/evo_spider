package cn.com.evo.cms.service.impl.cms;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.shiro.ShiroUser;

import cn.com.evo.admin.manage.domain.entity.Organization;
import cn.com.evo.cms.domain.entity.cms.QcloudParam;
import cn.com.evo.cms.repository.cms.QcloudParamRepository;
import cn.com.evo.cms.service.cms.QcloudParamService;

@Service
@Transactional
public class QcloudParamServiceImpl extends AbstractBaseService<QcloudParam, String> implements QcloudParamService {

    @Autowired
    private QcloudParamRepository qcloudParamRepository;

    @Override
    protected BaseRepository<QcloudParam, String> getBaseRepository() {
        return qcloudParamRepository;
    }
    @Override
    public void save(QcloudParam entity) {
    	entity.setEnable(0);// 默认非主要
    	super.save(entity);
    }
    
	@Override
	public void changeMain(String id) {
		QcloudParam entity = null;
		try {
			entity = getBaseRepository().findOne(id);
		} catch (Exception e) {
			throw new RuntimeException("实体不存在", e);
		}
		Integer isMain = entity.getEnable();// 是否主要，原始数据
		Integer isMainType = null;
		if (isMain == 0) {// 当前数据为非主要版本
			isMainType = new Integer(1);
			// 将其他版本设置为非主要
			List<QcloudParam> otherParams = qcloudParamRepository.findByAuthId(entity.getAuthId());//查询本组织机构下的全部数据
			for (QcloudParam temp : otherParams) {
				temp.setEnable(new Integer(0));
				try {
					super.saveOrUpdate(temp);
				} catch (Exception e) {
					throw new RuntimeException("更新其他数据为非主要异常", e);
				}
			}
			entity.setEnable(isMainType);
			try {
				super.saveOrUpdate(entity);
			} catch (Exception e) {
				throw new RuntimeException("更新实体异常", e);
			}
		} else {// 当前数据为主要版本
			throw new RuntimeException("无法取消主要，请直接设置其他配置为主要即可！！!");
		}
	}
	@Override
	public QcloudParam getParam() {
		try {
			String organizationId="";
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();// 获取当前登录用户
			if(shiroUser == null){
				organizationId="7";
			}else{
				Organization organization = shiroUser.getAccount().getOrganization();// 获取用户组织
				if(organization==null){
					organizationId="7";
				}else{
					organizationId=organization.getId();
				}
			}
			QcloudParam qcloudParam = qcloudParamRepository.getByAuthIdAndEnable(organizationId, 1);
			if(qcloudParam == null){
				throw new RuntimeException("登陆用户未进行服务器配置");
			}
			return qcloudParam;
		} catch (Exception e) {
			throw new RuntimeException("获取腾讯云配置参数异常", e);
		}
	}
	@Override
	public QcloudParam getParam(String authId) {
		try {
			QcloudParam qcloudParam = qcloudParamRepository.getByAuthIdAndEnable(authId, 1);
			if(qcloudParam == null){
				throw new RuntimeException("登陆用户未进行服务器配置");
			}
			return qcloudParam;
		} catch (Exception e) {
			throw new RuntimeException("获取腾讯云配置参数异常", e);
		}
	}
}
