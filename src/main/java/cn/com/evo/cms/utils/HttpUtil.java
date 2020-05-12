package cn.com.evo.cms.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

/**
 * 通讯工具类
 *
 * @author shilinxiao 2018-03-20 10:26
 */
public class HttpUtil {
    protected static Logger logger = LogManager.getLogger(HttpUtil.class);

    public static String post(String url, JSONObject jo, String name) {
        String result = null;
        try {
            HttpPost post = new HttpPost(url);
            StringEntity reqEntity = new StringEntity(jo.toString());
            reqEntity.setContentType("application/json");
            post.setEntity(reqEntity);
            logger.error("地址：" + url);
            logger.error(name + "==>发送的数据：" + jo.toString());
            HttpResponse resp = HttpClients.createDefault().execute(post);
            result = EntityUtils.toString(resp.getEntity(), "UTF-8");
            logger.error(name + "==>返回的数据：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String get(String strUrl, String name) {
        String result = null;
        try {
            HttpGet get = new HttpGet();
            get.setURI(new URI(strUrl));
            // 发送请求
            HttpResponse resp = HttpClients.createDefault().execute(get);
            logger.error(name + "==>地址+请求数据：" + strUrl);
            result = EntityUtils.toString(resp.getEntity(), "UTF-8");
            logger.error(name + "==>返回的数据：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
