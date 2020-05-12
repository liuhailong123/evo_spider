package cn.com.evo.cms.web.api.vo;

import cn.com.evo.cms.domain.entity.pay.Product;
import com.google.common.collect.Lists;

import java.util.List;

public class UserProductDetailApiVo extends ProductApivo {
    private static final long serialVersionUID = 1L;

    private List<UserProductServerApiVo> serverInfos = Lists.newArrayList();

    public UserProductDetailApiVo(Product product, List<UserProductServerApiVo> productServerApiVos) {
        super(product);
        this.serverInfos = productServerApiVos;
    }

    public List<UserProductServerApiVo> getServerInfos() {
        return serverInfos;
    }

    public void setServerInfos(List<UserProductServerApiVo> serverInfos) {
        this.serverInfos = serverInfos;
    }

}
