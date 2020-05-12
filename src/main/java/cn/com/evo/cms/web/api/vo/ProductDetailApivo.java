package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.pay.Product;
import com.google.common.collect.Lists;

import java.util.List;

public class ProductDetailApivo extends ProductApivo {
    private static final long serialVersionUID = 1L;

    private List<ProductServerApiVo> serverInfos = Lists.newArrayList();

    private List<PayConfigApiVo> payTypes = Lists.newArrayList();

    public ProductDetailApivo(Product product, List<ProductServerApiVo> productServerApiVos, List<PayConfigApiVo> payConfigVos) {
        super(product);
        this.serverInfos = productServerApiVos;
        this.payTypes = payConfigVos;
    }

    public List<ProductServerApiVo> getServerInfos() {
        return serverInfos;
    }

    public void setServerInfos(List<ProductServerApiVo> serverInfos) {
        this.serverInfos = serverInfos;
    }

    public List<PayConfigApiVo> getPayTypes() {
        return payTypes;
    }

    public void setPayTypes(List<PayConfigApiVo> payTypes) {
        this.payTypes = payTypes;
    }
}
