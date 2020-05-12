package cn.com.evo.admin.manage.web.controller;

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

import cn.com.evo.admin.manage.domain.entity.DictClassify;
import cn.com.evo.admin.manage.domain.vo.DictClassifyVo;
import cn.com.evo.admin.manage.service.DictClassifyService;

@Controller
@RequestMapping("/manage/dictionary/classify")
public class DictClassifyController extends BaseController {
    private static final String VIEW_PAGE = "manage/dictionary/classify/view";
    private static final String FORM_PAGE = "manage/dictionary/classify/form";

    @Autowired
    private DictClassifyService dictClassifyService;

    @Autowired
    private Mapper mapper;

    protected DictClassifyService getService() {
        return dictClassifyService;
    }

    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<DictClassify> specification = DynamicSpecifications.bySearchFilter(request,
                    DictClassify.class, null);
            List<DictClassify> dictClassifies = getService().findByCondition(specification, webPage);

            List<DictClassifyVo> lstVo = Lists.newArrayList();
            for (DictClassify classify : dictClassifies) {
                DictClassifyVo vo = mapper.map(classify, DictClassifyVo.class);
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

    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:search"})
    @RequestMapping(value = "/tree", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<TreeView> tree(Long id) {
        List<TreeView> treeViews = Lists.newArrayList();
        try {
            Pager page = new Pager();
            page.setSortName("createDate");
            page.setPageNumber(1);
            page.setPageSize(100);
            page.setSortOrder("asc");

            List<DictClassify> classifies = getService().findAll(page);
            int i = 0;
            for (DictClassify classify : classifies) {
                TreeView treeView = new TreeView();
                treeView.setId(classify.getId().toString());
                treeView.setParent("#");
                treeView.setText(classify.getName());
                treeView.setChildren(false);
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
                treeView.setLevel("0");
                State state = new State();
                if (i == 0) {
                    state.setSelected(true);
                    i++;
                }
                treeView.setState(state);
                treeViews.add(treeView);
            }
        } catch (Exception e) {
            logger.error("获取字典分类树异常！", e);
        }
        return treeViews;
    }

    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "新增字典分类", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(DictClassify entity) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().save(entity);
            msgRet.pushOk("添加成功！");
            msgRet.getExtra().put("type", 1);
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        DictClassify classify = getService().findById(id);
        mav.addObject("classify", classify);
        return mav;
    }

    @RunLogger(value = "编辑字典分类", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(DictClassify entity) {
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

    @RunLogger(value = "删除字典分类", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:remove"})
    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
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

    @RunLogger(value = "批量删除字典分类", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Dictionary:DictClassify:remove"})
    @RequestMapping(value = "/remove", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
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
