package com.frameworks.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    
    /**
     * 从properties配置文件中获取key对应的value
     * @param path 示例：/ftp/ftp.properties
     * @param key
     * @return
     */
    public String getValue(String path,String key){
        // 读取配置文件
        Properties prop = new Properties();
        InputStream in = this.getClass().getResourceAsStream(path);
        try {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("读取配置文件异常：",e);
        }
        return prop.getProperty(key).trim();
    }
}
