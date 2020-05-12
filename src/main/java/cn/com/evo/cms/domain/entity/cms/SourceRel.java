package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 资源关系表
 * The persistent class for the cms_content_source database table.
 */
@Entity
@Table(name = "cms_source_rel")
@NamedQuery(name = "SourceRel.findAll", query = "SELECT c FROM SourceRel c")
public class SourceRel extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) comment '资源id'")
    private String sourceId;

    @Column(columnDefinition = "varchar(32) comment '外键id'")
    private String fId;

    @Column(columnDefinition = "int(11) comment '关系类型。1-内容资源关系；2-栏目资源关系; 3-产品资源关系'")
    private int relType;

    @Column(columnDefinition = "int(11) comment '资源类型。1-视频；2-图片；3-音频；4-文本'")
    private int sourcetype;

    @Column(columnDefinition = "int(11) comment '业务类型。1-海报；2-背景；3-宣传片；4-正片；5-音频（待扩展）；6-文本（待扩展）'")
    private int businessType;

    public SourceRel() {

    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public int getRelType() {
        return relType;
    }

    public void setRelType(int relType) {
        this.relType = relType;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getSourcetype() {
        return this.sourcetype;
    }

    public void setSourcetype(int sourcetype) {
        this.sourcetype = sourcetype;
    }

}