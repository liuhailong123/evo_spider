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
import com.frameworks.core.web.constants.WebConsts;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.frameworks.utils.Exceptions;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Module;
import cn.com.evo.admin.manage.domain.entity.Permission;
import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.domain.entity.RolePermission;
import cn.com.evo.admin.manage.domain.vo.PermissionVo;
import cn.com.evo.admin.manage.domain.vo.RolePermissionVo;
import cn.com.evo.admin.manage.domain.vo.RoleVo;
import cn.com.evo.admin.manage.service.PermissionService;
import cn.com.evo.admin.manage.service.RoleService;

@Controller
@RequestMapping("/manage/role")
public class RoleController extends BaseController {
    private static final String VIEW_PAGE = "manage/role/view";
    private static final String FORM_PAGE = "manage/role/form";
    private static final String ASSIGN_PAGE = "manage/role/assign";

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private Mapper mapper;

    protected RoleService getService() {
        return roleService;
    }

    @RequiresPermissions(value = { "Manage:Role:show" })
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = { "Manage:Role:search" })
    @RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Role> specification = DynamicSpecifications.bySearchFilter(request, Role.class, null);
            List<Role> roles = getService().findByCondition(specification, webPage);
            List<RoleVo> lstVo = Lists.newArrayList();
            for (Role role : roles) {
                RoleVo vo = mapper.map(role, RoleVo.class);
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

    @RequiresPermissions(value = { "Manage:Role:show" })
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加角色", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Role:add" })
    @RequestMapping(value = "/add", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult store(Role entity) {
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

    @RequiresPermissions(value = { "Manage:Role:show" })
    @RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET })
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Role role = getService().findById(id);
        mav.addObject("role", role);
        return mav;
    }

    @RunLogger(value = "编辑角色", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Role:modify" })
    @RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult modify(Role entity) {
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

    @RunLogger(value = "删除角色", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Role:remove" })
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

    @RunLogger(value = "批量删除角色", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Role:remove" })
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

    @RequiresPermissions(value = { "Manage:Role:assign" })
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
    public ModelAndView goAssign(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(ASSIGN_PAGE);
        mav.addObject("roleId", id);
        return mav;
    }

    /**
     * 获取可分配的权限列表
     * 
     * @param roleId
     * @param moduleId
     * @return
     */
    @RequiresPermissions(value = { "Manage:Role:assign" })
    @RequestMapping(value = "/assigned", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataResult hasRolePermission(String roleId, String moduleId) {
        DataResult dataRet = new DataResult();
        try {
            // 查询选中角色已拥有的权限
            List<RolePermission> rolePermissions = roleService.findRolePermission(roleId, moduleId);
            // 查询选中模块菜单中所有定义的权限,且当前用户具备次权限
            List<Permission> permissions = permissionService.findByModuleId(moduleId);
            List<PermissionVo> hasPermissions = Lists.newArrayList();
            Subject subject = SecurityUtils.getSubject();
            for (Permission permission : permissions) {
                // String moduleCode = permission.getModule().getCode();
                String permCode = permission.getCode();
                // String checkCode = moduleCode + ":" + permCode;
                String checkCode = this.getResource(permission.getModule(), permCode);
                if (subject.isPermitted(checkCode)) {
                    hasPermissions.add(mapper.map(permission, PermissionVo.class));
                }
            }
            // 对比是否已分配
            List<RolePermissionVo> rolePermissionVos = Lists.newArrayList();
            for (PermissionVo permissionVo : hasPermissions) {
                boolean isAssigned = false;
                for (RolePermission rolePermission : rolePermissions) {
                    if (rolePermission.getPermission().getId() == permissionVo.getId()) {
                        RolePermissionVo rolePermissionVo = mapper.map(rolePermission, RolePermissionVo.class);
                        rolePermissionVos.add(rolePermissionVo);
                        isAssigned = true;
                    }
                }
                if (!isAssigned) {
                    RolePermissionVo rolePermissionVo = new RolePermissionVo();
                    rolePermissionVo.setPermission(permissionVo);
                    rolePermissionVos.add(rolePermissionVo);
                }
            }

            dataRet.pushOk("获取成功!");
            dataRet.setTotal(rolePermissionVos.size());
            dataRet.setRows(rolePermissionVos);
        } catch (Exception e) {
            dataRet.pushError("获取失败" + Exceptions.getStackTraceAsString(e));
            logger.error(e, e);
        }
        return dataRet;
    }

    @RunLogger(value = "为角色赋予权限", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Role:assign" })
    @RequestMapping(value = "/assign", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MsgResult assign(String roleId, String moduleId,
            @RequestParam(value = "permIds[]", required = false) String[] permIds) {
        MsgResult msgRet = new MsgResult();
        try {
            roleService.assign(roleId, moduleId, permIds);
            msgRet.pushOk("分配权限成功！");
        } catch (Exception e) {
            msgRet.pushError("分配权限失败：" + e.getMessage());
            logger.error("分配权限时，发生异常！", e);
        }
        return msgRet;
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
