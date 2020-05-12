package cn.com.evo.integration.scyd.common;/** * @Description: * @author: lu.xin * @create: 2019-05-17 3:46 PM **/public enum ConstantEnum {    /**     * 相互预定的上层标识key     */    csp_id("相互预定的上层标识key", "cspId"),    /**     * 相互预定的下层标识key     */    lsp_id("相互预定的下层标识key", "lspId"),    /**     * 商户号key     */    merchant_id("商户号key", "merchantId"),    /**     * 产品代码key     */    product_id("产品代码key", "productId"),    /**     * 加密私钥key     */    private_key("加密私钥key", "privateKey"),    /**     * 计费与鉴权接口地址key     */    auth_url("计费与鉴权接口地址key", "authURL"),    /**     * 资费与内容绑定接口地址key     */    pay_bind_url("资费与内容绑定接口地址key", "payBindUrl"),    /**     * 注入结果通知XML本地存储路径key     */    content_call_back_xml_dir("注入结果通知XML本地存储路径key", "contentCallBackXmlDir"),    /**     * 内容注入XML本地FTP目录key     */    content_xml_ftp_dir("内容注入XML本地FTP目录key", "contentXmlFtpDir"),    /**     * 图片FTP目录key     */    image_ftp_dir("图片FTP目录key", "imageFtpDir"),    /**     * 视频FTP目录key     */    video_ftp_dir("视频FTP目录key", "videoFtpDir"),    /**     * 视频访问域名key     */    video_host("视频访问域名key", "videoHost"),;    ConstantEnum(String name, String key) {        this.name = name;        this.key = key;    }    private String name;    private String key;    /**     * 根据key值获取枚举对象     *     * @param key     * @return     */    public static ConstantEnum getByKey(String key) {        //values()方法返回enum实例的数组        for (ConstantEnum s : values()) {            if (key.equals(s.getKey())) {                return s;            }        }        return null;    }    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public String getKey() {        return key;    }    public void setKey(String key) {        this.key = key;    }}