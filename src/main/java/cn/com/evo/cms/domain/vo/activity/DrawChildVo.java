package cn.com.evo.cms.domain.vo.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 抽奖池
 * The persistent class for the cms_app database table.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrawChildVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String drawId;

    private String name;

    private Integer count;

    private Integer nowCount;

    private Integer probability;

    private Integer sort;

    private int enable;

    private int isEffective;

    private String bgUrl;
}