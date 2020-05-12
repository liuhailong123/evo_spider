package cn.com.evo.integration.chongqing.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author rf
 * @date 2019/6/11
 */
public class CustInfo {
    /**
     * 账户编号（用于查询账单）
     */
    private String accountId;

    /**
     * 客户编号（BOSS系统编号）:A
     */
    private String custId;
    /**
     * 用户名
     */
    private String custName;
    /**
     * 账户总余额,（所有账本相加）
     */
    private String totleBalance;
    /**
     * 账户号
     */
    private String custCode;
    /**
     * 手机
     */
    private String mobile;
    /**
     * address地址
     */
    private String address;
    /**
     * 联系电话座机
     */
    private String phone;
    /**
     * 所属区域，用于查询账单
     */
    private String ownCorpOrg;
    /**
     * 账户下所有账本
     */
    private List<Balance> balanceList;
    /**
     * 账户下所有用户列表
     */
    private List<CA> userList;
    /**
     * 账户欠费(元)
     */
    private String oweTotalFee;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getTotleBalance() {
        return totleBalance;
    }

    public void setTotleBalance(String totleBalance) {
        this.totleBalance = totleBalance;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnCorpOrg() {
        return ownCorpOrg;
    }

    public void setOwnCorpOrg(String ownCorpOrg) {
        this.ownCorpOrg = ownCorpOrg;
    }

    public List<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList = balanceList;
    }

    public List<CA> getUserList() {
        return userList;
    }

    public void setUserList(List<CA> userList) {
        this.userList = userList;
    }

    public String getOweTotalFee() {
        return oweTotalFee;
    }

    public void setOweTotalFee(String oweTotalFee) {
        this.oweTotalFee = oweTotalFee;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accountId", accountId)
                .append("custId", custId)
                .append("custName", custName)
                .append("totleBalance", totleBalance)
                .append("custCode", custCode)
                .append("mobile", mobile)
                .append("address", address)
                .append("phone", phone)
                .append("ownCorpOrg", ownCorpOrg)
                .append("balanceList", balanceList)
                .append("userList", userList)
                .append("oweTotalFee", oweTotalFee)
                .toString();
    }
}
