package cn.com.evo.integration.nxsp.content.xml.model;

import javax.print.attribute.standard.MediaSize;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.List;

/**
 * @author rf
 * @date 2019/5/9
 */
public class Title implements Serializable {
    /**
     * 同groupAsset中的providerID
     */
    private String providerID;

    /**
     * 资产ID
     */
    private String assetID;

    /**
     * 默认值1
     */
    private String updateNum;

    /**
     * 标签
     * 多个标签之间使用逗号分隔
     */
    private String tag;

    /**
     * 时间对象
     */
    private AssetLifetime  assetLifetime;

    /**
     * 制式 黑白、彩色
     */
    private String format;

    /**
     * 推荐星级
     * 从 1－10，数字越大推荐星级
     * 越高，缺省为 6 ，为 3 颗星
     */
    private String starLevel;

    /**
     * 关键字
     * 多个关键字之间使用逗号分隔
     */
    private String keyword;

    /**
     * 广告内容标识
     */
    private String isAdvertise;

    /**
     * 幕后人员
     */
    private String background;

    /**
     * 发行年份
     */
    private String year;

    /**
     * 出品公司
     */
    private String studioName;

    /**
     * 资产关联的外部URL
     */
    private String externalURL;

    /**
     * 元数据对应的语言版本
     */
    private String metaLanguage;

    /**
     * 演员
     */
    private List<Actor> actors;

    /**
     * 播放时长(秒)
     */
    private String runTime;

    /**
     * 所含奖项
     * 多个奖项之间使用；分隔
     */
    private String awards;

    /**
     * 媒体格式
     */
    private String mediaFormat;

    /**
     * 导演
     */
    private Director director;

    /**
     * 国家地区
     * 内地=1,港台=2,韩日=3,欧美=4,东南亚=5，其他=99
     */
    private String countryOfOrigin;

    /**
     * 集数
     */
    private String items;

    /**
     * iptv简介
     */
    private String iptvDesc;

    /**
     * 说明
     */
    private String comments;

    /**
     * 电影名称
     */
    private String titleFull;

    /**
     * 代理公司
     */
    private String agent;

    /**
     * 元数据类型：Movie
     */
    private String showType = "Movie";

    /**
     * 内容的优先级，从1~n,数字越大优先级越高
     */
    private String priority;

    /**
     * 状态标志
     * 0:失效  1:生效
     */
    private String status;

    /**
     * 搜索名称首字母
     */
    private String searchName;

    /**
     * 限制类别
     * 采用国际通用的 Rating 等级
     */
    private String rating;

    /**
     * 中文描述
     */
    private String summaryMedium;

    /**
     * 看点
     */
    private String summaryShort;

    /**
     * 语言
     */
    private String language;

    /**
     * 监制
     */
    private String superviser;

    /**
     * 英文简介
     */
    private String englishDesc;

    private Extend extend;

    private String episodeId;

    @XmlElement(name = "vod:EpisodeID")
    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    @XmlElement(name = "Tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @XmlElement(name = "searchName")
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Title() {
    }

    public Title(String providerID, String assetID, String updateNum, AssetLifetime assetLifetime, String format, String starLevel, String keyword, String isAdvertise, String background, String year, String studioName, List<Actor> actors, String runTime, String awards, String mediaFormat, Director director, String countryOfOrigin, String iptvDesc, String comments, String titleFull, String agent, String showType, String priority, String status, String rating, String summaryMedium, String summaryShort, String language, String superviser, String englishDesc, Extend extend) {
        this.providerID = providerID;
        this.assetID = assetID;
        this.updateNum = updateNum;
        this.assetLifetime = assetLifetime;
        this.format = format;
        this.starLevel = starLevel;
        this.keyword = keyword;
        this.isAdvertise = isAdvertise;
        this.background = background;
        year = year;
        studioName = studioName;
        this.actors = actors;
        this.runTime = runTime;
        this.awards = awards;
        this.mediaFormat = mediaFormat;
        this.director = director;
        this.countryOfOrigin = countryOfOrigin;
        this.iptvDesc = iptvDesc;
        this.comments = comments;
        this.titleFull = titleFull;
        this.agent = agent;
        this.showType = showType;
        this.priority = priority;
        this.status = status;
        this.rating = rating;
        this.summaryMedium = summaryMedium;
        this.summaryShort = summaryShort;
        this.language = language;
        this.superviser = superviser;
        this.englishDesc = englishDesc;
        this.extend = extend;
    }


    @XmlAttribute(name = "providerID")
    public String getProviderID() {

        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    @XmlAttribute(name = "assetID")
    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    @XmlAttribute(name = "updateNum")
    public String getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(String updateNum) {
        this.updateNum = updateNum;
    }

    @XmlElement(name = "adi:AssetLifetime")
    public AssetLifetime getAssetLifetime() {
        return assetLifetime;
    }

    public void setAssetLifetime(AssetLifetime assetLifetime) {
        this.assetLifetime = assetLifetime;
    }

    @XmlElement(name = "vod:Format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @XmlElement(name = "vod:StarLevel")
    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    @XmlElement(name = "vod:Keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @XmlElement(name = "vod:IsAdvertise")
    public String getIsAdvertise() {
        return isAdvertise;
    }

    public void setIsAdvertise(String isAdvertise) {
        this.isAdvertise = isAdvertise;
    }

    @XmlElement(name = "vod:Background")
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @XmlElement(name = "vod:Year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        year = year;
    }

    @XmlElement(name = "vod:StudioName")
    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        studioName = studioName;
    }

    @XmlElement(name = "vod:ExternalURL")
    public String getExternalURL() {
        return externalURL;
    }

    public void setExternalURL(String externalURL) {
        this.externalURL = externalURL;
    }

    @XmlElement(name = "vod:MetaLanguage")
    public String getMetaLanguage() {
        return metaLanguage;
    }

    public void setMetaLanguage(String metaLanguage) {
        this.metaLanguage = metaLanguage;
    }

    @XmlElementWrapper
    @XmlElement(name = "vod:Actor")
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @XmlElement(name = "vod:RunTime")
    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    @XmlElement(name = "vod:Awards")
    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @XmlElement(name = "vod:MediaFormat")
    public String getMediaFormat() {
        return mediaFormat;
    }

    public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    @XmlElement(name = "vod:Director")
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @XmlElement(name = "vod:CountryOfOrigin")
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @XmlElement(name = "vod:Items")
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @XmlElement(name = "vod:IptvDesc")
    public String getIptvDesc() {
        return iptvDesc;
    }

    public void setIptvDesc(String iptvDesc) {
        this.iptvDesc = iptvDesc;
    }

    @XmlElement(name = "vod:Comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @XmlElement(name = "vod:TitleFull")
    public String getTitleFull() {
        return titleFull;
    }

    public void setTitleFull(String titleFull) {
        this.titleFull = titleFull;
    }

    @XmlElement(name = "vod:Agent")
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @XmlElement(name = "vod:ShowType")
    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    @XmlElement(name = "vod:Priority")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @XmlElement(name = "vod:Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "vod:Rating")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @XmlElement(name = "vod:SummaryMedium")
    public String getSummaryMedium() {
        return summaryMedium;
    }

    public void setSummaryMedium(String summaryMedium) {
        this.summaryMedium = summaryMedium;
    }

    @XmlElement(name = "vod:SummaryShort")
    public String getSummaryShort() {
        return summaryShort;
    }

    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    @XmlElement(name = "vod:Language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlElement(name = "vod:Superviser")
    public String getSuperviser() {
        return superviser;
    }

    public void setSuperviser(String superviser) {
        this.superviser = superviser;
    }

    @XmlElement(name = "vod:EnglishDesc")
    public String getEnglishDesc() {
        return englishDesc;
    }

    public void setEnglishDesc(String englishDesc) {
        this.englishDesc = englishDesc;
    }

    @XmlElement(name = "vod:Extend")
    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
