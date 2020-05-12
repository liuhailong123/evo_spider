package cn.com.evo.integration.longjiang.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * @Author ZhangLiJie
 * @Description
 * @Date: Created in 16:35 26/3/18
 * @Modified By:
 */
public class BaseHandle {


    String str = "[{\"id\":\"217538125421\",\"pid\":\"217538125421\",\"partner\":\"未来媒体\",\"name\":\"测试电影\",\"type\":\"电影“,\"poster_url\":\"http://10.177.3.31/20170922094130130.jpg\",\"resource_status\":\"1\"}]";

    String string = "{\"domain\":\"300\",\"operate\":\"add\",\"data\":\"[{\\\"mid\\\":\\\"11460\\\",\\\"name\\\":\\\"抓放爱\\\",\\\"url\\\":\\\"http://10.177.3.31/20170922094130130.jpg\\\",\\\"director\\\":\\\"苏珊珊?格兰特\\\",\\\"actor\\\":\\\"珍妮弗?加纳|凯文?史密斯\\\",\\\"category\\\":\\\"最热\\\",\\\"country\\\":\\\"美国\\\",\\\"years\\\":\\\"2017\\\",\\\"type\\\":\\\"电影\\\"}]\"}";

    //通用的请求龙江网络地址的方法
    public static String postOrJson(String url, String params) {
        String returnStr = null;
        try {
            //time
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            // 修改处
            StringEntity reqEntity = new StringEntity(params, Charset.forName("UTF-8"));
            reqEntity.setContentType("application/json");
            post.setEntity(reqEntity);
            System.out.println("地址：\n" + url);
            System.out.println("==>发送的信息:" + params);
            //time
            HttpResponse resp = HttpClients.createDefault().execute(post);
            //time
            returnStr = EntityUtils.toString(resp.getEntity(), "UTF-8");
            //time
            System.out.println("==>接口返回的信息: \n" + returnStr);
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    public static String post(String url, String params) {
        String returnStr = null;
        try {
            //time
            HttpPost post = new HttpPost(url);
            // 修改处
            StringEntity reqEntity = new StringEntity(params);
            reqEntity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(reqEntity);
            System.out.println("地址：\n" + url);
            System.out.println("==>发送的信息:" + params);
            //time
            HttpResponse resp = HttpClients.createDefault().execute(post);
            //time
            returnStr = EntityUtils.toString(resp.getEntity(), "UTF-8");
            //time
            System.out.println("==>接口返回的信息: \n" + returnStr);
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }
}


