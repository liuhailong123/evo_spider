package cn.com.evo.cms.domain.entity.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * 抽奖池
 * The persistent class for the cms_app database table.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "activity_draw")
@NamedQuery(name = "Draw.findAll", query = "SELECT a FROM Draw a")
public class Draw extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) COMMENT '名称'")
    private String name;

    @Column(columnDefinition = "varchar(60) COMMENT '开始时间'")
    private String beginTime;

    @Column(columnDefinition = "varchar(60) COMMENT '结束时间'")
    private String endTime;

    @Column(columnDefinition = "varchar(32) COMMENT '背景图，图片id'")
    private String pictureId;

    @Column(columnDefinition = "int(2) COMMENT '是否需要订购；0-否；1-是'")
    private Integer isNeedOrder;

    @Column(columnDefinition = "int(2) COMMENT '最大次数'")
    private Integer maxCount;

    @Column(columnDefinition = "int(2) COMMENT '限制单位；0-总量；1-每日；2-每月；3-每年'")
    private Integer unit;

    @Column(columnDefinition = "varchar(254) COMMENT '活动介绍'")
    private String info;

    @Column(columnDefinition = "varchar(32) COMMENT '应用id'")
    private String appId;

    @Column(columnDefinition = "int(2) COMMENT '是否启用；0-否；1-是'")
    private int enable;

}