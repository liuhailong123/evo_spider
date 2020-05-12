package cn.com.evo.cms.web.api.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class VideosApiVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 2D视频
     */
    private List<VideoApiVo> twoDimension = Lists.newArrayList();
    /**
     * 3D视频
     */
    private List<VideoApiVo> threeDimension = Lists.newArrayList();
    /**
     * VR视频
     */
    private List<VideoApiVo> virtualReality = Lists.newArrayList();

    public VideosApiVo() {

    }

    public VideosApiVo(VideosApiVo videos) {
        this.twoDimension = videos.getTwoDimension();
        this.threeDimension = videos.getThreeDimension();
        this.virtualReality = videos.getVirtualReality();
    }

    public List<VideoApiVo> getTwoDimension() {
        return twoDimension;
    }

    public void setTwoDimension(List<VideoApiVo> twoDimension) {
        this.twoDimension = twoDimension;
    }

    public List<VideoApiVo> getThreeDimension() {
        return threeDimension;
    }

    public void setThreeDimension(List<VideoApiVo> threeDimension) {
        this.threeDimension = threeDimension;
    }

    public List<VideoApiVo> getVirtualReality() {
        return virtualReality;
    }

    public void setVirtualReality(List<VideoApiVo> virtualReality) {
        this.virtualReality = virtualReality;
    }
}
