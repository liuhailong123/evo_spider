package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.cms.domain.entity.pay.ProductServerRel;
import cn.com.evo.cms.domain.vo.pay.ProductServerRelVo;
import cn.com.evo.cms.service.pay.ProductServerRelService;
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
@RequestMapping("/pay/productServerRel")
public class ProductServerRelController extends BaseController {
	private static final String FORM_PAGE = "cms/pay/productServerRel/form";

	@Autowired
	private ProductServerRelService ProductServerRelService;

	@Autowired
	private Mapper mapper;

	protected ProductServerRelService getService() {
		return ProductServerRelService;
	}

	@RequiresPermissions(value = { "ProductCharge:Prod:Product:search" })
	@RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DataResult list(HttpServletRequest request, Pager webPage) {
		DataResult dataRet = new DataResult();
		try {
			Specification<ProductServerRel> specification = DynamicSpecifications.bySearchFilter(request, ProductServerRel.class,null);
			List<ProductServerRel> entities = getService().findByCondition(specification, webPage);
			List<ProductServerRelVo> lstVo = Lists.newArrayList();
			for (ProductServerRel entity : entities) {
				ProductServerRelVo vo = mapper.map(entity, ProductServerRelVo.class);
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

//	@RequiresPermissions(value = { "ProductCharge:Prod:Product:show" })
//	@RequestMapping(value = "/add", method = { RequestMethod.GET })
//	public ModelAndView add(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView(FORM_PAGE);
//		return mav;
//	}

	@RunLogger(value = "添加", isSaveRequest = true)
	@RequiresPermissions(value = { "ProductCharge:Prod:Product:add" })
	@RequestMapping(value = "/add", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult store(@RequestParam("objStr") String objStr,@RequestParam("productId") String productId) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().save(objStr,productId);
			msgRet.pushOk("添加成功！");
		} catch (Exception e) {
			msgRet.pushError("添加失败：" + e.getMessage());
			logger.error("添加时，发生异常！", e);
		}
		return msgRet;
	}

	@RequiresPermissions(value = { "ProductCharge:Prod:Product:show" })
	@RequestMapping(value = "/edit/{productId}", method = { RequestMethod.GET })
	public ModelAndView edit(@PathVariable("productId") String productId) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		mav.addObject("productId", productId);
		return mav;
	}

	@RunLogger(value = "编辑", isSaveRequest = true)
	@RequiresPermissions(value = { "ProductCharge:Prod:Product:modify" })
	@RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult modify(@RequestParam("productId") String productId,@RequestParam("serverId") String serverId,@RequestParam("ruleId") String ruleId,@RequestParam("name") String name) {
		MsgResult msgRet = new MsgResult();
		try {
			getService().sava(productId,serverId,ruleId,name);
			msgRet.pushOk("保存成功!");
		} catch (Exception e) {
			msgRet.pushError("保存失败：" + e.getMessage());
			logger.error("保存时，发生异常！", e);
		}
		return msgRet;
	}

	@RunLogger(value = "删除", isSaveRequest = true)
	@RequiresPermissions(value = { "ProductCharge:Prod:Product:remove" })
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
	@RequiresPermissions(value = { "ProductCharge:Prod:Product:remove" })
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
