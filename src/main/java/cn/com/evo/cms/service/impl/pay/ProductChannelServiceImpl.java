package cn.com.evo.cms.service.impl.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.constants.WebConsts;

import cn.com.evo.cms.domain.entity.pay.ProductChannel;
import cn.com.evo.cms.repository.pay.ProductChannelRepository;
import cn.com.evo.cms.service.pay.ProductChannelService;

@Service
@Transactional
public class ProductChannelServiceImpl extends AbstractBaseService<ProductChannel, String> implements ProductChannelService {

	@Autowired
	private ProductChannelRepository productChannelRepository;

	@Override
	public void save(ProductChannel entity) {
		if (entity.getParent().getId() == null) {
			entity.setParent(null);
		}
		super.saveOrUpdate(entity);
	}

	@Override
	public void update(ProductChannel entity) {
		ProductChannel productChannel = super.findById(entity.getId());
		if (entity.getLevel() == WebConsts.DEFAULT_ROOT_LEVEL) {
			entity.setParent(null);
		}
		entity.setChildren(productChannel.getChildren());
		entity.setCreateDate(productChannel.getCreateDate());
		super.saveOrUpdate(entity);
	}

	@Override
	public List<ProductChannel> findByParentId(String parentId) {
		return productChannelRepository.findByParentId(parentId);
	}

	@Override
	protected BaseRepository<ProductChannel, String> getBaseRepository() {
		return productChannelRepository;
	}


}
