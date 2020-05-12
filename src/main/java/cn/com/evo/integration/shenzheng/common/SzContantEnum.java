package cn.com.evo.integration.shenzheng.common;

import cn.com.evo.integration.nxsp.common.NxConstantEnum;

/**
 * @author rf
 * @date 2019/5/29
 */
public enum SzContantEnum {
    cp_id("三方定义标识id", "cp_id"),
    cp_name("三方名称", "cp_name"),
    ingest_asset_url("注入地址", "ingest_asset_url"),
    modify_asset_url("注入地址", "ingest_asset_url"),
    video_ftp_path("视频片源物理文件地址", "video_ftp_path"),
    auth_play("影片鉴权", "auth_play"),
    ;


    SzContantEnum(String name, String key) {
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
    public static SzContantEnum getByKey(String key) {
        //values()方法返回enum实例的数组
        for (SzContantEnum s : values()) {
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
