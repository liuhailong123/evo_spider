package cn.com.evo.cms.web.controller.content;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Organization;
import cn.com.evo.cms.domain.entity.cms.QcloudParam;
import cn.com.evo.cms.domain.vo.cms.QcloudParamVo;
import cn.com.evo.cms.service.cms.QcloudParamService;

@Controller
@RequestMapping("/sourceManage/baseConfig/cloudParam")
public class QcloudParamController extends BaseController {
	private static final String VIEW_PAGE = "cms/sourceManage/baseConfig/cloudParam/view";
	private static final String FORM_PAGE = "cms/sourceManage/baseConfig/cloudParam/form";

	@Autowired
	private QcloudParamService qcloudParamService;

	@Autowired
	private Mapper mapper;

	protected QcloudParamService getService() {
		return qcloudParamService;
	}

	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:show" })
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView show(HttpServletRequest request) {
		return new ModelAndView(VIEW_PAGE);
	}

	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:search" })
	@RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DataResult list(HttpServletRequest request, Pager webPage) {
		DataResult dataRet = new DataResult();
//		Organization org = getLoginUser().getOrganization();
		try {
			Specification<QcloudParam> specification = DynamicSpecifications.bySearchFilter(request, QcloudParam.class, null);
			List<QcloudParam> entities = getService().findByCondition(specification, webPage);
			List<QcloudParamVo> lstVo = Lists.newArrayList();
			for (QcloudParam entity : entities) {
				QcloudParamVo vo = mapper.map(entity, QcloudParamVo.class);
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

	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:show" })
	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		return mav;
	}

	@RunLogger(value = "添加腾讯云配置", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:add" })
	@RequestMapping(value = "/add", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult store(QcloudParam entity) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().save(entity);
			msgRet.pushOk("添加成功！");
		} catch (Exception e) {
			msgRet.pushError("添加失败：" + e.getMessage());
			logger.error("添加时，发生异常！", e);
		}
		return msgRet;
	}

	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:show" })
	@RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET })
	public ModelAndView edit(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		QcloudParam entity = getService().findById(id);
		mav.addObject("entity", entity);
		return mav;
	}

	@RunLogger(value = "编辑腾讯云配置", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:modify" })
	@RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult modify(QcloudParam entity) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().update(entity);
			msgRet.pushOk("修改成功!");
		} catch (Exception e) {
			msgRet.pushError("修改失败：" + e.getMessage());
			logger.error("修改时，发生异常！", e);
		}
		return msgRet;
	}

	@RunLogger(value = "删除腾讯云配置", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:remove" })
	@RequestMapping(value = "/remove/{id}", method = { RequestMethod.POST }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult remove(@PathVariable("id") String id) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().deleteById(id);
			msgRet.pushOk("删除成功！");
		} catch (Exception e) {
			msgRet.pushError("删除失败：" + e.getMessage());
			logger.error("删除时，发生异常！", e);
		}
		return msgRet;
	}

	@RunLogger(value = "批量删除腾讯云配置", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:remove" })
	@RequestMapping(value = "/remove", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult remove(@RequestParam("ids[]") String[] ids) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().deleteByIds(ids);
			msgRet.pushOk("批量删除成功!");
		} catch (Exception e) {
			msgRet.pushError("批量删除失败：" + e.getMessage());
			logger.error("批量删除时，发生异常！", e);
		}
		return msgRet;
	}

	@RunLogger(value = "设置数据是否主要", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:SourceConfig:CloudParam:modify" })
	@RequestMapping(value = "/changeMain", method = { RequestMethod.POST }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult changeMain(@RequestParam("id") String id) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().changeMain(id);
			msgRet.pushOk("设置数据是否主要!");
		} catch (Exception e) {
			msgRet.pushError("设置数据是否主要失败：" + e.getMessage());
			logger.error("设置数据是否主要时，发生异常！", e);
		}
		return msgRet;
	}
}
