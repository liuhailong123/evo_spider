package cn.com.evo.cms.service.impl.total;

import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.total.ContentTotal;
import cn.com.evo.cms.domain.enums.UserActionTypeEnum;
import cn.com.evo.cms.domain.vo.total.ContentTotalSearchVo;
import cn.com.evo.cms.repository.total.ContentTotalRepository;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.total.ContentTotalService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.page.Pager;
import com.frameworks.utils.DateUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ContentTotalServiceImpl extends AbstractBaseService<ContentTotal, String> implements ContentTotalService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ContentTotalRepository repository;
    @Autowired
    private ColumnService columnService;

    @Override
    protected BaseRepository<ContentTotal, String> getBaseRepository() {
        return repository;
    }

    @Override
    public void up(ContentTotal contentTotal) {
        ContentTotal entity;
        List<ContentTotal> entitys;
        String thisDate = "";
        UserActionTypeEnum userActionTypeEnum = UserActionTypeEnum.val(contentTotal.getType());
        switch (userActionTypeEnum) {
            case playRecord:
                super.save(contentTotal);
                break;
            case likeRecord:
                //判断收藏记录是否存在
                entitys = repository.getByAppIdAndUserIdAndBizValueAndType(contentTotal.getApp().getId(), contentTotal.getUserId(), contentTotal.getBizValue(), contentTotal.getType());
                if (entitys.size() <= 0) {
                    //不存在 新增收藏记录
                    super.save(contentTotal);
                } else {
                    //存在 更新收藏记录 修改时间
                    super.update(entitys.get(0));
                }
                break;
            case signRecord:
                //判断用户今日是否已签到
                thisDate = DateUtil.getDate();
                entity = repository.getByAppIdAndUserIdThisDateAndType(contentTotal.getApp().getId(), contentTotal.getUserId(), thisDate + "%", contentTotal.getType());
                if (entity == null) {
                    //今日没签到 新增签到记录
                    super.save(contentTotal);
                }
                break;
            case timeRecord:
                //判断今日 用户观看时长记录是否存在
                thisDate = DateUtil.getDate();
                entity = repository.getByAppIdAndUserIdThisDateAndType(contentTotal.getApp().getId(), contentTotal.getUserId(), thisDate + "%", contentTotal.getType());
                if (entity == null) {
                    //不存在 新增用户观看时长记录
                    super.save(contentTotal);
                } else {
                    //存在
                    if (StringUtils.isBlank(entity.getDuration())) {
                        entity.setDuration(contentTotal.getDuration());
                    } else {
                        entity.setDuration((Integer.valueOf(entity.getDuration()) + Integer.valueOf(contentTotal.getDuration())) + "");
                    }
                    //更新用户观看时长记录
                    super.update(entity);
                }
                break;
            case blackRecord:
                //判断黑名单记录是否存在
                entitys = repository.getByAppIdAndUserIdAndBizValueAndType(contentTotal.getApp().getId(), contentTotal.getUserId(), contentTotal.getBizValue(), contentTotal.getType());
                if (entitys.size() <= 0) {
                    //不存在 新增黑名单记录
                    super.save(contentTotal);
                } else {
                    //存在 //更新黑名单记录 修改时间
                    super.update(entitys.get(0));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public List<ContentTotal> query(ContentTotal contentTotal, Integer pageNum, Integer pageSize) {
        Integer start = (pageNum - 1) * pageSize;
        List<ContentTotal> entitys = Lists.newArrayList();
        UserActionTypeEnum userActionTypeEnum = UserActionTypeEnum.val(contentTotal.getType());
        switch (userActionTypeEnum) {
            case playRecord:
                //播放
                //获取播放记录
                entitys = repository.findByAppIdAndUserIdAndTypeGroupByBizValueOrderByCreateDateLimit(contentTotal.getApp().getId(), contentTotal.getUserId(), contentTotal.getType(), start, pageSize);
                break;
            case likeRecord:
                //收藏
                //获取播放记录
                entitys = repository.findByAppIdAndUserIdAndTypeLimitStartAndPageSize(contentTotal.getApp().getId(), contentTotal.getUserId(), contentTotal.getType(), start, pageSize);
                break;
            case signRecord:
                //签到
                //获取签到记录
                entitys = repository.findByAppIdAndUserIdAndType(contentTotal.getApp().getId(), contentTotal.getUserId(), contentTotal.getType());
                break;
            case timeRecord:
                //观看
                //获取今日观看时长记录
                String thisDate = DateUtil.getDate();
                ContentTotal entity = repository.getByAppIdAndUserIdThisDateAndType(contentTotal.getApp().getId(), contentTotal.getUserId(), thisDate + "%", contentTotal.getType());
                entitys.add(entity);
                break;
            case blackRecord:
                // 黑名单
                entitys = repository.findByAppIdAndUserIdAndType(contentTotal.getApp().getId(), contentTotal.getUserId(), contentTotal.getType());
                break;
            default:
                break;
        }
        return entitys;
    }

    @Override
    public ContentTotal getByUserIdAndContentId(String userId, String contentId, Integer type) {
        UserActionTypeEnum userActionTypeEnum = UserActionTypeEnum.val(type);
        switch (userActionTypeEnum) {
            case playRecord:
                // 播放记录时，单独逻辑
                List<ContentTotal> list = repository.findByUserIdAndBizValueAndTypeOrderByCreateDateDesc(userId, contentId, type);
                if (list.size() > 0) {
                    return list.get(0);
                } else {
                    return null;
                }
            default:
                return repository.getByUserIdAndBizValueAndType(userId, contentId, type);
        }
    }

    @Override
    public void delete(String appId, String userId, String contentIds, Integer type) {
        String[] contentIdArray = contentIds.split(",");
        for (String contentId : contentIdArray) {
            System.out.println(contentId);
            List<ContentTotal> contentTotals = repository.getByAppIdAndUserIdAndBizValueAndType(appId, userId, contentId, type);
            if (contentTotals.size() > 0) {
                for (ContentTotal contentTotal : contentTotals) {
                    if (StringUtils.isNotBlank(contentTotal.getId())) {
                        this.deleteByEntity(contentTotal);
                    }
                }
            }else {
                throw new RuntimeException("删除播放记录为空");
            }
        }
    }

    @Override
    public Long getCountByAppIdAndUserIdAndType(String appId, String userId, Integer type) {
        return repository.getCountByAppIdAndUserIdAndType(appId, userId, type);
    }

    @Override
    public Long getCountByAppIdAndUserIdAndTypeGroupByBizValue(String appId, String userId, Integer type) {
        return repository.getCountByAppIdAndUserIdAndTypeGroupByBizValue(appId, userId, type);
    }

    @Override
    public void saveKeyword(String keyword, String appId) {
        ContentTotal contentTotal = repository.getByAppIdAndBizValueAndType(appId, keyword,
                UserActionTypeEnum.queryKeyWork.getIndex());
        if (contentTotal != null) {
            contentTotal.setNumber(contentTotal.getNumber() + 1);
            this.update(contentTotal);
        } else {
            Column app = columnService.findById(appId);
            contentTotal = new ContentTotal(keyword, "", "", app,
                    UserActionTypeEnum.queryKeyWork.getIndex(), 1);
            this.save(contentTotal);
        }
    }

    @Override
    public List<ContentTotal> findKeywordByAppIdAndCount(String appId, Integer count) {
        return repository.findKeywordByAppIdAndCount(appId, count,
                UserActionTypeEnum.queryKeyWork.getIndex());
    }

    @Override
    public List<ContentTotal> findByPage(Pager webPage, ContentTotalSearchVo contentTotalSearchVo) {
        String sql = createSql(contentTotalSearchVo);
        return query(sql, webPage);
    }


    /**
     * 创建底层sql
     *
     * @param contentTotalSearchVo
     * @return
     */
    public String createSql(ContentTotalSearchVo contentTotalSearchVo) {
        // 查询sql
        String sql = "select c.id,c.bizValue,c.number,c.duration,c.userId,c.appId,c.type,c.createDate" +
                " from total_content c,vip_user_account a" +
                " where c.userId = a.userId";

        // 拼接条件
        if (contentTotalSearchVo.getType() != null) {
            sql += " and c.type = " + contentTotalSearchVo.getType();
        }
        if (StringUtils.isNotBlank(contentTotalSearchVo.getAppId())) {
            sql += " and c.appId = " + contentTotalSearchVo.getAppId();
        }
        if (StringUtils.isNotBlank(contentTotalSearchVo.getAccountNo())) {
            sql += " and a.accountNo like '%" + contentTotalSearchVo.getAccountNo() + "%'";
        }

        sql += " order by c.createDate desc";

        return sql;
    }

    /**
     * 执行通用查询以及转换方法
     *
     * @param sql     底层sql
     * @param webPage 分页对象
     * @return
     */
    public List<ContentTotal> query(String sql, Pager webPage) {
        // 每页条数
        int pageSize = Integer.valueOf(webPage.getPageSize());
        // 页数
        int pageNum = Integer.valueOf(webPage.getPageNumber());
        // 分页 起始值
        int start = (pageNum - 1) * pageSize;

        // 查询分页数据
        SQLQuery listSqlQuery = entityManager.createNativeQuery(sql + " limit ?1,?2").setParameter(1, start).setParameter(2, pageSize)
                .unwrap(SQLQuery.class);

        // 返回接口映射为map
        List listTemps = listSqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

        // 处理list数据
        List<ContentTotal> contentTotals = Lists.newArrayList();
        for (Object obj : listTemps) {
            Map row = (Map) obj;
            ContentTotal contentTotal = new ContentTotal();
            contentTotal.setId((String) row.get("id"));
            contentTotal.setBizValue((String) row.get("bizValue"));

            contentTotal.setNumber((Integer) row.get("number"));
            contentTotal.setDuration((String) row.get("duration"));
            contentTotal.setUserId((String) row.get("userId"));

            Column app = columnService.findById((String) row.get("appId"));
            contentTotal.setApp(app);
            contentTotal.setType((Integer) row.get("type"));

            contentTotal.setCreateDate((Date) row.get("createDate"));
            contentTotals.add(contentTotal);
        }


        // 查询总条数
        SQLQuery countSqlQuery = entityManager.createNativeQuery(sql).unwrap(SQLQuery.class);

        // 返回接口映射为map
        List countTemps = countSqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

        webPage.setTotalCount(countTemps.size());
        return contentTotals;
    }
}
