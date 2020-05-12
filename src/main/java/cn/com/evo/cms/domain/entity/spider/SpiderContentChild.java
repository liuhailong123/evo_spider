package cn.com.evo.cms.domain.entity.spider;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author rf
 * @date 2020/4/28
 */
@Entity
@Table(name = "spi_spider_content_child")
@NamedQuery(name = "SpiderContentChild.findAll", query = "SELECT s FROM SpiderContentChild s")
public class SpiderContentChild extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    private String fId;

    /**
     * 段落排序
     */
    private String textSort;

    /**
     * 段落内容
     */
    private String contentText;

    public SpiderContentChild() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fId", fId)
                .append("textSort", textSort)
                .append("contentText", contentText)
                .toString();
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getTextSort() {
        return textSort;
    }

    public void setTextSort(String textSort) {
        this.textSort = textSort;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
