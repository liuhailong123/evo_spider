package cn.com.evo.cms.service.pay;

import java.util.List;

import com.frameworks.core.service.BaseService;

import cn.com.evo.cms.domain.entity.pay.ProductChannel;

public interface ProductChannelService extends BaseService<ProductChannel, String> {

    List<ProductChannel> findByParentId(String parentId);

}
