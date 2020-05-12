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
import com.google.common.collect.Lists;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.AccountRole;
import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.domain.vo.AccountRoleVo;
import cn.com.evo.admin.manage.domain.vo.AccountVo;
import cn.com.evo.admin.manage.domain.vo.RoleVo;
import cn.com.evo.admin.manage.service.AccountRoleService;
import cn.com.evo.admin.manage.service.AccountService;
import cn.com.evo.admin.manage.service.RoleService;

@Controller
@RequestMapping("/manage/account")
public class AccountController extends BaseController {

    private static final String VIEW_PAGE = "manage/account/view";
    private static final String FORM_PAGE = "manage/account/form";
    private static final String FORM_PWD_PAGE = "manage/account/pwd";
    private static final String ASSIGN_PAGE = "manage/account/assign";

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Mapper mapper;

    protected AccountService getService() {
        return accountService;
    }

    @RequiresPermissions(value = { "Manage:Account:show" })
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = { "Manage:Account:search" })
    @RequestMapping(value = "/list", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Account> specification = DynamicSpecifications.bySearchFilter(request, Account.class, null);
            List<Account> accounts = getService().findByCondition(specification, webPage);
            List<AccountVo> lstVo = Lists.newArrayList();
            for (Account account : accounts) {
                AccountVo vo = mapper.map(account, AccountVo.class);
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

    @RequiresPermissions(value = { "Manage:Account:add" })
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("LOGINUSER", this.getLoginUser());
        mav.addObject("SUPERMAN", ShiroConsts.SUPER_MAN);
        return mav;
    }

    @RunLogger(value = "添加用户账号", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:add" })
    @RequestMapping(value = "/add", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult store(Account entity) {
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

    @RequiresPermissions(value = { "Manage:Account:modify" })
    @RequestMapping(value = "/edit/{id}", method = { RequestMethod.GET })
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Account account = getService().findById(id);
        mav.addObject("account", account);
        mav.addObject("LOGINUSER", this.getLoginUser());
        mav.addObject("SUPERMAN", ShiroConsts.SUPER_MAN);
        return mav;
    }

    @RunLogger(value = "编辑用户账号", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:modify" })
    @RequestMapping(value = "/edit", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult modify(Account entity) {
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

    @RunLogger(value = "删除用户账号", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:remove" })
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

    @RunLogger(value = "批量删除用户账号", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:remove" })
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

    @RequiresPermissions(value = { "Manage:Account:modify" })
    @RequestMapping(value = "/modifyPassword", method = { RequestMethod.GET })
    public ModelAndView modifyPassword() {
        ModelAndView mav = new ModelAndView(FORM_PWD_PAGE);
        return mav;
    }

    @RunLogger(value = "修改账号密码", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:modify" })
    @RequestMapping(value = "/modifyPassword", method = { RequestMethod.POST }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult modifyPassword(String plainPassword, String newPassword) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().updatePassword(getLoginUser().getId(), plainPassword, newPassword);
            msgRet.pushOk("修改密码成功!");
        } catch (Exception e) {
            msgRet.pushError("修改密码失败：" + e.getMessage());
            logger.error("修改密码时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "重置账号密码", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:reset" })
    @RequestMapping(value = "/reset", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult reset(String id, String newPassword) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().resetPassword(id, newPassword);
            msgRet.pushOk("重置密码成功!");
        } catch (Exception e) {
            msgRet.pushError("重置密码失败：" + e.getMessage());
            logger.error("重置密码时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = { "Manage:Account:assign" })
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
    public ModelAndView goAssign(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(ASSIGN_PAGE);
        mav.addObject("accountId", id);
        return mav;
    }

    @RunLogger(value = "赋予账号角色", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:assign" })
    @RequestMapping(value = "/assign", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult assign(AccountRole accountRole) {
        MsgResult msgResult = new MsgResult();
        try {
            this.accountRoleService.save(accountRole);
            msgResult.pushOk("授权成功!");
        } catch (Exception e) {
            msgResult.pushError("授权失败!");
            logger.error(e, e);
        }
        return msgResult;
    }

    @RunLogger(value = "撤销账号角色", isSaveRequest = true)
    @RequiresPermissions(value = { "Manage:Account:assign" })
    @RequestMapping(value = "/revoke", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public MsgResult revoke(AccountRole accountRole) {
        MsgResult msgResult = new MsgResult();
        try {
            this.accountRoleService.deleteByEntity(accountRole);
            msgResult.pushOk("撤销成功!");
        } catch (Exception e) {
            msgResult.pushError("撤销失败!");
            logger.error(e, e);
        }
        return msgResult;
    }

    @RequiresPermissions(value = { "Manage:Account:assign" })
    @RequestMapping(value = "/assigned", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public DataResult assigned(String accountId) {
        DataResult dataResult = new DataResult();
        try {
            // 不考虑部门级角色
            List<AccountRole> accountRoles = this.accountRoleService.findByAccountId(accountId);
            List<AccountRoleVo> lstVo = Lists.newArrayList();
            for (AccountRole accountRole : accountRoles) {
                AccountRoleVo vo = mapper.map(accountRole, AccountRoleVo.class);
                lstVo.add(vo);
            }
            dataResult.pushOk("获取已分配列表成功");
            dataResult.setTotal(lstVo.size());
            dataResult.setRows(lstVo);
        } catch (Exception e) {
            dataResult.pushError("获取已分配列表异常！");
            logger.error(e, e);
        }
        return dataResult;
    }

    @RequiresPermissions(value = { "Manage:Account:assign" })
    @RequestMapping(value = "/unAssigned", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public DataResult unAssigned(String accountId) {
        DataResult dataResult = new DataResult();
        try {
            Account account = this.getLoginUser();
            Account targetAccount = this.getService().findById(accountId);
            // 当前用户已有角色
            List<Role> currentRoles = accountService.findRoles(account);
            // 定义一个当前用户所有角色与目标用户部门角色的集合
            List<Role> currentAllRoles = Lists.newArrayList();
            currentAllRoles.addAll(currentRoles);
            if (account.getSuperman() == ShiroConsts.ADMINISTRATOR || account.getSuperman() == ShiroConsts.SUPER_MAN) {
                // 根据目标用户组织获取所有角色
                List<Role> orgAllRoles = this.roleService.findByOrganizationId(targetAccount.getOrganization().getId());
                orgAllRoles.removeAll(currentRoles);
                currentAllRoles.addAll(orgAllRoles);
            }
            // 获取目标用户所有角色
            List<Role> targetRoles = this.getService().findRoles(targetAccount);
            List<RoleVo> lstVo = Lists.newArrayList();
            for (Role role : currentAllRoles) {
                boolean isAssign = false;
                for (Role targetRole : targetRoles) {
                    if (targetRole.getId() == role.getId()) {
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
            dataResult.setTotal(lstVo.size());
            dataResult.setRows(lstVo);
        } catch (Exception e) {
            dataResult.pushError("获取未授权列表异常！");
            logger.error(e, e);
        }
        return dataResult;
    }
}
