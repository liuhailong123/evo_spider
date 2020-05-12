package cn.com.evo.integration.scyd.pay;import cn.com.evo.cms.utils.HttpUtil;import cn.com.evo.integration.common.ConstantFactory;import cn.com.evo.integration.scyd.common.ConstantEnum;import cn.com.evo.integration.scyd.common.ScydUtils;import cn.com.evo.integration.scyd.pay.dto.ScydAuthRequestDto;import cn.com.evo.integration.scyd.pay.dto.ScydBindRequestDto;import cn.com.evo.integration.scyd.pay.dto.ScydResponseDto;import com.alibaba.fastjson.JSONObject;import org.apache.logging.log4j.LogManager;import org.apache.logging.log4j.Logger;import java.util.Map;/** * @Description: * @author: lu.xin * @create: 2019-04-09 8:37 PM **/public class ScydPaySDK {    protected static Logger logger = LogManager.getLogger(ScydPaySDK.class);    /**     * 支付鉴权接口     *     * @param scydAuthRequestDto 请求参数     * @return     */    public static ScydResponseDto payAuth(ScydAuthRequestDto scydAuthRequestDto) {        // 创建签名        Map<String, String> map = scydAuthRequestDto.toMap();        String sign = ScydUtils.getSign(map, ConstantFactory.map.get(ConstantEnum.private_key.getKey()));        scydAuthRequestDto.setSign(sign);        // 创建请求地址        String url = ConstantFactory.map.get(ConstantEnum.auth_url.getKey()) + "?" + scydAuthRequestDto.toParams();        // 创建请求参数        JSONObject jo = scydAuthRequestDto.toJson();        String resultStr = HttpUtil.post(url, jo, "请求四川移动内容鉴权接口");        // 转换响应参数        ScydResponseDto scydResponseDto = JSONObject.toJavaObject(JSONObject.parseObject(resultStr), ScydResponseDto.class);        return scydResponseDto;    }    /**     * 产品与内容绑定接口     *     * @param bindRequest     * @return     */    public static ScydResponseDto contentBind(ScydBindRequestDto bindRequest) {        ScydResponseDto response = null;        try {            // 创建请求参数            JSONObject jo = bindRequest.toJson();            String resultStr = HttpUtil.post(ConstantFactory.map.get(ConstantEnum.pay_bind_url.getKey()), jo, "请求四川移动产品与内容绑定接口");            // 转换响应参数            response = JSONObject.toJavaObject(JSONObject.parseObject(resultStr),                    ScydResponseDto.class);        } catch (Exception e) {            logger.error("内容资费绑定异常" + e.getMessage(), e);        }        return response;    }}