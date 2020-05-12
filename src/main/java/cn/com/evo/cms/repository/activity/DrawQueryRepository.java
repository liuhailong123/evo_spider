package cn.com.evo.cms.repository.activity;

import cn.com.evo.cms.domain.entity.activity.DrawQuery;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawQueryRepository extends BaseRepository<DrawQuery, String> {

    /**
     * 获取用户抽奖记录
     *
     * @param userId
     * @return
     */
    List<DrawQuery> findByUserId(String userId);

    /**
     * 获取用户抽奖记录
     *
     * @param userId
     * @param appId
     * @return
     */
    List<DrawQuery> findByUserIdAndAppId(String userId, String appId);

    /**
     * 获取用户抽奖记录
     *
     * @param userId     用户id
     * @param createDate 创建时间模糊匹配
     * @return
     */
    @Query(value = "select * from activity_draw_query c where c.userId = ?1 and c.createDate like ?2", nativeQuery = true)
    List<DrawQuery> findByUserIdAndCreateDateLike(String userId, String createDate);

    /**
     * 获取用户抽奖记录
     *
     * @param userId    用户id
     * @param beginDate 时间段-开始时间
     * @param endDate   时间段-结束时间
     * @return
     */
    List<DrawQuery> findByUserIdAndCreateDateIsBetween(String userId, String beginDate, String endDate);

    /**
     * 获取抽奖记录
     *
     * @param isOwn
     * @param appId
     * @return
     */
    List<DrawQuery> findByIsOwnAndAppId(Integer isOwn, String appId);
}
