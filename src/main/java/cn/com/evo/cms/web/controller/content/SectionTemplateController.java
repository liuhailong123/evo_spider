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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;

import cn.com.evo.cms.domain.entity.cms.SectionTemplate;
import cn.com.evo.cms.domain.vo.cms.SectionTemplateVo;
import cn.com.evo.cms.service.cms.SectionTemplateService;

@Controller
@RequestMapping("/template/sectionTemplate")
public class SectionTemplateController extends BaseController {

	private static final String VIEW_PAGE = "cms/template/sectionTemplate/view";
	private static final String FORM_PAGE = "cms/template/sectionTemplate/form";
	private static final String SELECT_PAGE = "cms/template/sectionTemplate/select";
	@Autowired
	private Mapper mapper;
	@Autowired
	private SectionTemplateService cmsSectionTemplateService;

	protected SectionTemplateService getService() {
		return cmsSectionTemplateService;
	}

	@RequestMapping(value = "/select", method = { RequestMethod.GET })
	public ModelAndView select(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(SELECT_PAGE);
		return mav;
	}	
	
	
	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:show" })
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView show(HttpServletRequest request) {
		return new ModelAndView(VIEW_PAGE);
	}

	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:search" })
	@RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DataResult list(HttpServletRequest request, Pager webPage) {
		DataResult dataRet = new DataResult();
		try {
			Specification<SectionTemplate> specification = DynamicSpecifications.bySearchFilter(request,
					SectionTemplate.class, null);
			List<SectionTemplate> entities = getService().findByCondition(specification, webPage);
			List<SectionTemplateVo> lstVo = Lists.newArrayList();
			for (SectionTemplate entity : entities) {
				SectionTemplateVo vo = mapper.map(entity, SectionTemplateVo.class);
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

	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:show" })
	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		return mav;
	}

	@RunLogger(value = "添加", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:add" })
	@RequestMapping(value = "/add", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult store(SectionTemplate entity,@RequestParam(value = "file", required = false) MultipartFile file) {
		MsgResult msgRet = new MsgResult();
		try {
			entity.setEnable(1);
			getService().save(entity,file);
			msgRet.pushOk("添加成功！");
		} catch (Exception e) {
			msgRet.pushError("添加失败：" + e.getMessage());
			logger.error("添加时，发生异常！", e);
		}
		return msgRet;
	}

	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:show" })
	@RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET })
	public ModelAndView edit(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		SectionTemplate entity = getService().findById(id);
		mav.addObject("entity", entity);
		return mav;
	}

	@RunLogger(value = "修改", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:modify" })
	@RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult modify(SectionTemplate entity,@RequestParam(value = "file", required = false) MultipartFile file) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().update(entity,file);
			msgRet.pushOk("修改成功!");
		} catch (Exception e) {
			msgRet.pushError("修改失败：" + e.getMessage());
			logger.error("修改时，发生异常！", e);
		}
		return msgRet;
	}

	@RunLogger(value = "删除单条", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:remove" })
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

	@RunLogger(value = "删除多条", isSaveRequest = true)
	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:remove" })
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
	
	@RequiresPermissions(value = { "ContentCenter:Template:SectionTemplate:remove" })
	@RequestMapping(value = "/deleteFile", method = { RequestMethod.POST }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult deleteFile(@RequestParam("id") String id) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().deleteFile(id);
			msgRet.pushOk("删除资源文件成功!");
		} catch (Exception e) {
			msgRet.pushError("删除资源文件失败：" + e.getMessage());
			logger.error("删除资源文件时，发生异常！", e);
		}
		return msgRet;
	}	
	
	
}
