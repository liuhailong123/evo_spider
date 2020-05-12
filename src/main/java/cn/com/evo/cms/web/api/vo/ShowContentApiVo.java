package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.enums.ContentTypeEnum;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShowContentApiVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 关系id
     */
    private String id;

    /**
     * 内容id
     */
    private String contentId;

    private String name;

    /**
     * 模板编码
     */
    private String templateCode = "";

    private String title = "";

    /**
     * 内容类型ContentTypeEnum
     * 1-电影；2-剧集；3-直播；4-活动；5-图书；6-栏目
     */
    private Integer type;

    private String liveUrl = "";

    /**
     * 总集数
     */
    private Integer episodeCount = 1;

    /**
     * 当前集数
     */
    private Integer nowEpisodeCount = 0;

    /**
     * 评分
     */
    private String score = "8";

    /**
     * 是否最新
     */
    private Integer isNew = 0;

    /**
     * 是否推荐
     */
    private Integer isRecommend = 0;

    /**
     * 是否热门
     */
    private Integer isHot = 0;

    /**
     * 简介
     */
    private String info = "";

    private List<PictureApiVo> pictures = Lists.newArrayList();

    public ShowContentApiVo(String id, String name, Integer type) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public ShowContentApiVo(Content content, List<PictureApiVo> pictures, CatalogueRelation catalogueRelation) {
        if (content != null) {
            this.id = catalogueRelation.getId();
            this.contentId = content.getId();
            this.name = content.getName();
            if (StringUtils.isBlank(content.getTitle())) {
                this.title = "";
            } else {
                this.title = content.getTitle();
            }
            this.type = content.getClassify();

            this.score = content.getGrade();

            if (content.getSumNum() != null) {
                this.episodeCount = content.getSumNum();
            }

            this.isNew = isNew(content.getModifyDate(), 7);

            this.info = content.getInfo();
        }
        if (pictures != null) {
            if (pictures.size() > 0) {
                this.pictures = pictures;
            }
        }
    }

    public ShowContentApiVo(BookInfo bookInfo, List<PictureApiVo> pictures, CatalogueRelation catalogueRelation) {
        if (bookInfo != null) {
            this.id = catalogueRelation.getId();
            this.contentId = bookInfo.getId();
            this.name = bookInfo.getName();
            this.type = ContentTypeEnum.book.getIndex();
            this.info = bookInfo.getInfo();
        }
        if (pictures != null) {
            if (pictures.size() > 0) {
                this.pictures = pictures;
            }
        }

    }

    public ShowContentApiVo(Column column, List<PictureApiVo> pictures, CatalogueRelation catalogueRelation) {
        if (column != null) {
            this.id = catalogueRelation.getId();
            this.contentId = column.getId();
            this.name = column.getName();
            if (StringUtils.isBlank(column.getTitle())) {
                this.title = "";
            } else {
                this.title = column.getTitle();
            }
            this.type = ContentTypeEnum.column.getIndex();

            if (StringUtils.isBlank(column.getTemplateCode())) {
                this.templateCode = "";
            } else {
                this.templateCode = column.getTemplateCode();
            }
        }
        if (pictures != null) {
            if (pictures.size() > 0) {
                this.pictures = pictures;
            }
        }
    }

    public ShowContentApiVo(LliveBroadcast lliveBroadcast, List<PictureApiVo> pictures, CatalogueRelation catalogueRelation) {
        if (lliveBroadcast != null) {
            this.id = catalogueRelation.getId();
            this.contentId = lliveBroadcast.getId();
            this.name = lliveBroadcast.getName();
            this.type = ContentTypeEnum.live.getIndex();
            this.liveUrl = lliveBroadcast.getUrl();
            this.info = lliveBroadcast.getInfo();
        }
        if (pictures != null) {
            if (pictures.size() > 0) {
                this.pictures = pictures;
            }
        }
    }

    public ShowContentApiVo(Active active, List<PictureApiVo> pictures, CatalogueRelation catalogueRelation) {
        if (active != null) {
            this.id = catalogueRelation.getId();
            this.contentId = active.getId();
            this.name = active.getName();
            this.type = ContentTypeEnum.live.getIndex();
            this.info = active.getInfo();
        }
        if (pictures != null) {
            if (pictures.size() > 0) {
                this.pictures = pictures;
            }
        }
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

    public List<PictureApiVo> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureApiVo> pictures) {
        this.pictures = pictures;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Integer getNowEpisodeCount() {
        return nowEpisodeCount;
    }

    public void setNowEpisodeCount(Integer nowEpisodeCount) {
        this.nowEpisodeCount = nowEpisodeCount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 判断是否最新
     *
     * @param modifyDate 内容修改时间
     * @param overDate   超期天数
     * @return
     */
    public static Integer isNew(Date modifyDate, Integer overDate) {
        if (overDate == null) {
            overDate = 7;
        }
        Long nowTime = System.currentTimeMillis();
        Long modifyTime = modifyDate.getTime();
        Long overTime = (long) overDate * 24 * 60 * 60 * 1000;
        if (modifyTime + overTime > nowTime) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
