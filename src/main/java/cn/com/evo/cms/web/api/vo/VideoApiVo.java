package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.cms.Video;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class VideoApiVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * videoId
     */
    private String id;
    /**
     * 清晰度 手机 标清 高清 超清 蓝光 4K 8K
     */
    private int definition;
    /**
     * 地址
     */
    private String url;
    /**
     * 类型 1:2D 2:3D 3:VR
     */
    private Integer type;

    private String cdn1Url;

    private String cdn2Url;

    private String cdn3Url;

    private String platForm;

    public VideoApiVo(int definition, String url, Integer type) {
        this.definition = definition;
        this.url = url;
        this.type = type;
    }

    public VideoApiVo(int definition, String cdn1Url, String cdn2Url, String cdn3Url, Integer type) {
        this.definition = definition;
        this.cdn1Url = cdn1Url;
        this.cdn2Url = cdn2Url;
        this.cdn3Url = cdn3Url;
        this.type = type;
    }


    public VideoApiVo(Video video) {
        this.id = video.getId();
        this.definition = video.getDefinition();
        this.url = video.getUrl();
        this.cdn1Url = video.getCdn1Url();
        this.cdn2Url = video.getCdn2Url();
        this.cdn3Url = video.getCdn3Url();
        this.type = video.getType();
        this.platForm = video.getPlatform();
    }

    public static List<VideoApiVo> getVideos(List<VideoApiVo> videos2D, List<VideoApiVo> videos3D, List<VideoApiVo> videosVR) {
        List<VideoApiVo> videos = Lists.newArrayList();
        if (videos2D != null) {
            if (videos2D.size() > 0) {
                for (VideoApiVo vo : videos2D) {
                    videos.add(vo);
                }
            }
        }
        if (videos3D != null) {
            if (videos3D.size() > 0) {
                for (VideoApiVo vo : videos3D) {
                    videos.add(vo);
                }
            }
        }
        if (videosVR != null) {
            if (videosVR.size() > 0) {
                for (VideoApiVo vo : videosVR) {
                    videos.add(vo);
                }
            }
        }
        return videos;
    }

    public int getDefinition() {
        return definition;
    }

    public void setDefinition(int definition) {
        this.definition = definition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCdn1Url() {
        return cdn1Url;
    }

    public void setCdn1Url(String cdn1Url) {
        this.cdn1Url = cdn1Url;
    }

    public String getCdn2Url() {
        return cdn2Url;
    }

    public void setCdn2Url(String cdn2Url) {
        this.cdn2Url = cdn2Url;
    }

    public String getCdn3Url() {
        return cdn3Url;
    }

    public void setCdn3Url(String cdn3Url) {
        this.cdn3Url = cdn3Url;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
