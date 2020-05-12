package cn.com.evo.integration.xjdx.common;

import cn.com.evo.integration.chongqing.common.CQConstantEnum;

/**
 * @author rf
 * @date 2019/8/14
 */
public enum XjdxConstantEnum {
    /**
     * 相互预定的上层标识key
     */
    csp_id("相互预定的上层标识key", "cspId"),
    /**
     * 相互预定的下层标识key
     */
    lsp_id("相互预定的下层标识key", "lspId"),
    /**
     * webService注入地址
     */
    ctms_url("webService注入地址", "ctms_url"),
    /**
     * 注入返回xml存放地址
     */
    call_back_xml_dir("注入返回xml存放地址", "call_back_xml_dir"),
    /**
     * ftp服务器XML文件存放路径
     */
    ftp_xml_path("ftp服务器XML文件存放路径", "ftp_xml_path"),
    /**
     * ftp绝对路径
     */
    ftp_path("ftp绝对路径", "ftp_path"),
    /**
     * 测试ftp地址
     */
    ftp_file_path("测试ftp地址", "ftp_file_path"),

    ;


    XjdxConstantEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    private String name;
    private String key;


    /**
     * 根据key值获取枚举对象
     *
     * @param key
     * @return
     */
    public static XjdxConstantEnum getByKey(String key) {
        //values()方法返回enum实例的数组
        for (XjdxConstantEnum s : values()) {
            if (key.equals(s.getKey())) {
                return s;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
