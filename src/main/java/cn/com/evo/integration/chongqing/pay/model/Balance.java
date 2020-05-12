package cn.com.evo.integration.chongqing.pay.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 账本类
 * @author rf
 * @date 2019/6/11
 */
public class Balance {
    /**
     * 账本类型
     * 0：通用账本
     * 1002：数字基本
     * 1003：宽带
     * 1004：数字付费
     * 1005：互动基本
     */
    private String balanceId;
    /**
     * 账本名称
     */
    private String balanceName;
    /**
     * 账户余额（元）
     */
    private String balance;

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public String getBalanceName() {
        return balanceName;
    }

    public void setBalanceName(String balanceName) {
        this.balanceName = balanceName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("balanceId", balanceId)
                .append("balanceName", balanceName)
                .append("balance", balance)
                .toString();
    }
}
