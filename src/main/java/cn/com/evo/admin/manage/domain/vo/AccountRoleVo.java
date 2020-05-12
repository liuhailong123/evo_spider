package cn.com.evo.admin.manage.domain.vo;

import com.frameworks.core.vo.BaseVo;

public class AccountRoleVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private AccountVo account;

    private RoleVo role;

    public AccountVo getAccount() {
        return account;
    }

    public void setAccount(AccountVo account) {
        this.account = account;
    }

    public RoleVo getRole() {
        return role;
    }

    public void setRole(RoleVo role) {
        this.role = role;
    }
}
