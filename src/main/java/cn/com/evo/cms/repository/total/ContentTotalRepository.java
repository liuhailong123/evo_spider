package cn.com.evo.cms.repository.total;

import cn.com.evo.cms.domain.entity.total.ContentTotal;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentTotalRepository extends BaseRepository<ContentTotal, String> {

//    ContentTotal getByContentIdAndColumnIdAndAppIdAndUserIdAndType(String contentId, String columnId, Integer appId, String userId, Integer type);

    ContentTotal getByAppIdAndUserIdAndBizValueAndTypeAndNumber(String appId, String userId, String contentId, Integer type, Integer number);

    List<ContentTotal> getByAppIdAndUserIdAndBizValueAndType(String appId, String userId, String contentId, Integer type);

    @Query(value = "SELECT * from total_content where appId=?1 AND userId=?2 AND createDate like ?3 AND type=?4", nativeQuery = true)
    ContentTotal getByAppIdAndUserIdThisDateAndType(String appId, String userId, String thisDate, Integer type);

    /**
     * 获取用户行为。无需去重时使用
     *
     * @param appId
     * @param userId
     * @param type
     * @param start
     * @param pageSize
     * @return
     */
    @Query(value = "SELECT * from total_content where appId=?1 AND userId=?2 AND type=?3 ORDER BY modifyDate desc LIMIT ?4,?5 ", nativeQuery = true)
    List<ContentTotal> findByAppIdAndUserIdAndTypeLimitStartAndPageSize(String appId, String userId, Integer type, Integer start, Integer pageSize);

    /**
     * 获取播放记录，收藏记录总条数
     *
     * @param appId
     * @param userId
     * @param type
     * @return
     */
    @Query(value = "SELECT count(*) from total_content where appId=?1 AND userId=?2 AND type=?3 ", nativeQuery = true)
    Long getCountByAppIdAndUserIdAndType(String appId, String userId, Integer type);

    /**
     * 根据bizValue去重分页查询方法
     * (用户播放记录使用)
     *
     * @param appId
     * @param userId
     * @param type
     * @param start
     * @param pageSize
     * @return
     */
    @Query(value = "SELECT * from total_content where appId=?1 AND userId=?2 AND type=?3 GROUP BY bizValue ORDER BY createDate desc LIMIT ?4,?5 ", nativeQuery = true)
    List<ContentTotal> findByAppIdAndUserIdAndTypeGroupByBizValueOrderByCreateDateLimit(String appId, String userId, Integer type, Integer start, Integer pageSize);


    /**
     * 获取总数（对bizValue去重）
     *
     * @param appId
     * @param userId
     * @param type
     * @return
     */
    @Query(value = "select count(*) from (SELECT * from total_content where appId=?1 AND userId=?2 AND type=?3 GROUP BY bizValue)b", nativeQuery = true)
    Long getCountByAppIdAndUserIdAndTypeGroupByBizValue(String appId, String userId, Integer type);

    List<ContentTotal> findByAppIdAndUserIdAndType(String appId, String userId, Integer type);

    ContentTotal getByUserIdAndBizValueAndType(String userId, String contentId, Integer type);

    List<ContentTotal> findByUserIdAndBizValueAndTypeOrderByCreateDateDesc(String userId, String contentId, Integer type);

    ContentTotal getByAppIdAndBizValueAndType(String appId, String keyword, Integer type);

    @Query(value = "SELECT * from total_content where appId=?1 AND type=?3 order by number desc limit ?2 ", nativeQuery = true)
    List<ContentTotal> findKeywordByAppIdAndCount(String appId, Integer count, int type);
}
