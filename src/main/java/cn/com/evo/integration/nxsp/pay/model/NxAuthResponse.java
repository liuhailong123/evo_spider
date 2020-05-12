package cn.com.evo.integration.nxsp.pay.model;

/**
 * 宁夏套餐鉴权返回对象
 * @author rf
 * @date 2019/5/22
 */
public class NxAuthResponse {
    private String is_purchased;

    private String play_token;

    private String auth_random_sn;

    private String ret;

    private String ret_msg;

    private String message;

    public NxAuthResponse() {
    }

    public NxAuthResponse(String is_purchased, String play_token, String auth_random_sn, String ret, String ret_msg, String message) {
        this.is_purchased = is_purchased;
        this.play_token = play_token;
        this.auth_random_sn = auth_random_sn;
        this.ret = ret;
        this.ret_msg = ret_msg;
        this.message = message;
    }

    public String getIs_purchased() {
        return is_purchased;
    }

    public void setIs_purchased(String is_purchased) {
        this.is_purchased = is_purchased;
    }

    public String getPlay_token() {
        return play_token;
    }

    public void setPlay_token(String play_token) {
        this.play_token = play_token;
    }

    public String getAuth_random_sn() {
        return auth_random_sn;
    }

    public void setAuth_random_sn(String auth_random_sn) {
        this.auth_random_sn = auth_random_sn;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
