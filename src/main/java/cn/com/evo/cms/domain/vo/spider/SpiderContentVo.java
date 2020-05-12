package cn.com.evo.cms.domain.vo.spider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;

/**
 * @author rf
 * @date 2020/4/28
 * 爬取文章表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpiderContentVo extends com.frameworks.core.vo.BaseVo {
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

    /**
     * 详细内容html
     */
    private String spInfo;

    public void setSpInfo(byte[] spInfo) {
        if(spInfo != null){
            this.spInfo = new String(spInfo, 0, spInfo.length);
        }
    }

    /**
     * 作者
     */
    private String author;

    public SpiderContentVo() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("subtitle", subtitle)
                .append("digest", digest)
                .append("contentUrl", contentUrl)
                .append("sourelUrl", sourelUrl)
                .append("cover", cover)
                .append("tags", tags)
                .append("source", source)
                .append("dateTime", dateTime)
                .append("readNum", readNum)
                .append("likeNum", likeNum)
                .append("author", author)
                .toString();
    }
}
