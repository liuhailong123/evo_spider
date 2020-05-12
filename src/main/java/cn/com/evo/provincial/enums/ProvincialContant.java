package cn.com.evo.provincial.enums;

/**
 * @author rf
 * @date 2019/7/18
 */
public enum ProvincialContant {
    chong_qing("chongqing", "cqgdContentServiceImpl"),
    guang_xi("guangxi", "gxgdContentServiceImpl"),
    ning_xia("ningxia", "nxContentServiceImpl"),
    qing_hai("qingHai", "qingHaiContentServiceImpl"),
    shen_zhen("shenzhen", "SZContentServiceImpl"),
    ji_nan("jnyx", "jnyxContentServiceImpl"),
    wa_su("wasu", "wasuContentServiceImpl"),
    long_jiang("Longjiang", "ljContentSynServiceImpl"),
    si_chuan("scyd", "scydContentSynServiceImpl"),
    xin_jiang("xjnt", "xjntContentSynServiceImpl"),
    xin_jiang_tvos("xjTvos", "XJContentServiceImpl"),
    ;

    private String code;

    private String name;


    public static ProvincialContant Initialization(String code){
        ProvincialContant provincial ;
        for (ProvincialContant provincialEnum : values()) {
            if(code.equals(provincialEnum.getCode())){
                provincial = provincialEnum;
                return provincial;
            }
        }
        return null;
    }

    ProvincialContant(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
