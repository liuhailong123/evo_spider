package cn.com.evo.cms.constant;

/**
 * 静态常量类
 *
 * @author shilinxiao
 */
public class Constant {
    /**
     * 版本：dev（开发）；test（测试）；pro（生产）；
     */
    private static Version version = Version.dev;

    /**
     * 微信小程序地址
     */
    public static String WXxcxPath = "https://wx.evomedia.cn/";

    /**
     * 本地视频上传临时路径
     */
    public static String video_upload_local_path() {
        switch (version) {
            case pro:
                return "/home/video/";
            case dev:
                return "/data/video/";
            default:
                return "/data/video/";
        }
    }

    /**
     * 本地图片上传临时路径
     */
    public static String image_upload_local_path() {
        switch (version) {
            case pro:
                return "/home/image/";
            case dev:
                return "/data/image/";
            default:
                return "/data/image/";
        }
    }

    /**
     * 数据迁移使用
     */
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/cms_new_db?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8";
    public static String JDBC_NAME = "root";
    public static String JDBC_PASSWORD = "admin";


    public static String cms_spPath = getCmsSpPath();

    private static String getCmsSpPath() {
        String path = "";
        switch (version) {
            case dev:
                path = "http://192.168.10.102:8080/cms_sp/";
                break;
            case pro:
                path = "http://10.19.4.247:8888/cms_sp/";
                break;
            default:
                break;
        }
        return path;
    }
}
