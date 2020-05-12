package cn.com.evo.cms.domain.vo.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 抽奖池
 * The persistent class for the cms_app database table.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrawVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String beginTime;

    private String endTime;

    private String pictureId;

    private Integer isNeedOrder;

    private Integer maxCount;

    private Integer unit;

    private String info;

    private String appId;

    private int enable;

}