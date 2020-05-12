package cn.com.evo.integration.longjiang.content.xml;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpStatus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author ZhangLiJie
 * @Description
 * @Date: Created in 23:31 8/7/18
 * @Modified By:
 * 请求龙江服务器获取已上传到cdn的资源列表
 */
public class XmlRequestOrJsonResponse {
    /**
     * 发送xml请求到server端
     *
     * @param url       xml请求数据地址
     * @param xmlString 发送的xml数据流
     * @return null发送失败，否则返回响应内容
     */
    public static String findResource(String url, String xmlString) {
        System.out.println("电视剧资产内容请求的数据:" + xmlString);
        //创建httpclient工具对象
        HttpClient client = new HttpClient();
        //创建post请求方法
        PostMethod myPost = new PostMethod(url);
        //设置请求超时时间
        client.setConnectionTimeout(3000 * 1000);
        String responseString = null;
        try {
            //设置请求头部类型
            myPost.setRequestHeader("Content-Type", "text/xml");
            myPost.setRequestHeader("charset", "utf-8");
            //设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
            myPost.setRequestBody(xmlString);
            int statusCode = client.executeMethod(myPost);
            //只有请求成功200了，才做处理
            if (statusCode == HttpStatus.SC_OK) {
                InputStream inputStream = myPost.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                responseString = stringBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myPost.releaseConnection();
        }
        return responseString;
    }


    public static void main(String[] args) {
        String str = "\n" +
                "[\n" +
                "    {\n" +
                "        \"actor\":\"\",\n" +
                "        \"actorsDisplay\":\"未知\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"actor\":\"\",\n" +
                "        \"actorsDisplay\":\"未知\"\n" +
                "    }\n" +
                "]";
        JSONArray jo = JSONArray.parseArray(str);
        jo.size();
        System.out.println(jo.size());
    }

}
