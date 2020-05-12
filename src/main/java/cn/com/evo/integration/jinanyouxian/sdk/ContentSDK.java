package cn.com.evo.integration.jinanyouxian.sdk;import cn.com.evo.cms.utils.HttpUtil;import cn.com.evo.integration.common.ConstantFactory;import cn.com.evo.integration.jinanyouxian.common.ConstantEnum;import cn.com.evo.integration.jinanyouxian.sdk.dto.GetAuthorityInfoResponse;import com.alibaba.fastjson.JSONObject;import org.apache.logging.log4j.LogManager;import org.apache.logging.log4j.Logger;/** * @Description: 内容类SDK * @author: lu.xin * @create: 2019-04-17 11:12 AM **/public class ContentSDK {    protected static Logger logger = LogManager.getLogger(ContentSDK.class);    /**     * 调用局方接口获取视频信息     *     * @param accesstoken 用户登陆标识     * @param videoId     视频id     * @return 播放地址     */    public static String getVideoAuthorityInfo(String accesstoken, String videoId) {        try {            String url = ConstantFactory.map.get(ConstantEnum.get_authority_url.getKey()) +                    "?accesstoken=" + accesstoken + "&programid=" + videoId + "&playtype=demand";            // 调用局方接口            String resultStr = HttpUtil.get(url, "调用局方接口获取视频鉴权信息");            GetAuthorityInfoResponse response = JSONObject.parseObject(resultStr, GetAuthorityInfoResponse.class);            if (response.getRet() == 0) {                //接口调用成功                String playUrl = "http://stream.slave.jndtv.cn:14312/playurl?accesstoken=" + accesstoken + "&programid=" + videoId + "&playtype=demand&playtoken=" + response.getPlay_token() + "&protocol=http&auth=no";                logger.error(playUrl);                return playUrl;            } else {                return null;            }        } catch (Exception e) {            throw new RuntimeException("调用局方接口获取视频鉴权信息异常：" + e.getMessage(), e);        }    }    public static void main(String[] args) {        getVideoAuthorityInfo("R5C517975U209F2010K537296F3IFFFFFFFFP8M2FB3EB8V20213Z5A5B6W11EEF60072A51", "100097823");    }}