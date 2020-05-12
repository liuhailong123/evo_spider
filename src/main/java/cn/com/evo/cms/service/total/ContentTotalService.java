package cn.com.evo.cms.service.total;

import cn.com.evo.cms.domain.entity.total.ContentTotal;
import cn.com.evo.cms.domain.vo.total.ContentTotalSearchVo;
import com.frameworks.core.service.BaseService;
import com.frameworks.core.web.page.Pager;

import java.util.List;

public interface ContentTotalService extends BaseService<ContentTotal, String> {
    List<ContentTotal> findByPage(Pager webPage, ContentTotalSearchVo contentTotalSearchVo);

    /**
     * 用户行为上报
     *
     * @param contentTotal
     */
    void up(ContentTotal contentTotal);

    /**
     * 用户行为查询
     *
     * @param contentTotal
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ContentTotal> query(ContentTotal contentTotal, Integer pageNum, Integer pageSize);

    /**
     * 根据用户id 内容id 获取用户行为记录
     *
     * @param userId
     * @param contentId
     * @param type
     */
    ContentTotal getByUserIdAndContentId(String userId, String contentId, Integer type);

    /**
     * 用户行为删除
     *
     * @param appId
     * @param userId
     * @param contentId
     * @param type
     */
    void delete(String appId, String userId, String contentId, Integer type);

    /**
     * 获取用户行为总条数
     *
     * @param appId
     * @param userId
     * @param type
     * @return
     */
    Long getCountByAppIdAndUserIdAndType(String appId, String userId, Integer type);

    /**
     * 获取用户行为总数（对bizValue去重）
     *
     * @param appId
     * @param userId
     * @param type
     * @return
     */
    Long getCountByAppIdAndUserIdAndTypeGroupByBizValue(String appId, String userId, Integer type);

    /**
     * 记录搜索关键字
     *
     * @param keyword
     * @param appId
     */
    void saveKeyword(String keyword, String appId);

    /**
     * 获取热搜关键词
     *
     * @param appId
     * @param count
     * @return
     */
    List<ContentTotal> findKeywordByAppIdAndCount(String appId, Integer count);

}
