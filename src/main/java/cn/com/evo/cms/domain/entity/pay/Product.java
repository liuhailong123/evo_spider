package cn.com.evo.cms.domain.entity.pay;

import com.frameworks.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pay_product")
@NamedQuery(name = "Product.findAll", query = "SELECT q FROM Product q")
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "varchar(64) COMMENT '产品套餐名称'")
    private String name;

    @Column(columnDefinition = "varchar(10) COMMENT '产品套餐编码'")
    private String code;

    /**
     * 第三方ppvId
     */
    @Column(columnDefinition = "varchar(64) COMMENT '三方产品id'")
    private String thirdPartyId;

    /**
     * 第三发产品编码
     */
    @Column(columnDefinition = "varchar(20) COMMENT '三方产品编码'")
    private String thirdPartyCode;

    @Column(columnDefinition = "varchar(20) COMMENT '三方boss产品编码'")
    private String thirdBossPartyCode;

    @Column(columnDefinition = "varchar(10) COMMENT '原价'")
    private String originalPrice;

    @Column(columnDefinition = "varchar(10) COMMENT '优惠价'")
    private String currentPrice;

    @Column(columnDefinition = "int(1) COMMENT '是否发送短信'")
    private Integer shortMssageInform;

    @Column(columnDefinition = "varchar(32) COMMENT '应用Id'")
    private String appId;

    @Column(columnDefinition = "varchar(1024) COMMENT '简介'")
    private String info;

    @Column(columnDefinition = "int(3) COMMENT '排序'")
    private Integer sort;

    @Column(columnDefinition = "varchar(64) COMMENT '产品内容编码'")
    private String contentCode;

    @Column(columnDefinition = "ini(11) COMMENT '产品类型;1-sp;2-boss'")
    private Integer type;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ProductServerRel> productServerRels = new ArrayList<ProductServerRel>(0);

}
