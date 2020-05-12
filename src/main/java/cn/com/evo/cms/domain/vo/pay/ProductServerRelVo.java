package cn.com.evo.cms.domain.vo.pay;

import com.frameworks.core.vo.BaseVo;

public class ProductServerRelVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private ServerRuleRelVo serverRuleRel;

    private ProductVo product;

    public ServerRuleRelVo getServerRuleRel() {
        return serverRuleRel;
    }

    public void setServerRuleRel(ServerRuleRelVo serverRuleRel) {
        this.serverRuleRel = serverRuleRel;
    }

    public ProductVo getProduct() {
        return product;
    }

    public void setProduct(ProductVo product) {
        this.product = product;
    }
}
