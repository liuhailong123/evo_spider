package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.IndexConfChild;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class IndexApiVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name = "";
    private String bgImg = "";
    private String focusImg = "";
    private String blankUrl = "";
    private Integer up;
    private Integer down;
    private Integer left;
    private Integer right;
    private Integer position;
    private Integer type;

    public IndexApiVo(IndexConfChild indexConfChild) {
        this.id = indexConfChild.getContentId();
        if (StringUtils.isNotBlank(indexConfChild.getName())) {
            this.name = indexConfChild.getName();
        }
        if (StringUtils.isNotBlank(indexConfChild.getBgImg())) {
            this.bgImg = indexConfChild.getBgImg();
        }
        if (StringUtils.isNotBlank(indexConfChild.getFocusImg())) {
            this.focusImg = indexConfChild.getFocusImg();
        }
        if (StringUtils.isNotBlank(indexConfChild.getBlankUrl())) {
            this.blankUrl = indexConfChild.getBlankUrl();
        }
        this.up = indexConfChild.getUpPx();
        this.down = indexConfChild.getDownPx();
        this.left = indexConfChild.getLeftPx();
        this.right = indexConfChild.getRightPx();
        this.position = indexConfChild.getPosition();
        this.type = indexConfChild.getType();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getDown() {
        return down;
    }

    public void setDown(Integer down) {
        this.down = down;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
