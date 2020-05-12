package cn.com.evo.cms.web.controller.pay;

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
import com.frameworks.core.web.widget.TreeView;
import com.frameworks.core.web.widget.TreeView.NodeType;
import com.frameworks.core.web.widget.TreeView.State;
import com.google.common.collect.Lists;

import cn.com.evo.cms.domain.entity.pay.ProductChannel;
import cn.com.evo.cms.domain.vo.pay.ProductChannelVo;
import cn.com.evo.cms.service.pay.ProductChannelService;

/**
 * 产品渠道
 * 
 * @author shilinxiao
 *
 */
@Controller
@RequestMapping("/productCharge/productChannel")
public class ProductChannelController extends BaseController {
	private static final String VIEW_PAGE = "cms/productCharge/productChannel/view";
	private static final String FORM_PAGE = "cms/productCharge/productChannel/form";

	@Autowired
	private ProductChannelService productChannelService;

	@Autowired
	private Mapper mapper;

	protected ProductChannelService getService() {
		return productChannelService;
	}

	@RequiresPermissions(value = { "ProductCharge:ProductChannel:search" })
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView show(HttpServletRequest request) {
		return new ModelAndView(VIEW_PAGE);
	}

	@RequiresPermissions(value = { "ProductCharge:ProductChannel:search" })
	@RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public DataResult list(HttpServletRequest request, Pager webPage) {
		DataResult dataRet = new DataResult();
		try {
			Specification<ProductChannel> specification = DynamicSpecifications.bySearchFilter(request,
					ProductChannel.class, null);
			List<ProductChannel> entitys = getService().findByCondition(specification, webPage);
			List<ProductChannelVo> lstVo = Lists.newArrayList();
			for (ProductChannel entity : entitys) {
				ProductChannelVo vo = mapper.map(entity, ProductChannelVo.class);
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

	@RequiresPermissions(value = { "ProductCharge:ProductChannel:search" })
	@RequestMapping(value = "/tree", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<TreeView> tree(String id) {
		List<TreeView> treeViews = Lists.newArrayList();
		try {
			if ("".equals(id)) {
				TreeView rootTree = new TreeView();
				rootTree.setId("0");
				rootTree.setParent("#");
				rootTree.setText("所有渠道");
				rootTree.setChildren(true);
				rootTree.setType(NodeType.folder);
				rootTree.setIcon(TreeView.ICON_FOLDER);
				rootTree.setLevel("-1");
				State rootState = new State();
				rootState.setOpened(true);
				rootState.setSelected(true);
				rootTree.setState(rootState);
				treeViews.add(rootTree);
			} else {
				if ("0".equals(id)) {
					id = null;
				}
				List<ProductChannel> entitys = getService().findByParentId(id);
				for (ProductChannel entity : entitys) {
					TreeView treeView = new TreeView();
					treeView.setId(entity.getId().toString());
					ProductChannel parent = entity.getParent();
					treeView.setParent(parent == null ? "0" : parent.getId().toString());
					treeView.setText(entity.getName());
					treeView.setChildren(entity.getChildren().isEmpty() ? false : true);
					treeView.setType(treeView.getChildren() ? NodeType.folder : NodeType.item);
					switch (treeView.getType()) {
					case folder:
						treeView.setIcon(TreeView.ICON_FOLDER);
						break;
					case item:
						treeView.setIcon(TreeView.ICON_ITEM);
						break;
					default:
						break;
					}
					treeView.setLevel(String.valueOf(entity.getLevel()));
					State state = new State();
					treeView.setState(state);
					treeViews.add(treeView);
				}
			}
		} catch (Exception e) {
			logger.error("获取组织机构树异常！", e);
		}
		return treeViews;
	}

	@RequiresPermissions(value = { "ProductCharge:ProductChannel:show" })
	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		mav.addObject("entity", new ProductChannel());
		return mav;
	}

	@RunLogger(value = "添加", isSaveRequest = true)
	@RequiresPermissions(value = { "ProductCharge:ProductChannel:add" })
	@RequestMapping(value = "/add", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult store(ProductChannel entity) {
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

	@RequiresPermissions(value = { "ProductCharge:ProductChannel:show" })
	@RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET })
	public ModelAndView edit(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView(FORM_PAGE);
		ProductChannel entity = getService().findById(id);
		mav.addObject("entity", entity);
		return mav;
	}

	@RunLogger(value = "编辑", isSaveRequest = true)
	@RequiresPermissions(value = { "ProductCharge:ProductChannel:modify" })
	@RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public MsgResult modify(ProductChannel entity) {
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
	@RequiresPermissions(value = { "ProductCharge:ProductChannel:remove" })
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
	@RequiresPermissions(value = { "ProductCharge:ProductChannel:remove" })
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
