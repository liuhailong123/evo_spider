package cn.com.evo.cms.domain.vo.spider;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author rf
 * @date 2020/4/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpiderContentChildVo extends com.frameworks.core.vo.BaseVo {
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

}
