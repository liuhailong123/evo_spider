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
@Table(name = "activity_draw_child")
@NamedQuery(name = "DrawChild.findAll", query = "SELECT a FROM DrawChild a")
public class DrawChild extends com.frameworks.core.entity.BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) COMMENT '抽奖id'")
    private String drawId;

    @Column(columnDefinition = "varchar(32) COMMENT '奖品名称'")
    private String name;

    @Column(columnDefinition = "int(10) COMMENT '奖品数量'")
    private Integer count;

    @Column(columnDefinition = "int(10) COMMENT '剩余数量'")
    private Integer nowCount;

    @Column(columnDefinition = "int(5) COMMENT '中奖概率'")
    private Integer probability;

    @Column(columnDefinition = "int(2) COMMENT '排序；'")
    private Integer sort;

    @Column(columnDefinition = "int(2) COMMENT '是否启用；0-否；1-是'")
    private int enable;

    @Column(columnDefinition = "int(2) COMMENT '是否有效奖项；0-否；1-是'")
    private int isEffective;

    @Column(columnDefinition = "varchar(32) COMMENT '图片id'")
    private String pictureId;

}