package cn.com.evo.cms.service.activity;

import cn.com.evo.cms.domain.entity.activity.DrawChild;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface DrawChildService extends BaseService<DrawChild, String> {

    List<DrawChild> findByDrawIdAndEnable(String drawId, Integer enable);
}
