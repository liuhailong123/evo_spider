package cn.com.evo.cms.repository.activity;

import cn.com.evo.cms.domain.entity.activity.DrawChild;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface DrawChildRepository extends BaseRepository<DrawChild, String> {

    /**
     * 查询抽奖配置信息
     *
     * @param drawId 抽奖活动id
     * @param enable 是否启用
     * @return
     */
    List<DrawChild> findByDrawIdAndEnableOrderBySort(String drawId, Integer enable);
}
