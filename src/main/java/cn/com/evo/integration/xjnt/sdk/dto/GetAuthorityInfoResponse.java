package cn.com.evo.integration.xjnt.sdk.dto;import java.util.Arrays;/** * @Description: * @author: lu.xin * @create: 2019-04-17 5:56 PM **/public class GetAuthorityInfoResponse {    private Integer ret;    private String ret_msg;    private String play_token;    private String[] demand_url;    private String[] rate_list;    private Integer free_trial_time;    private String auth_random_sn;    private String message;    @Override    public String toString() {        return "GetAuthorityInfoResponse{" +                "ret=" + ret +                ", ret_msg='" + ret_msg + '\'' +                ", play_token='" + play_token + '\'' +                ", demand_url=" + Arrays.toString(demand_url) +                ", rate_list=" + Arrays.toString(rate_list) +                ", free_trial_time=" + free_trial_time +                ", auth_random_sn='" + auth_random_sn + '\'' +                ", message='" + message + '\'' +                '}';    }    public Integer getRet() {        return ret;    }    public void setRet(Integer ret) {        this.ret = ret;    }    public String getRet_msg() {        return ret_msg;    }    public void setRet_msg(String ret_msg) {        this.ret_msg = ret_msg;    }    public String getPlay_token() {        return play_token;    }    public void setPlay_token(String play_token) {        this.play_token = play_token;    }    public String[] getDemand_url() {        return demand_url;    }    public void setDemand_url(String[] demand_url) {        this.demand_url = demand_url;    }    public String[] getRate_list() {        return rate_list;    }    public void setRate_list(String[] rate_list) {        this.rate_list = rate_list;    }    public Integer getFree_trial_time() {        return free_trial_time;    }    public void setFree_trial_time(Integer free_trial_time) {        this.free_trial_time = free_trial_time;    }    public String getAuth_random_sn() {        return auth_random_sn;    }    public void setAuth_random_sn(String auth_random_sn) {        this.auth_random_sn = auth_random_sn;    }    public String getMessage() {        return message;    }    public void setMessage(String message) {        this.message = message;    }}