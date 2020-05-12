package cn.com.evo.integration.longjiang.handle;

import cn.com.evo.integration.longjiang.common.BaseHandle;
import cn.com.evo.integration.longjiang.common.Config;
import cn.com.evo.integration.longjiang.common.request.VoiceSynDataRequest;
import com.alibaba.fastjson.JSONObject;


public class VoiceSynHandle extends BaseHandle {

    private VoiceSynDataRequest request;
    public static VoiceSynHandle me = null;

    public VoiceSynHandle() {
    }

    public static VoiceSynHandle create(VoiceSynDataRequest request) {
        if (null == me) {
            synchronized (VoiceSynHandle.class){
                if(null == me) {
                    me = new VoiceSynHandle();
                }
            }
        }
        me.request = request;
        return me;
    }

    /**
     * 接口接口
     */
    public String call() {
        try {
            String params = JSONObject.toJSONString(request);
            String temp = postOrJson(Config.voiceSynUrl, params);
            return temp;
        } catch (Exception e) {
            return null;
        }
    }

}
