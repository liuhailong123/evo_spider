package cn.com.evo.cms.domain.vo.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductPayTypeRelVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String productId;

    private String configId;

    private PayConfig config;
}