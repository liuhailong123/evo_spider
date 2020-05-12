package cn.com.evo.integration.shenzheng.common.chck;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rf
 * @date 2019/6/20
 */
public class Chck {
    private String key;

    private String value;

    public Chck() {
    }

    public Chck(String key, String value) {

        this.key = key;
        this.value = value;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @param key 变量名
     * @param value 变量值
     * @return
     */
    public static String chckValue(String key, String value){
        if(StringUtils.isNotBlank(value)){
            return value;
        }else {
            throw new RuntimeException("参数验证异常:" + key + "=" + value);
        }
    }
}
