package cn.com.evo.cms.web.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付方式配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayConfigApiVo {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer payType;

}