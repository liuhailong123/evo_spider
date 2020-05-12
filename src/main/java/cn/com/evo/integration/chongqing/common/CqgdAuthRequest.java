package cn.com.evo.integration.chongqing.common;

import cn.com.evo.integration.common.request.AuthRequest;

/**
 * @author rf
 * @date 2019/6/11
 */
public class CqgdAuthRequest extends AuthRequest {

    public static CqgdAuthRequest playAuthInit(String cardNo,
                                               String appId, String columnId, String contentId,
                                               String childContentId) {
        CqgdAuthRequest request = new CqgdAuthRequest();
        request.setCardNo(cardNo);
        request.setAppId(appId);
        request.setColumnId(columnId);
        request.setContentId(contentId);
        request.setChildContentId(childContentId);
        return request;
    }
}
