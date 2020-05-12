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

import cn.com.evo.cms.domain.entity.cms.FallTemplatePosition;
import cn.com.evo.cms.domain.vo.cms.FallTemplatePositionVo;
import cn.com.evo.cms.service.cms.FallTemplatePositionService;
import cn.com.evo.cms.service.cms.FallTemplateService;

@RequestMapping("/template/fallTemplatePosition")
@Controller
public class FallTemplatePositionController extends BaseController {

	private static final String FORM_PAGE = "cms/template/fallTemplate/fallTemplatePosition/form";

	@Autowired
	private FallTemplatePositionService fallTemplatePositionService;

	@Autowired
	private FallTemplateService fallTemplateService;

	@Autowired
	private Mapper mapper;

	protected FallTemplatePositionService getService() {
		return fallTemplatePositionService;
	}

	@RequiresPermissions(value = { "ContentCenter:Template:FallTemplate:search" })
	@RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DataResult list(HttpServletRequest request, Pager webPage) {
		DataResult dataRet = new DataResult();
		try {
			Specification<FallTemplatePosition> specification = DynamicSpecifications.bySearchFilter(request,
					FallTemplatePosition.class, null);
			List<FallTemplatePosition> entities = getService().findByCondition(specification, webPage);
			List<FallTemplatePositionVo> lstVo = Lists.newArrayList();
			for (FallTemplatePosition entity : entities) {
				FallTemplatePositionVo vo = mapper.map(entity, FallTemplatePositionVo.class);
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

	@RequiresPermissions(value = { "ContentCenter:Template:FallTemplate:show" })
	@RequestMapping(value = "/add/{fallTemplateId}", method = { RequestMethod.GET })
	public ModelAndView add(HttpServletRequest request, @PathVariable("fallTemplateId") String fallTemplateId) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		FallTemplatePosition entity = new FallTemplatePosition();
		entity.setFallTemplate(fallTemplateService.findById(fallTemplateId));
		mav.addObject("entity", entity);
		return mav;
	}

	@RunLogger(value = "添加", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:FallTemplate:add" })
	@RequestMapping(value = "/add/{fallTemplateId}", method = { RequestMethod.POST }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult store(FallTemplatePosition entity) {
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

	@RequiresPermissions(value = { "ContentCenter:Template:FallTemplate:show" })
	@RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET })
	public ModelAndView edit(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		FallTemplatePosition entity = getService().findById(id);
		mav.addObject("entity", entity);
		return mav;
	}

	@RunLogger(value = "编辑", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:FallTemplate:modify" })
	@RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult modify(FallTemplatePosition entity) {
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

	@RunLogger(value = "删除", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:FallTemplate:remove" })
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

	@RunLogger(value = "批量删除", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:FallTemplate:remove" })
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
}
