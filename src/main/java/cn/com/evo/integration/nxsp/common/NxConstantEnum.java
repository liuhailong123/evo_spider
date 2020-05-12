package cn.com.evo.integration.nxsp.common;

/**
 * @author rf
 * @date 2019/5/22
 */
public enum NxConstantEnum {
    /**
     * sp身份id
     */
    LSPID("sp身份id", "LSPID"),
    /**
     * 局方平台的媒资系统ID
     */
    AMSID("局方平台的媒资系统ID", "AMSID"),
    /**
     * 内容提供商id
     */
    ProviderID("内容提供商id", "ProviderID"),
    /**
     * 产品包id
     */
    PPVId("产品包id", "PPVId"),
    /**
     * 产品包id
     */
    FREE_PPVId("免费产品包id", "FREE_PPVId"),
    /**
     * 套餐id
     */
    package_id("套餐id", "package_id"),
    /**
     * ftp服务器图片存储目录
     */
    ftp_image_path("ftp服务器图片存储目录", "ftp_image_path"),
    /**
     * ftp服务器视频存储目录
     */
    ftp_video_path("ftp服务器视频存储目录", "ftp_video_path"),
    /**
     * ftp服务器注入xml存储目录
     */
    ftp_xml_path("ftp服务器注入xml存储目录", "ftp_xml_path"),
    /**
     * 注入回调地址
     */
    ingestAssest_notify("注入回调地址", "ingestAssest_notify"),
    /**
     * 发布回调地址
     */
    publish_notify("发布回调地址", "publish_notify"),

    /**
     * 询价接口uri
     * /feemanager/ordermanager/get_price_info
     */
    get_price_info("询价接口uri", "get_price_info"),
    /**
     * 获取视频信息
     * /feemanager/ordermanager/get_price_info
     */
    get_info("获取视频信息", "get_info"),
    /**
     * /business/wehall/product
     * 获取套餐信息uri
     */
    get_product_info("获取套餐信息uri", "get_product_info"),
    /**
     * 测试服务器地址
     */
    test_http_addr("测试服务器地址", "test_http_addr"),
    /**
     * 注入地址
     */
    ctms_url("注入webservice地址", "ctms_url"),
    /**
     * 创建订单uri (暂时不用)
     */
    create_order("创建订单uri", "create_order"),
    /**
     * 套餐鉴权uri(暂时不用)
     */
    get_authority_info("套餐鉴权uri", "get_authority_info"),
    /**
     * 支付uri(展示不用)
     */
    pay("支付uri", "pay"),

    columnId("栏目id", "columnId"),
    /**
     * 播放域名
     */
    video_host("播放域名", "video_host"),
    /**
     * 播放鉴权
     */
    get_authority_url("播放鉴权", "get_authority_url");

    NxConstantEnum(String name, String key) {
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
    public static NxConstantEnum getByKey(String key) {
        //values()方法返回enum实例的数组
        for (NxConstantEnum s : values()) {
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
