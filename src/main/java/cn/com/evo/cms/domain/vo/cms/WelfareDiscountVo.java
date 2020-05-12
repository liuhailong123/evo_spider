package cn.com.evo.cms.domain.vo.cms;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WelfareDiscountVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private int type;

    private String goodsId;
}