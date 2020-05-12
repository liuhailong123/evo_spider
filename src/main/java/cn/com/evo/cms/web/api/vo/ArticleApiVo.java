package cn.com.evo.cms.web.api.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author rf
 * @date 2020/5/9
 */
public class ArticleApiVo implements Serializable {
    private String title;

    private String subtitle;

    private String author;

    private String digest;

    private String cover;

    private String dateTime;

    private Integer readNum;

    private Integer likeNum;

    private String spInfo;

    public ArticleApiVo(String title, String subtitle, String author, String digest, String cover, String dateTime, Integer readNum, Integer likeNum, String spInfo) {
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.digest = digest;
        this.cover = cover;
        this.dateTime = dateTime;
        this.readNum = readNum;
        this.likeNum = likeNum;
        this.spInfo = spInfo;
    }

    public ArticleApiVo() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getSpInfo() {
        return spInfo;
    }

    public void setSpInfo(String spInfo) {
        this.spInfo = spInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("subtitle", subtitle)
                .append("author", author)
                .append("digest", digest)
                .append("cover", cover)
                .append("dateTime", dateTime)
                .append("readNum", readNum)
                .append("likeNum", likeNum)
                .append("spInfo", spInfo)
                .toString();
    }
}
