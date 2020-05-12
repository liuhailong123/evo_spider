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
import com.frameworks.core.shiro.ShiroConsts;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.frameworks.core.web.widget.TreeView;
import com.frameworks.core.web.widget.TreeView.NodeType;
import com.frameworks.core.web.widget.TreeView.State;
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.Organization;
import cn.com.evo.admin.manage.domain.entity.OrganizationRole;
import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.domain.vo.OrganizationRoleVo;
import cn.com.evo.admin.manage.domain.vo.OrganizationVo;
import cn.com.evo.admin.manage.domain.vo.RoleVo;
import cn.com.evo.admin.manage.service.AccountService;
import cn.com.evo.admin.manage.service.OrganizationRoleService;
import cn.com.evo.admin.manage.service.OrganizationService;

@Controller
@RequestMapping("/manage/organization")
public class OrganizationController extends BaseController {
    private static final String VIEW_PAGE = "manage/organization/view";
    private static final String FORM_PAGE = "manage/organization/form";
    private static final String ASSIGN_PAGE = "manage/organization/assign";

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationRoleService organizationRoleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;

    protected OrganizationService getService() {
        return organizationService;
    }

    @RequiresPermissions(value = {"Manage:Organization:search"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Manage:Organization:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Organization> specification = DynamicSpecifications.bySearchFilter(request,
                    Organization.class, null);
            List<Organization> organizations = getService().findByCondition(specification, webPage);
            List<OrganizationVo> lstVo = Lists.newArrayList();
            for (Organization organization : organizations) {
                OrganizationVo vo = mapper.map(organization, OrganizationVo.class);
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

    @RequiresPermissions(value = {"Manage:Organization:search"})
    @RequestMapping(value = "/tree", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<TreeView> tree(String id) {
        List<TreeView> treeViews = Lists.newArrayList();
        try {
            Account account = this.getLoginUser();
            boolean isSuperman = account.getSuperman() == ShiroConsts.SUPER_MAN;
            if (isSuperman) {
                if ("".equals(id)) {
                    TreeView rootTree = new TreeView();
                    rootTree.setId("0");
                    rootTree.setParent("#");
                    rootTree.setText("所有机构");
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
                    List<Organization> organizations = getService().findByParentId(id);
                    for (Organization organization : organizations) {
                        TreeView treeView = new TreeView();
                        treeView.setId(organization.getId().toString());
                        Organization parent = organization.getParent();
                        treeView.setParent(parent == null ? "0" : parent.getId().toString());
                        treeView.setText(organization.getName());
                        treeView.setChildren(organization.getChildren().isEmpty() ? false : true);
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
                        treeView.setLevel(String.valueOf(organization.getLevel()));
                        State state = new State();
                        treeView.setState(state);
                        treeViews.add(treeView);
                    }
                }
            } else {
                Organization rootOrg = account.getOrganization();
                if ("".equals(id)) {
                    TreeView rootTree = new TreeView();
                    rootTree.setId(rootOrg.getId().toString());
                    rootTree.setParent("#");
                    rootTree.setText(rootOrg.getName());
                    List<Organization> children = getService().findByParentId(rootOrg.getId());
                    rootTree.setChildren(children.isEmpty() ? false : true);
                    rootTree.setType(rootTree.getChildren() ? NodeType.folder : NodeType.item);
                    switch (rootTree.getType()) {
                        case folder:
                            rootTree.setIcon(TreeView.ICON_FOLDER);
                            break;
                        case item:
                            rootTree.setIcon(TreeView.ICON_ITEM);
                            break;
                        default:
                            break;
                    }
                    rootTree.setLevel(String.valueOf(rootOrg.getLevel()));
                    State state = new State();
                    state.setOpened(true);
                    state.setSelected(true);
                    rootTree.setState(state);
                    treeViews.add(rootTree);
                } else {
                    List<Organization> organizations = getService().findByParentId(id);
                    for (Organization organization : organizations) {
                        TreeView treeView = new TreeView();
                        treeView.setId(organization.getId().toString());
                        Organization parent = organization.getParent();
                        treeView.setParent(parent == null ? "0" : parent.getId().toString());
                        treeView.setText(organization.getName());
                        treeView.setChildren(organization.getChildren().isEmpty() ? false : true);
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
                        treeView.setLevel(String.valueOf(organization.getLevel()));
                        State state = new State();
                        treeView.setState(state);
                        treeViews.add(treeView);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取组织机构树异常！", e);
        }
        return treeViews;
    }

    @RequiresPermissions(value = {"Manage:Organization:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("organization", new Organization());
        return mav;
    }

    @RunLogger(value = "添加组织机构", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Organization:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Organization entity) {
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

    @RequiresPermissions(value = {"Manage:Organization:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Organization organization = getService().findById(id);
        mav.addObject("organization", organization);
        return mav;
    }

    @RunLogger(value = "编辑组织机构", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Organization:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Organization entity) {
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

    @RunLogger(value = "删除组织机构", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Organization:remove"})
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

    @RunLogger(value = "批量删除组织机构", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Organization:remove"})
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

    @RequiresPermissions(value = {"Manage:Organization:assign"})
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
    public ModelAndView goAssign(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(ASSIGN_PAGE);
        mav.addObject("organizationId", id);
        return mav;
    }

    @RunLogger(value = "赋予组织机构角色", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Organization:assign"})
    @RequestMapping(value = "/assign", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult assign(OrganizationRole organizationRole) {
        MsgResult msgResult = new MsgResult();
        try {
            this.organizationRoleService.save(organizationRole);
            msgResult.pushOk("授权成功!");
        } catch (Exception e) {
            msgResult.pushError("授权失败!");
            logger.error(e, e);
        }
        return msgResult;
    }

    @RunLogger(value = "撤销组织机构角色", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Organization:assign"})
    @RequestMapping(value = "/revoke", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult revoke(OrganizationRole organizationRole) {
        MsgResult msgResult = new MsgResult();
        try {
            this.organizationRoleService.deleteByEntity(organizationRole);
            msgResult.pushOk("撤销成功!");
        } catch (Exception e) {
            msgResult.pushError("撤销失败!");
            logger.error(e, e);
        }
        return msgResult;
    }

    @RequiresPermissions(value = {"Manage:Organization:assign"})
    @RequestMapping(value = "/assigned", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult assigned(String organizationId) {
        DataResult dataResult = new DataResult();
        try {
            List<OrganizationRole> organizationRoles = this.organizationRoleService
                    .findByOrganizationId(organizationId);
            List<OrganizationRoleVo> lstVo = Lists.newArrayList();
            for (OrganizationRole organizationRole : organizationRoles) {
                OrganizationRoleVo vo = mapper.map(organizationRole, OrganizationRoleVo.class);
                lstVo.add(vo);
            }
            dataResult.pushOk("获取已授权列表成功");
            dataResult.setRows(lstVo);
        } catch (Exception e) {
            dataResult.pushError("获取已授权列表异常！");
            logger.error(e, e);
        }
        return dataResult;
    }

    @RequiresPermissions(value = {"Manage:Organization:assign"})
    @RequestMapping(value = "/unAssigned", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult unAssigned(String organizationId) {
        DataResult dataResult = new DataResult();
        try {
            Account account = this.getLoginUser();
            List<Role> currentRoles = accountService.findRoles(account);
            List<OrganizationRole> organizationRoles = this.organizationRoleService
                    .findByOrganizationId(organizationId);
            List<RoleVo> lstVo = Lists.newArrayList();
            for (Role role : currentRoles) {
                boolean isAssign = false;
                for (OrganizationRole orgRole : organizationRoles) {
                    if (orgRole.getRole().getId() == role.getId()) {
                        isAssign = true;
                        break;
                    }
                }
                if (!isAssign) {
                    RoleVo vo = mapper.map(role, RoleVo.class);
                    lstVo.add(vo);
                }
            }
            dataResult.pushOk("获取未授权列表成功");
            dataResult.setRows(lstVo);
        } catch (Exception e) {
            dataResult.pushError("获取未授权列表异常！");
            logger.error(e, e);
        }
        return dataResult;
    }
}
