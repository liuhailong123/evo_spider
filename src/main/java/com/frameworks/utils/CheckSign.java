package com.frameworks.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class CheckSign {
    private static String secret = "86EF07DF39249A42A2BA1B37D7993E55";
    
    public static boolean check(Map<String, String> params) {
        String sign = params.get("sign");
        String afterSign = buildSign(params, secret);
        if (sign.equals(afterSign)) {
            return true;
        } else {
            return false;
        }
    }

    public static String buildSign(Map<String, String> params, String secret) {
        Map<String, String> treeMap = new TreeMap<String, String>(params);// treeMap默认会以key值升序排序
        StringBuilder sb = new StringBuilder();
        for (String key : treeMap.keySet()) {// 排序后的字典，将所有参数按"key=value"格式拼接在一起
            if(!"sign".equals(key)){
                sb.append(key).append("=").append(treeMap.get(key)).append("&");
            }
        }
        sb.append("key=" + secret);
//        System.out.println("待加密的源参数串为:" + sb.toString());
        MessageDigest md5;
        byte[] bytes = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(sb.toString().getBytes("UTF-8"));// md5加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
//        System.out.println("加密后签名为:" + sign.toString());
        return sign.toString();
    }
}
