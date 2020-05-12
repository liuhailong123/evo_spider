package cn.com.evo.integration.longjiang.handle;

import cn.com.evo.integration.longjiang.common.BaseHandle;
import cn.com.evo.integration.longjiang.common.Config;
import cn.com.evo.integration.longjiang.common.request.UserInfoRequest;
import org.apache.logging.log4j.Logger;

/**
 * @Author ZhangLiJie
 * @Description 用户信息类请求
 * @Date: Created in 16:18 26/3/18
 * @Modified By:
 */
public class UserInfoHandle extends BaseHandle {
    protected Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
    private UserInfoRequest request;
    public static UserInfoHandle me = null;

    public UserInfoHandle() {
    }

    public static UserInfoHandle create(UserInfoRequest request) {
        if (null == me) {
            me = new UserInfoHandle();
        }
        me.request = request;
        return me;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public Object getUserInfo() {

        String cardNo = this.request.getCardno();
        String otherParams = this.request.getOtherParams();
        String str = "smartCardNo=" + cardNo + "&otherParas=" + otherParams;
        Object temp = post(Config.userInfo_url, str);
        return temp;
    }

}
