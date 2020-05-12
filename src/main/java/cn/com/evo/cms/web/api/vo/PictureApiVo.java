package cn.com.evo.cms.web.api.vo;

import java.io.Serializable;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.cms.Picture;

public class PictureApiVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 图片类型
     */
    private Integer type;

    private String url;

    public PictureApiVo() {
    }

    public PictureApiVo(String imageHost, String fileName, Integer type) {
        this.url = imageHost + fileName;
        this.type = type;
    }


    public PictureApiVo(Picture picture, Province provice) {
        this.url = provice.getImageHost() + picture.getFileName();
        this.type = picture.getType();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
