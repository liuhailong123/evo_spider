package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.pay.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductApivo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String code;

    private String originalPrice;

    private String currentPrice;

    private String info = "";

    private String pictureUrl = "";

    public ProductApivo(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.code = product.getCode();
        this.originalPrice = product.getOriginalPrice();
        this.currentPrice = product.getCurrentPrice();
        if (StringUtils.isNotBlank(product.getInfo())) {
            this.info = product.getInfo();
        }
    }
}
