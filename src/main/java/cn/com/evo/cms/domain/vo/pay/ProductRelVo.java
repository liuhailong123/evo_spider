package cn.com.evo.cms.domain.vo.pay;

import com.frameworks.core.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 产品定价
 * 用于给应用、栏目、内容进行订单
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductRelVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String productId;

    private String productName;

    private String bizId;

    private Integer type;

    private Integer isShow;

    private Integer sort;

    private String originalPrice;

    private String currentPrice;
}
