package cn.com.evo.cms.domain.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cms_app database table.
 */
@Entity
@Table(name = "cms_app")
@NamedQuery(name = "App.findAll", query = "SELECT a FROM App a")
public class App extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private int classify;

    private Long collectCount;

    private Long downCount;

    private String info;

    private String name;

    private String spellLong;

    private String spellShort;

    private int type;

    @Column(columnDefinition = "varchar(60) COMMENT '应用编码'")
    private String code;

    @Column(columnDefinition = "varchar(32) COMMENT '版本名称'")
    private String versionName;

    @Column(columnDefinition = "varchar(32) COMMENT '版本号'")
    private String versionCode;

    @Column(columnDefinition = "varchar(64) COMMENT '包名'")
    private String packageName;

    @Column(columnDefinition = "varchar(254) COMMENT '版本说明'")
    private String versionInfo;

    @Column(columnDefinition = "varchar(32) COMMENT '发布时间'")
    private String publishDate;

    public App() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getClassify() {
        return this.classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public Long getCollectCount() {
        return this.collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public Long getDownCount() {
        return this.downCount;
    }

    public void setDownCount(Long downCount) {
        this.downCount = downCount;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpellLong() {
        return this.spellLong;
    }

    public void setSpellLong(String spellLong) {
        this.spellLong = spellLong;
    }

    public String getSpellShort() {
        return this.spellShort;
    }

    public void setSpellShort(String spellShort) {
        this.spellShort = spellShort;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}