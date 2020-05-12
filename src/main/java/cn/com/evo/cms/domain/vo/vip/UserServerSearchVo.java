package cn.com.evo.cms.domain.vo.vip;

import com.frameworks.core.web.constants.WebConsts;
import com.frameworks.core.web.search.SearchFilter;
import com.frameworks.utils.ServletUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;


/**
 * 用户服务开通搜索对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserServerSearchVo {
    private String appId;
    private String userId;
    private String accountNo;

    public UserServerSearchVo(HttpServletRequest request) {
        Collection<SearchFilter> searchFilters = getSearchFilter(request);
        for (SearchFilter searchFilter : searchFilters) {
            String fieldName = searchFilter.getFieldName();
            String value = searchFilter.getValue().toString();
            if ("appId".equals(fieldName)) {
                this.appId = value;
            }
            if ("userId".equals(fieldName)) {
                this.userId = value;
            }
            if ("accountNo".equals(fieldName)) {
                this.accountNo = value;
            }
        }
    }

    /**
     * 请求转换查询条件
     *
     * @param request
     * @return
     */
    public static Collection<SearchFilter> getSearchFilter(HttpServletRequest request) {
        Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, WebConsts.SEARCH_PREFIX);
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        return filters.values();
    }
}