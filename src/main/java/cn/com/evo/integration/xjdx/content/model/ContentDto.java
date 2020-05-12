package cn.com.evo.integration.xjdx.content.model;

import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Video;

/**
 * @author rf
 * @date 2019/6/6
 */
public class ContentDto {
    private Video video;

    private Content content;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
