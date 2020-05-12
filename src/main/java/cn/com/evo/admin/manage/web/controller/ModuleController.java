package cn.com.evo.admin.manage.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
import com.frameworks.core.shiro.ShiroConsts;
import com.frameworks.core.web.constants.WebConsts;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.frameworks.core.web.widget.TreeView;
import com.frameworks.core.web.widget.TreeView.NodeType;
import com.frameworks.core.web.widget.TreeView.State;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Module;
import cn.com.evo.admin.manage.domain.entity.Permission;
import cn.com.evo.admin.manage.domain.vo.ModuleVo;
import cn.com.evo.admin.manage.service.ModuleService;

@Controller
@RequestMapping("/manage/module")
public class ModuleController extends BaseController {

    private static final String VIEW_PAGE = "manage/module/view";
    private static final String FORM_PAGE = "manage/module/form";

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private Mapper mapper;

    protected ModuleService getService() {
        return moduleService;
    }

    @RequiresPermissions(value = {"Manage:Module:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Manage:Module:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Module> specification = DynamicSpecifications.bySearchFilter(request, Module.class, null);
            List<Module> modules = getService().findByCondition(specification, webPage);
            List<ModuleVo> lstVo = Lists.newArrayList();
            for (Module module : modules) {
                ModuleVo vo = mapper.map(module, ModuleVo.class);
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

    @RequiresPermissions(value = {"Manage:Module:search"})
    @RequestMapping(value = "/tree/{level}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<TreeView> tree(String id, @PathVariable("level") int level) {
        List<TreeView> treeViews = Lists.newArrayList();
        try {
            if ("".equals(id)) {
                id = null;
            }
            List<Module> modules = getService().findByParentId(id);
            for (Module module : modules) {
                boolean isAuth = hasAuth(module);
                logger.debug(module.getName() + ":" + isAuth);
                if (!isAuth) {
                    continue;
                }
                TreeView treeView = new TreeView();
                treeView.setId(module.getId().toString());
                Module parent = module.getParent();
                treeView.setParent(parent == null ? "#" : parent.getId().toString());
                treeView.setText(module.getName());
                treeView.setChildren(module.getChildren().isEmpty() ? false : true);
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
                treeView.setLevel(String.valueOf(module.getLevel()));
                State state = new State();
                if (treeView.getChildren() && (module.getLevel() < 1)) {
                    state.setOpened(true);
                    state.setSelected(true);
                } else if (module.getLevel() == level) {
//                    state.setDisabled(true);
                }
                treeView.setState(state);
                treeViews.add(treeView);
            }
        } catch (Exception e) {
            logger.error("获取菜单树异常！", e);
        }
        return treeViews;
    }

    @RequiresPermissions(value = {"Manage:Module:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Module module = new Module();
        initPermission(module);
        mav.addObject("module", module);
        return mav;
    }

    @RunLogger(value = "添加模块菜单", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Module:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Module entity) {
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

    @RequiresPermissions(value = {"Manage:Module:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Module module = getService().findById(id);
        initPermission(module);
        mav.addObject("module", module);
        return mav;
    }

    @RunLogger(value = "编辑模块菜单", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Module:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Module entity) {
        MsgResult msgRet = new MsgResult();
        try {
            // 更新实体
            getService().update(entity);
            // 变更权限
            getService().changePermissions(entity);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除模块菜单", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Module:remove"})
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

    @RunLogger(value = "批量删除模块菜单", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Module:remove"})
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

    // TODO 默认授权定义
    private void initPermission(Module module) {
        List<Permission> permissions = Lists.newArrayList();

        Permission show = new Permission();
        show.setName("访问");
        show.setCode(ShiroConsts.PERMISSION_SHOW_DEFAULT_CODE);
        show.setDescription("允许访问的权限.");

        Permission save = new Permission();
        save.setName("新增");
        save.setCode(ShiroConsts.PERMISSION_ADD_DEFAULT_CODE);
        save.setDescription("允许新增的权限.");

        Permission update = new Permission();
        update.setName("编辑");
        update.setCode(ShiroConsts.PERMISSION_MODIFY_DEFAULT_CODE);
        update.setDescription("允许编辑的权限.");

        Permission delete = new Permission();
        delete.setName("删除");
        delete.setCode(ShiroConsts.PERMISSION_REMOVE_DEFAULT_CODE);
        delete.setDescription("允许删除的权限.");

        Permission search = new Permission();
        search.setName("查询");
        search.setCode(ShiroConsts.PERMISSION_SEARCH_DEFAULT_CODE);
        search.setDescription("允许查询的权限.");

        permissions.add(show);
        permissions.add(save);
        permissions.add(update);
        permissions.add(delete);
        permissions.add(search);

        if (module.getPermissions().isEmpty()) {
            for (Permission permission : permissions) {
                module.addPermission(permission);
            }
        } else {
            for (Permission permission : permissions) {
                boolean isExists = false;
                for (Permission assign : module.getPermissions()) {
                    if (assign.getCode().equalsIgnoreCase(permission.getCode())) {
                        isExists = true;
                    }
                }
                if (!isExists) {
                    module.addPermission(permission);
                }
            }
        }
    }

    private boolean hasAuth(Module module) {
        Subject subject = SecurityUtils.getSubject();
        String resShow = this.getResource(module, ShiroConsts.PERMISSION_SHOW_DEFAULT_CODE);
        String resSearch = this.getResource(module, ShiroConsts.PERMISSION_SEARCH_DEFAULT_CODE);
        if (subject.isPermitted(resShow) || subject.isPermitted(resSearch)) {
            return true;
        }
        return false;
    }

    private String getResource(Module module, String resource) {
        if (module.getLevel() > (WebConsts.DEFAULT_ROOT_LEVEL + 1)) {
            resource = module.getCode() + ":" + resource;
            return getResource(module.getParent(), resource);
        } else {
            return module.getCode() + ":" + resource;
        }
    }
}
