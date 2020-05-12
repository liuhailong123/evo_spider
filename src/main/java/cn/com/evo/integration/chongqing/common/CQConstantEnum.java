package cn.com.evo.integration.chongqing.common;

/**
 * @author rf
 * @date 2019/6/5
 */
public enum CQConstantEnum {
    /**
     * 相互预定的上层标识key
     */
    csp_id("相互预定的上层标识key", "cspId"),
    /**
     * 相互预定的下层标识key
     */
    lsp_id("相互预定的下层标识key", "lspId"),
    /**
     * ftp视频存放路径
     */
    ftp_video_path("ftp视频存放路径", "ftp_video_path"),
    /**
     * ftp海报存放路径
     */
    ftp_image_path("ftp海报存放路径", "ftp_image_path"),
    /**
     * ftp服务器XML文件存放路径
     */
    ftp_xml_path("ftp服务器XML文件存放路径", "ftp_xml_path"),
    /**
     * 获取终端用户信息用作鉴权 接口地址
     */
    get_user_info("获取终端用户信息用作鉴权", "get_user_info"),
    /**
     * 支付接口
     */
    pay("支付接口", "pay"),
    /**
     * 支付回调
     */
    pay_notify("支付回调", "pay_notify"),
    /**
     * 套餐编码
     */
    product_code("套餐编码", "product_code"),
    /**
     * 电子渠道平台主套餐ID，格式为4位数字,局方提供
     */
    comboid("电子渠道平台主套餐ID，格式为4位数字", "comboid"),
    /**
     * 应用id-局方提供
     */
    app_id("应用id", "app_id"),
    /**
     * webService注入地址
     */
    ctms_url("webService注入地址", "ctms_url"),
    /**
     * 注入返回xml存放地址
     */
    call_back_xml_dir("注入返回xml存放地址", "call_back_xml_dir"),

    ;


    CQConstantEnum(String name, String key) {
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
    public static CQConstantEnum getByKey(String key) {
        //values()方法返回enum实例的数组
        for (CQConstantEnum s : values()) {
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
