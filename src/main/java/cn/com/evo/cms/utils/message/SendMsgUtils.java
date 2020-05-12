package cn.com.evo.cms.utils.message;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Author ZhangLiJie
 * @Description
 * @Date: Created in 18:04 12/10/18
 * @Modified By:
 */
public class SendMsgUtils {

    /**
     * 发送短信验证码
     *
     * @param phone
     * @param code
     * @return
     */
    public static Boolean sendMsg(String sn, String pwd, String phone, String code) throws UnsupportedEncodingException {
        Boolean result = false;
        if (StringUtils.isBlank(sn)) {
            sn = "SDK-BJR-010-00722";
        }

        if (StringUtils.isBlank(pwd)) {
            pwd = "27e7dbd@5b7";
        }

        // 内容
        String content = code;
        Client client = new Client(sn, pwd);
        // 我们的Demo最后是拼成xml了，所以要按照xml的语法来转义
        if (content.indexOf("&") >= 0) {
            content = content.replace("&", "&amp;");
        }

        if (content.indexOf("<") >= 0) {
            content = content.replace("<", "&lt;");
        }

        if (content.indexOf(">") >= 0) {
            content = content.replace(">", "&gt;");
        }

        // 短信发送
        String result_mt = client.mt(phone, content, "", "", "");
        if (result_mt.startsWith("-") || result_mt.equals("")) {
            // 以负号判断是否发送成功
            System.out.print("发送失败！返回值为：" + result_mt + "。请查看webservice返回值对照表");
        } else {
            // 输出返回标识，为小于19位的正数，String类型的，记录您发送的批次
            System.out.print("发送成功，返回值为： " + result_mt);
            result = true;
        }
        return result;
    }
}
