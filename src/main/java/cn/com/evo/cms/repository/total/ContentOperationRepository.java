package cn.com.evo.cms.repository.total;

import cn.com.evo.cms.domain.entity.total.ContentOperation;
import com.frameworks.core.repository.BaseRepository;

import java.util.List;

public interface ContentOperationRepository extends BaseRepository<ContentOperation, String> {
    /**
     * 根据相关性id获取内容注入对象
     *
     * @param correlateId
     * @return
     */
    ContentOperation getByCorrelateId(String correlateId);

    /**
     * 根据注入状态获取内容注入列表
     *
     * @param status
     * @return
     */
    List<ContentOperation> findByStatus(Integer status);

    /**
     * 根据contentid获取内容注入对象
     * @param contentId
     * @return
     */
    List<ContentOperation> findByContentIdOrderByCreateDateDesc(String contentId);
}
