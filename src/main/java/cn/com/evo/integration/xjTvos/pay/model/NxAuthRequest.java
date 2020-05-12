package cn.com.evo.integration.xjTvos.pay.model;

import cn.com.evo.integration.common.request.AuthRequest;

/**
 * @author rf
 * @date 2019/5/17
 */
public class NxAuthRequest extends AuthRequest {

    private String accesstoken;

    public static NxAuthRequest playAuthInit(String cardNo,
                                             String appId, String columnId, String contentId,
                                             String childContentId, String accesstoken) {
        NxAuthRequest request = new NxAuthRequest();
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
