package cn.com.evo.integration.longjiang.common.msg;

/**
 * @Author ZhangLiJie
 * @Description 统一返回编码
 * @Date: Created in 16:07 26/3/18
 * @Modified By:
 */
public enum DataMsgCode {
    ERROR("-1"),
    TIME_OUT("-2"),
    FAIL("-3"),
    SUCCESS("00");
    // 成员变量
    private String code;

    // 构造方法
    private DataMsgCode(String code) {
        this.code = code;
    }
}
