package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.*;


@Entity
@Table(name = "`cms_index_conf_child`")
@NamedQuery(name = "IndexConfChild.findAll", query = "SELECT f FROM IndexConfChild f")
public class IndexConfChild extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    private String contentId;

    private String name;

    private String bgImg;

    private String focusImg;

    private String blankUrl;

    private Integer position;

    private Integer upPx;

    private Integer downPx;

    private Integer leftPx;

    private Integer rightPx;

    private Integer type;

    private String indexConfId;

    public IndexConfChild() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getFocusImg() {
        return focusImg;
    }

    public void setFocusImg(String focusImg) {
        this.focusImg = focusImg;
    }

    public String getBlankUrl() {
        return blankUrl;
    }

    public void setBlankUrl(String blankUrl) {
        this.blankUrl = blankUrl;
    }

    public Integer getUpPx() {
        return upPx;
    }

    public void setUpPx(Integer upPx) {
        this.upPx = upPx;
    }

    public Integer getDownPx() {
        return downPx;
    }

    public void setDownPx(Integer downPx) {
        this.downPx = downPx;
    }

    public Integer getLeftPx() {
        return leftPx;
    }

    public void setLeftPx(Integer leftPx) {
        this.leftPx = leftPx;
    }

    public Integer getRightPx() {
        return rightPx;
    }

    public void setRightPx(Integer rightPx) {
        this.rightPx = rightPx;
    }

    public String getIndexConfId() {
        return indexConfId;
    }

    public void setIndexConfId(String indexConfId) {
        this.indexConfId = indexConfId;
    }
}