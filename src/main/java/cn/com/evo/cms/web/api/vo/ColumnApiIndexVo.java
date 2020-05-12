package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.Column;

import java.io.Serializable;
import java.util.List;

public class ColumnApiIndexVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 目录id
     */
    private String id;
    /**
     * 目录名称
     */
    private String name;
    /**
     * 目录标题
     */
    private String title;
    /**
     * 目录类型
     */
    private int type;
    /**
     * 顺序
     */
    private int sort;
    /**
     * 分类标签
     */
    private String classifyTags;
    /**
     * 目录图标
     */
    private String imgUrl = "";
    /**
     * 目录焦点图标
     */
    private String focusImgUrl = "";

    public ColumnApiIndexVo(Column column, List<PictureApiVo> pictures) {
        this.id = column.getId();
        this.name = column.getName();
        this.title = column.getTitle();
        this.type = column.getType();
        this.sort = column.getSort();
        if (column.getClassifyTags() != null) {
            this.classifyTags = column.getClassifyTags();
        } else {
            this.classifyTags = "";
        }
        for (PictureApiVo vo : pictures) {
            if (vo.getType() == 1) {
                this.imgUrl = vo.getUrl();
            }
            if (vo.getType() == 2) {
                this.focusImgUrl = vo.getUrl();
            }
        }
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFocusImgUrl() {
        return focusImgUrl;
    }

    public void setFocusImgUrl(String focusImgUrl) {
        this.focusImgUrl = focusImgUrl;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getClassifyTags() {
        return classifyTags;
    }

    public void setClassifyTags(String classifyTags) {
        this.classifyTags = classifyTags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
