package cn.com.evo.cms.service.total;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.cms.domain.entity.total.ContentOperation;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ContentOperationService extends BaseService<ContentOperation, String> {
    /**
     * 根据注入状态获取数据列表
     *
     * @param status
     * @return
     */
    List<ContentOperation> findByStatus(Integer status);

    /**
     * 根据相关性id获取内容注入对象
     *
     * @param correlateId
     * @return
     */
    ContentOperation getByCorrelateId(String correlateId);

    /**
     * 根据contentId获取内容注入对象
     * @param contentId
     * @return
     */
    List<ContentOperation> findByContentIdOrderByCreateDateDesc(String contentId);

    /**
     * 重新发送注入请求
     *
     * @param id
     */
    void send(String id, Province province);

    /**
     * 批量重新发送注入请求
     *
     * @param ids
     */
    void sends(String[] ids);
}
