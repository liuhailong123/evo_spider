package cn.com.evo.cms.repository.activity;

import cn.com.evo.cms.domain.entity.activity.Draw;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface DrawRepository extends BaseRepository<Draw, String> {
    /**
     * 获取抽奖列表
     *
     * @param enable
     * @param appId
     * @return
     */
    List<Draw> findByEnableAndAppId(Integer enable, String appId);

    /**
     * 根据appId获取抽奖list
     *
     * @param appId
     * @return
     */
    List<Draw> findByAppId(String appId);
}
