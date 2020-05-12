package cn.com.evo.cms.web.controller.user;

import cn.com.evo.cms.domain.entity.app.AppLockConf;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.vip.UserLockRecord;
import cn.com.evo.cms.domain.vo.vip.UserLockRecordVo;
import cn.com.evo.cms.service.app.AppLockConfService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.vip.UserLockRecordService;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user/userLockRecord")
public class UserLockRecordController extends BaseController {
	private static final String FORM_PAGE = "cms/user/userLockRecord/form";

	@Autowired
	private UserLockRecordService userLockRecordService;

	@Autowired
	private AppLockConfService appLockConfService;

	@Autowired
	private ColumnService columnService;

	@Autowired
	private Mapper mapper;

	protected UserLockRecordService getService() {
		return userLockRecordService;
	}

	@RequiresPermissions(value = { "User:UserLockRecord:search" })
	@RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DataResult list(HttpServletRequest request, Pager webPage) {
		DataResult dataRet = new DataResult();
		try {
			Specification<UserLockRecord> specification = DynamicSpecifications.bySearchFilter(request, UserLockRecord.class,null);
			List<UserLockRecord> entities = getService().findByCondition(specification, webPage);
			List<UserLockRecordVo> lstVo = Lists.newArrayList();
			for (UserLockRecord entity : entities) {
				UserLockRecordVo vo = mapper.map(entity, UserLockRecordVo.class);
				AppLockConf appLockConf=appLockConfService.findById(entity.getAppLockConfId());
				String contentName="";
				if(appLockConf.getContentType()==1){
					Column column=columnService.findById(appLockConf.getContentId());
					contentName=column.getName();
				}
				vo.setLockContentName(contentName);
				vo.setLockContentType(appLockConf.getContentType());
				lstVo.add(vo);
			}
			dataRet.pushOk("获取数据列表成功！");
			dataRet.setTotal(webPage.getTotalCount());
			dataRet.setRows(lstVo);
		} catch (Exception e) {
			dataRet.pushError("获取数据列表失败！");
			logger.error("获取数据列表异常！", e);
		}
		return dataRet;
	}
}
