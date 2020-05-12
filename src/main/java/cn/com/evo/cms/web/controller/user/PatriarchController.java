package cn.com.evo.cms.web.controller.user;

import cn.com.evo.cms.domain.entity.app.App;
import cn.com.evo.cms.domain.entity.vip.Patriarch;
import cn.com.evo.cms.domain.vo.app.AppVo;
import cn.com.evo.cms.domain.vo.vip.PatriarchVo;
import cn.com.evo.cms.service.app.AppService;
import cn.com.evo.cms.service.vip.PatriarchService;
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
@RequestMapping("user/patriarch")
public class PatriarchController extends BaseController {
	private static final String VIEW_PAGE = "cms/user/patriarch/view";

	@Autowired
	private PatriarchService patriarchService;

	@Autowired
	private Mapper mapper;

	protected PatriarchService getService() {
		return patriarchService;
	}

	@RequiresPermissions(value = { "User:UserLockRecord:show" })
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView show(HttpServletRequest request) {
		return new ModelAndView(VIEW_PAGE);
	}

	@RequiresPermissions(value = { "User:UserLockRecord:search" })
	@RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DataResult list(HttpServletRequest request, Pager webPage) {
		DataResult dataRet = new DataResult();
		try {
			Specification<Patriarch> specification = DynamicSpecifications.bySearchFilter(request, Patriarch.class,null);
			List<Patriarch> entities = getService().findByCondition(specification, webPage);
			List<PatriarchVo> lstVo = Lists.newArrayList();
			for (Patriarch entity : entities) {
				PatriarchVo vo = mapper.map(entity, PatriarchVo.class);
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
