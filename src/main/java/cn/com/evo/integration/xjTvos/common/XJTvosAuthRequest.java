package cn.com.evo.integration.xjTvos.common;

import cn.com.evo.integration.common.request.AuthRequest;

/**
 * @author rf
 * @date 2019/9/10
 */
public class XJTvosAuthRequest extends AuthRequest {
    private String accesstoken;

    public static XJTvosAuthRequest playAuthInit(String cardNo,
                                             String appId, String columnId, String contentId,
                                             String childContentId, String accesstoken) {
        XJTvosAuthRequest request = new XJTvosAuthRequest();
        request.setCardNo(cardNo);
        request.setAppId(appId);
        request.setColumnId(columnId);
        request.setContentId(contentId);
        request.setChildContentId(childContentId);
        request.setAccesstoken(accesstoken);
        return request;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}
