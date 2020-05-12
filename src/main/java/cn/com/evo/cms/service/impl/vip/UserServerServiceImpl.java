package cn.com.evo.cms.service.impl.vip;

import cn.com.evo.cms.domain.entity.pay.ProductServerRel;
import cn.com.evo.cms.domain.entity.pay.Rule;
import cn.com.evo.cms.domain.entity.pay.Server;
import cn.com.evo.cms.domain.entity.pay.ServerRuleRel;
import cn.com.evo.cms.domain.entity.vip.UserServer;
import cn.com.evo.cms.domain.enums.UnitEnum;
import cn.com.evo.cms.domain.vo.vip.UserServerSearchVo;
import cn.com.evo.cms.domain.vo.vip.UserServerVo;
import cn.com.evo.cms.repository.vip.UserServerRepository;
import cn.com.evo.cms.service.pay.ProductServerRelService;
import cn.com.evo.cms.service.vip.UserServerService;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.core.web.page.Pager;
import com.frameworks.utils.DateUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServerServiceImpl extends AbstractBaseService<UserServer, String> implements UserServerService {

    protected Logger logger = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserServerRepository userServerRepository;
    @Autowired
    private ProductServerRelService productServerRelService;

    @Override
    protected BaseRepository<UserServer, String> getBaseRepository() {
        return userServerRepository;
    }


    @Override
    public List<UserServer> openServer(String userId, String appId, String productId) {
        try {
            //根据产品id 获取产品服务配置关系 List
            List<ProductServerRel> productServerRels = productServerRelService.findByProductId(productId);

            if (productServerRels == null || productServerRels.size() == 0) {
                throw new RuntimeException("产品服务配置关系不存在!!!");
            }

            List<UserServer> userServers = Lists.newArrayList();
            //遍历 产品服务配置关系 List
            for (ProductServerRel productServerRel : productServerRels) {
                //获取产品服务配置 （服务+规则）
                ServerRuleRel serverRuleRel = productServerRel.getServerRuleRel();
                //获取 规则
                Rule rule = serverRuleRel.getRule();
                //获取 服务
                Server server = serverRuleRel.getServer();

                //规则类型
                UnitEnum unitEnum = UnitEnum.getByValue(Integer.valueOf(rule.getUnit()));
                if (unitEnum.equals(UnitEnum.min)
                        || unitEnum.equals(UnitEnum.hour)
                        || unitEnum.equals(UnitEnum.day)
                        || unitEnum.equals(UnitEnum.month)
                        || unitEnum.equals(UnitEnum.year)) {
                    //查询用户是否开通 此服务
                    UserServer userServer = userServerRepository.getByUserIdAndAppIdAndServerCode(userId, appId, server.getCode());
                    if (userServer == null) {
                        //未开通过服务
                        //计算 服务到期时间
                        String maturityTime = calculateMaturityTime(System.currentTimeMillis(), rule.getUnit(), rule.getCount());
                        //保存用户服务开通情况
                        userServer = new UserServer(userId, appId, server.getCode(), maturityTime);
                        super.save(userServer);
                    } else {
                        //开通过服务
                        Long startTime = System.currentTimeMillis();
                        //判断服务是否已到期
                        if (DateUtil.compareTime(userServer.getMaturityTime())) {
                            //已到期,直接使用当前时间
                        } else {
                            //未到期,使用原服务到期时间
                            startTime = DateUtil.stringToDate(userServer.getMaturityTime()).getTime();
                        }
                        //计算 服务到期时间
                        String maturityTime = calculateMaturityTime(startTime, rule.getUnit(), rule.getCount());
                        userServer.setMaturityTime(maturityTime);
                        super.update(userServer);
                    }
                    userServers.add(userServer);
                } else {
                    //TODO 图书逻辑暂未实现，。。。。。。
                }
            }
            return userServers;
        } catch (Exception e) {
            logger.error("服务开通失败", e.getMessage());
            throw new RuntimeException("服务开通失败" + e);
        }
    }

    @Override
    public void openServer(String userId, String appId, String productId, String beginTime, String endTime) {
        List<UserServer> userServers = this.openServer(userId, appId, productId);
        for (UserServer userServer : userServers) {
            userServer.setCreateDate(DateUtil.stringToDate(beginTime));
            userServer.setMaturityTime(endTime);

            super.update(userServer);
        }
    }

    @Override
    public void closeServer(String userId, String appId, String productId) {
        try {
            //根据产品id 获取产品服务配置关系 List
            List<ProductServerRel> productServerRels = productServerRelService.findByProductId(productId);

            if (productServerRels == null || productServerRels.size() == 0) {
                throw new RuntimeException("产品服务配置关系不存在!!!");
            }

            //遍历 产品服务配置关系 List
            for (ProductServerRel productServerRel : productServerRels) {
                //获取产品服务配置 （服务+规则）
                ServerRuleRel serverRuleRel = productServerRel.getServerRuleRel();
                //获取 规则
                Rule rule = serverRuleRel.getRule();
                //获取 服务
                Server server = serverRuleRel.getServer();

                //规则类型
                UnitEnum unitEnum = UnitEnum.getByValue(Integer.valueOf(rule.getUnit()));
                if (unitEnum.equals(UnitEnum.min)
                        || unitEnum.equals(UnitEnum.hour)
                        || unitEnum.equals(UnitEnum.day)
                        || unitEnum.equals(UnitEnum.month)
                        || unitEnum.equals(UnitEnum.year)) {
                    //查询用户是否开通 此服务
                    UserServer userServer = userServerRepository.getByUserIdAndAppIdAndServerCode(userId, appId, server.getCode());
                    if (userServer == null) {
                        //未开通过服务
                        throw new RuntimeException("未开通过该服务");
                    } else {
                        //开通过服务
                        //计算 服务到期时间
                        String maturityTime = calculateBeginTime(DateUtil.stringToDate(userServer.getMaturityTime()).getTime(), rule.getUnit(), rule.getCount());
                        userServer.setMaturityTime(maturityTime);
                        super.update(userServer);
                    }
                } else {
                    //TODO 图书逻辑暂未实现，。。。。。。
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("服务关闭失败" + e);
        }
    }

    @Override
    public boolean checkUserServer(String userId, String serverCode, String appId) {
        //已过期
        boolean result = true;
        try {
            List<UserServer> userServers = userServerRepository.findByUserIdAndAppIdAndServerCode(userId, appId, serverCode);
            if (userServers != null && userServers.size() > 0) {
                UserServer userServer = userServers.get(0);
                if (userServer != null) {
                    //判断服务是否已到期
                    if (!DateUtil.compareTime(userServer.getMaturityTime())) {
                        //未到期
                        result = false;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("用户服务开通情况检测失败" + e);
        }
        return result;
    }

    @Override
    public String getMaturityTimeByUserIdAndAppId(String userId, String appId) {
        String maturityTime = DateUtil.getDateTime();
        List<UserServer> entities = userServerRepository.findByUserIdAndAppIdOrderByMaturityTimeDesc(userId, appId);
        if (entities == null || entities.size() == 0) {
            return maturityTime;
        }
        return entities.get(0).getMaturityTime();
    }

    @Override
    public List<UserServer> findByUserIdAndAppId(String userId, String appId) {
        return userServerRepository.findByUserIdAndAppIdOrderByMaturityTimeDesc(userId, appId);
    }

    @Override
    public UserServer getByUserIdAndAppIdAndServerCode(String userId, String appId, String serverCode) {
        return userServerRepository.getByUserIdAndAppIdAndServerCode(userId, appId, serverCode);
    }

    @Override
    public Boolean checkUserServerIsExpire(String userId, String appId) {
        Boolean result = true;//已过期
        List<UserServer> entities = this.findByUserIdAndAppId(userId, appId);
        if (entities != null) {
            if (entities.size() > 0) {
                for (UserServer userSever : entities) {
                    result = this.checkUserServer(userId, userSever.getServerCode(), appId);
                    if (!result) {
                        break;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public List<UserServerVo> findByPage(Pager webPage, UserServerSearchVo userServerSearchVo) {
        String sql = createSql(userServerSearchVo);
        return query(sql, webPage);
    }

    /**
     * 计算到期时间
     *
     * @param startTime 开始时间
     * @param unit      单位
     * @param count     时长
     * @return
     */
    private String calculateMaturityTime(Long startTime, String unit, Integer count) {
        //到期时间初始化
        Date maturityTime = new Date();
        switch (UnitEnum.getByValue(Integer.valueOf(unit))) {
            case min:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.MINUTE, count);
                break;
            case hour:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.HOUR_OF_DAY, count);
                break;
            case day:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.DAY_OF_MONTH, count);
                break;
            case month:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.MONTH, count);
                break;
            case year:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.YEAR, count);
                break;
            default:
                break;
        }
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(maturityTime);
    }


    /**
     * 计算开始时间
     *
     * @param endTime 到期时间
     * @param unit    单位
     * @param count   时长
     * @return
     */
    private String calculateBeginTime(Long endTime, String unit, Integer count) {
        //到期时间初始化
        Date maturityTime = new Date();
        switch (UnitEnum.getByValue(Integer.valueOf(unit))) {
            case min:
                maturityTime = DateUtil.subDate(new Date(endTime), Calendar.MINUTE, count);
                break;
            case hour:
                maturityTime = DateUtil.subDate(new Date(endTime), Calendar.HOUR_OF_DAY, count);
                break;
            case day:
                maturityTime = DateUtil.subDate(new Date(endTime), Calendar.DAY_OF_MONTH, count);
                break;
            case month:
                maturityTime = DateUtil.subDate(new Date(endTime), Calendar.MONTH, count);
                break;
            case year:
                maturityTime = DateUtil.subDate(new Date(endTime), Calendar.YEAR, count);
                break;
            default:
                break;
        }
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(maturityTime);
    }


    /**
     * 创建底层sql
     *
     * @param userServerSearchVo
     * @return
     */
    public String createSql(UserServerSearchVo userServerSearchVo) {
        // 查询sql
        String sql = "SELECT s.userId,a.accountNo,s.createDate,s.maturityTime,s.appId FROM vip_user_account a,vip_user_server s WHERE a.userId=s.userId ";

        // 拼接条件
        if (StringUtils.isNotBlank(userServerSearchVo.getAppId())) {
            sql += " and s.appId = '" + userServerSearchVo.getAppId() + "'";
        }
        if (StringUtils.isNotBlank(userServerSearchVo.getUserId())) {
            sql += " and s.userId = '" + userServerSearchVo.getUserId() + "'";
        }
        if (StringUtils.isNotBlank(userServerSearchVo.getAccountNo())) {
            sql += " and a.accountNo like '%" + userServerSearchVo.getAccountNo() + "%'";
        }

        sql += " order by a.accountNo";
        return sql;
    }

    /**
     * 执行通用查询以及转换方法
     *
     * @param sql     底层sql
     * @param webPage 分页对象
     * @return
     */
    public List<UserServerVo> query(String sql, Pager webPage) {
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
        List<UserServerVo> userServers = Lists.newArrayList();
        for (Object obj : listTemps) {
            Map row = (Map) obj;
            UserServerVo userServerVo = new UserServerVo();
            userServerVo.setUserId((String) row.get("userId"));
            userServerVo.setUserAccount((String) row.get("accountNo"));
            userServerVo.setCreateDate((Date) row.get("createDate"));
            userServerVo.setMaturityTime((String) row.get("maturityTime"));
            userServerVo.setAppId((String) row.get("appId"));

            userServers.add(userServerVo);
        }


        // 查询总条数
        SQLQuery countSqlQuery = entityManager.createNativeQuery(sql).unwrap(SQLQuery.class);

        // 返回接口映射为map
        List countTemps = countSqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

        webPage.setTotalCount(countTemps.size());
        return userServers;
    }
}
