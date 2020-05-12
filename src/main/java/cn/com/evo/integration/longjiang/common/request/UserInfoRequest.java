package cn.com.evo.integration.longjiang.common.request;


import cn.com.evo.integration.longjiang.common.result.OrderProductsResult;

import java.util.List;

/**
 * @Author ZhangLiJie
 * @Description
 * @Date: Created in 16:30 26/3/18
 * @Modified By:
 */
public class UserInfoRequest {
    private String cardno;
    private String otherParams;
    private List<OrderProductsResult> orderProductsResultList;

    public UserInfoRequest(String cardno, String otherParams) {
        this.cardno = cardno;
        this.otherParams = otherParams;
    }


    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(String otherParams) {
        this.otherParams = otherParams;
    }

    public List<OrderProductsResult> getOrderProductsResultList() {
        return orderProductsResultList;
    }

    public void setOrderProductsResultList(List<OrderProductsResult> orderProductsResultList) {
        this.orderProductsResultList = orderProductsResultList;
    }
}
