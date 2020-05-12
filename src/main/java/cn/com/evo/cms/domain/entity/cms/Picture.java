package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.*;

/**
 * The persistent class for the cms_picture database table.
 */
@Entity
@Table(name = "cms_source_picture")
@NamedQuery(name = "Picture.findAll", query = "SELECT p FROM Picture p")
public class Picture extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String resolution;

    @ManyToOne
    @JoinColumn(name = "sourceId")
    private Source source; // 资源表

    private Integer type;

    private String fileName;

    private String size;

    private String ext;

    private String cloudPath;

    public Picture() {
    }

    public String getCloudPath() {
        return cloudPath;
    }

    public void setCloudPath(String cloudPath) {
        this.cloudPath = cloudPath;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getResolution() {
        return this.resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}