package cn.com.evo.integration.longjiang.user;

import cn.com.evo.integration.longjiang.common.msg.DataMsg;
import cn.com.evo.integration.longjiang.common.request.UserInfoRequest;
import cn.com.evo.integration.longjiang.handle.UserInfoHandle;
import org.springframework.jdbc.UncategorizedSQLException;

/**
 * @Author ZhangLiJie
 * @Description 用户类接口封装
 * @Date: Created in 15:56 26/3/18
 * @Modified By:
 */
public class UserApi {

    /**
     * 获取用户信息接口
     *
     * @return
     */
    public static DataMsg getUserInfoApi(String cardNo, String otherParams) {
        DataMsg msg;
        try {
            // 调用龙江接口 获取用户信息 请求参数封装 (智能卡号 ,其他参数)
            UserInfoRequest request = new UserInfoRequest(cardNo, otherParams);
            // 调用获取用户信息接口
            //result是String类型的xml
            Object result = UserInfoHandle.create(request).getUserInfo();
            if (result != null) {
                // true
                msg = DataMsg.pushOK("获取数据成功", result);
            } else {
                msg = DataMsg.pushFail("调用接口获取数据失败");
            }
        } catch (UncategorizedSQLException e) { //请求超时异常
            msg = DataMsg.pushError("服务器异常!!!" + e.getMessage());
        }
        return msg;
    }

}
