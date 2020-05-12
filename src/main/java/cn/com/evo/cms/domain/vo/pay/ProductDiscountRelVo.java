package cn.com.evo.cms.domain.vo.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 产品优惠关系
 * The persistent class for the cms_content_source database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDiscountRelVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String productId;

    private String discountId;

    private int type;

}