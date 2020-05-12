package cn.com.evo.cms.domain.entity.pay;

import com.frameworks.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 产品定价
 * 用于给应用、栏目、内容进行订单
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_product_rel")
@NamedQuery(name = "ProductRel.findAll", query = "SELECT q FROM ProductRel q")
public class ProductRel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(32) COMMENT '产品id'")
    private String productId;

    @Column(columnDefinition = "varchar(32) COMMENT '业务id、应用id、栏目id、内容id'")
    private String bizId;

    @Column(columnDefinition = "int(3) COMMENT '类型；1-应用定价；2-栏目定价；3-内容定价'")
    private Integer type;

    @Column(columnDefinition = "int(11) COMMENT '排序；'")
    private Integer sort;

    @Column(columnDefinition = "int(3) COMMENT '是否前端显示；1-是；0-否'")
    private Integer isShow;
}
