package cn.com.evo.cms.domain.entity.spider;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author rf
 * @date 2020/4/28
 * 爬取文章表
 */
@Entity
@Table(name = "spi_spider_content")
@NamedQuery(name = "SpiderContent.findAll", query = "SELECT s FROM SpiderContent s")
public class SpiderContent extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 作者
     */
    private String author;

    /**
     * 简介、摘要
     */
    private String digest;

    /**
     * 内容地址
     */
    private String contentUrl;

    /**
     * 源地址
     */
    private String sourelUrl;

    /**
     * 封面
     */
    private String cover;

    /**
     * 分类标签
     */
    private String tags;

    /**
     * 爬取源
     */
    private String source;

    /**
     * 发布时间
     */
    private String dateTime;

    /**
     * 阅读量
     */
    private Integer readNum;

    /**
     * 喜欢量
     */
    private Integer likeNum;

    private byte[] spInfo;

    public byte[] getSpInfo() {
        return spInfo;
    }

    public void setSpInfo(byte[] spInfo) {
        this.spInfo = spInfo;
    }

    public SpiderContent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getSourelUrl() {
        return sourelUrl;
    }

    public void setSourelUrl(String sourelUrl) {
        this.sourelUrl = sourelUrl;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("subtitle", subtitle)
                .append("author", author)
                .append("digest", digest)
                .append("contentUrl", contentUrl)
                .append("sourelUrl", sourelUrl)
                .append("cover", cover)
                .append("tags", tags)
                .append("source", source)
                .append("dateTime", dateTime)
                .append("readNum", readNum)
                .append("likeNum", likeNum)
                .append("spInfo", spInfo)
                .toString();
    }
}
