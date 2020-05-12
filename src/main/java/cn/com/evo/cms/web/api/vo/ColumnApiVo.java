package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.Column;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class ColumnApiVo implements Serializable {
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
     * 目录附属图片
     */
    private List<PictureApiVo> pictures = Lists.newArrayList();

    public ColumnApiVo(Column column, List<PictureApiVo> pictures) {
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
        this.pictures = pictures;
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

    public List<PictureApiVo> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureApiVo> pictures) {
        this.pictures = pictures;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
