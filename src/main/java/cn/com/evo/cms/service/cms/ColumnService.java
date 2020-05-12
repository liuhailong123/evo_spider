package cn.com.evo.cms.service.cms;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.cms.domain.entity.cms.Column;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface ColumnService extends BaseService<Column, String> {

    /**
     * 获取站点下栏目 List
     *
     * @param siteId
     * @return
     */
    List<Column> findAllColumnBySiteId(String siteId);

    /**
     * 获取后台管理用户下已授权的所有站点
     *
     * @param account
     * @return
     */
    List<Column> findByAccount(Account account);


    /**
     * 站点与角色授权
     *
     * @param roleId
     * @param siteIds
     */
    void assign(String roleId, String[] siteIds);

    /**
     * 保存栏目以及附属信息
     *
     * @param entity
     * @param other
     */
    void save(Object entity, Object other, String columnPId);

    /**
     * 更新栏目以及附属信息
     *
     * @param entity
     * @param other
     * @param columnPId
     */
    void update(Object entity, Object other, String columnPId);

    /**
     * 根据目录id获取子级目录-分页
     *
     * @param columnId
     * @param type
     * @param start
     * @param pageSize
     * @return
     */
    List<Column> findByPIdAndType(String columnId, Integer type, Integer start, Integer pageSize);

    /**
     * 根据目录id获取子级目录
     *
     * @param columnId
     * @return
     */
    List<Column> findByPId(String columnId);

    /**
     * 根据栏目id 获取该栏目下 瀑布流总数
     *
     * @param columnId
     * @param type
     * @return
     */
    Long findWaterfallCountByColumnIdAndType(String columnId, int type);

    List<Column> findByNameAndEnable(String columnsName, int enable);

    /**
     * 获取栏目下子分类
     *
     * @param columnId
     * @return
     */
    // List<Column> getChild(String columnId);

    /**
     * 设置或者取消推荐字段
     *
     * @param columnId 栏目id
     * @param type     1-设置；2-取消
     */
    void setRecommend(String columnId, Integer type);

    /**
     * 根据任意栏目id获取应用id
     *
     * @param columnId
     * @return
     */
    String getAppId(String columnId);

    Column getByThirdCode(String code);
}
