package cn.com.evo.cms.domain.vo.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 价格优惠表
 * The persistent class for the cms_content_source database table.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class PriceDiscountVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private int type;

    private int isMore;

    private String targetValue;

    private String discountValue;

    private int priority;


}