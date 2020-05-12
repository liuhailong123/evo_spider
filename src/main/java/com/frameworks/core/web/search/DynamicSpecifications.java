package com.frameworks.core.web.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.frameworks.core.web.constants.WebConsts;
import com.frameworks.core.web.search.SearchFilter.Operator;
import com.frameworks.utils.Exceptions;
import com.frameworks.utils.ServletUtils;

import cn.com.evo.admin.manage.domain.entity.Organization;

public class DynamicSpecifications {
    private static final Logger logger = LogManager.getLogger(DynamicSpecifications.class);

    // 用于存储每个线程的request请求
    private static final ThreadLocal<HttpServletRequest> LOCAL_REQUEST = new ThreadLocal<HttpServletRequest>();

    private static final String SHORT_DATE = "yyyy-MM-dd";
    private static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME = "HH:mm:ss";

    public static void putRequest(HttpServletRequest request) {
        LOCAL_REQUEST.set(request);
    }

    public static HttpServletRequest getRequest() {
        return LOCAL_REQUEST.get();
    }

    public static void removeRequest() {
        LOCAL_REQUEST.remove();
    }

    public static Collection<SearchFilter> genSearchFilter(HttpServletRequest request) {
        Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, WebConsts.SEARCH_PREFIX);
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        return filters.values();
    }

    public static <T> Specification<T> bySearchFilter(HttpServletRequest request, final Class<T> entityClazz, Organization org, final Collection<SearchFilter> searchFilters) {
        return bySearchFilter(request, entityClazz, org, searchFilters.toArray(new SearchFilter[]{}));
    }

    public static <T> Specification<T> bySearchFilter(HttpServletRequest request, final Class<T> entityClazz, Organization org, final SearchFilter... searchFilters) {
        Collection<SearchFilter> filters = genSearchFilter(request);
        Set<SearchFilter> set = new HashSet<SearchFilter>(filters);
        for (SearchFilter searchFilter : searchFilters) {
            set.add(searchFilter);
        }
        //增加数据权限
        if (org != null) {
            set.add(new SearchFilter("authId", Operator.EQ, getBaseOrgId(org)));
        }
        return bySearchFilter(entityClazz, set);
    }

    public static <T> Specification<T> bySearchFilter1(HttpServletRequest request, final Class<T> entityClazz, Organization org, final SearchFilter... searchFilters) {
        Collection<SearchFilter> filters = genSearchFilter(request);
        Set<SearchFilter> set = new HashSet<SearchFilter>(filters);
        for (SearchFilter searchFilter : searchFilters) {
            set.add(searchFilter);
        }
        //增加数据权限
        if (org != null) {
            set.add(new SearchFilter("authId", Operator.EQ, getBaseOrgId(org)));
        }
        return bySearchFilter2(entityClazz, set);
    }

    @SuppressWarnings("unchecked")
    public static <T> Specification<T> bySearchFilter(final Class<T> entityClazz, final Collection<SearchFilter> searchFilters) {
        final Set<SearchFilter> filterSet = new HashSet<SearchFilter>();
        HttpServletRequest request = getRequest();
        if (request != null) {
            // 数据权限中的filter
            Collection<SearchFilter> nestFilters =
                    (Collection<SearchFilter>) request.getAttribute(WebConsts.NEST_DYNAMIC_SEARCH);
            if (nestFilters != null && !nestFilters.isEmpty()) {
                for (SearchFilter searchFilter : nestFilters) {
                    filterSet.add(searchFilter);
                }
            }
        }

        // 自定义
        for (SearchFilter searchFilter : searchFilters) {
            filterSet.add(searchFilter);
        }

        return new Specification<T>() {

            @SuppressWarnings({"rawtypes"})
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (filterSet != null && !filterSet.isEmpty()) {
                    List<Predicate> predicates = new ArrayList<Predicate>();
                    for (SearchFilter filter : filterSet) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.getFieldName(), ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }

                        // 自动进行enum和date的转换。
                        Class clazz = expression.getJavaType();
                        if (Date.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
                            filter.setValue(convert2Date((String) filter.getValue()));
                        } else if (Enum.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
                            filter.setValue(convert2Enum(clazz, (String) filter.getValue()));
                        }
                        // logic operator
                        switch (filter.getOperator()) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.getValue()));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, "%" + filter.getValue() + "%"));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.getValue()));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.getValue()));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case IN:
                                predicates.add(builder.and(expression.in((Object[]) filter.getValue())));
                                break;
                            case NOTEQ:
                                predicates.add(builder.notEqual(expression, filter.getValue()));
                                break;
                            case ISNOTNULL:
                                predicates.add(builder.isNotNull(expression));
                                break;
                            default:
                                break;
                        }
                    }

                    // 将所有条件用 and 联合起来
                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }

        };
    }

    /**
     * 去除like 前后百分号
     *
     * @param entityClazz
     * @param searchFilters
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Specification<T> bySearchFilter1(final Class<T> entityClazz, final Collection<SearchFilter> searchFilters) {
        final Set<SearchFilter> filterSet = new HashSet<SearchFilter>();
        HttpServletRequest request = getRequest();
        if (request != null) {
            // 数据权限中的filter
            Collection<SearchFilter> nestFilters =
                    (Collection<SearchFilter>) request.getAttribute(WebConsts.NEST_DYNAMIC_SEARCH);
            if (nestFilters != null && !nestFilters.isEmpty()) {
                for (SearchFilter searchFilter : nestFilters) {
                    filterSet.add(searchFilter);
                }
            }
        }

        // 自定义
        for (SearchFilter searchFilter : searchFilters) {
            filterSet.add(searchFilter);
        }

        return new Specification<T>() {

            @SuppressWarnings({"rawtypes"})
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (filterSet != null && !filterSet.isEmpty()) {
                    List<Predicate> predicates = new ArrayList<Predicate>();
                    for (SearchFilter filter : filterSet) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.getFieldName(), ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }

                        // 自动进行enum和date的转换。
                        Class clazz = expression.getJavaType();
                        if (Date.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
                            filter.setValue(convert2Date((String) filter.getValue()));
                        } else if (Enum.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
                            filter.setValue(convert2Enum(clazz, (String) filter.getValue()));
                        }
                        // logic operator
                        switch (filter.getOperator()) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.getValue()));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, filter.getValue() + ""));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.getValue()));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.getValue()));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case IN:
                                predicates.add(builder.and(expression.in((Object[]) filter.getValue())));
                                break;
                            case NOTEQ:
                                predicates.add(builder.notEqual(expression, filter.getValue()));
                                break;
                            case ISNOTNULL:
                                predicates.add(builder.isNotNull(expression));
                                break;
                            default:
                                break;
                        }
                    }

                    // 将所有条件用 and 联合起来
                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }

        };
    }

    private static Date convert2Date(String dateString) {
        SimpleDateFormat sFormat = new SimpleDateFormat(SHORT_DATE);
        try {
            return sFormat.parse(dateString);
        } catch (ParseException e) {
            try {
                sFormat = new SimpleDateFormat(LONG_DATE);
                return sFormat.parse(dateString);
            } catch (ParseException e1) {
                try {
                    sFormat = new SimpleDateFormat(TIME);
                    return sFormat.parse(dateString);
                } catch (ParseException e2) {
                    logger.error("Convert time is error! The dateString is " + dateString + "." + Exceptions.getStackTraceAsString(e2));
                }
            }
        }

        return null;
    }


    private static <E extends Enum<E>> E convert2Enum(Class<E> enumClass, String enumString) {
        return EnumUtils.getEnum(enumClass, enumString);
    }

    private static String getBaseOrgId(Organization org) {
        int level = org.getLevel();
        while (level != 0) {//循环拿到顶层机构id
            org = org.getParent();
            level--;
        }
        return org.getId();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Specification<T> bySearchFilter2(final Class<T> entityClazz, final Collection<SearchFilter> searchFilters) {
        final Set<SearchFilter> filterSet = new HashSet<SearchFilter>();
        HttpServletRequest request = getRequest();
        if (request != null) {
            // 数据权限中的filter
            Collection<SearchFilter> nestFilters =
                    (Collection<SearchFilter>) request.getAttribute(WebConsts.NEST_DYNAMIC_SEARCH);
            if (nestFilters != null && !nestFilters.isEmpty()) {
                for (SearchFilter searchFilter : nestFilters) {
                    filterSet.add(searchFilter);
                }
            }
        }

        // 自定义
        for (SearchFilter searchFilter : searchFilters) {
            filterSet.add(searchFilter);
        }

        return new Specification<T>() {

            @SuppressWarnings({"rawtypes"})
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (filterSet != null && !filterSet.isEmpty()) {
                    List<Predicate> predicates = new ArrayList<Predicate>();
                    for (SearchFilter filter : filterSet) {
                        // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                        String[] names = StringUtils.split(filter.getFieldName(), ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }

                        // 自动进行enum和date的转换。
                        Class clazz = expression.getJavaType();
                        if (Date.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
                            filter.setValue(convert2Date((String) filter.getValue()));
                        } else if (Enum.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
                            filter.setValue(convert2Enum(clazz, (String) filter.getValue()));
                        }
                        // logic operator
                        switch (filter.getOperator()) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.getValue()));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, filter.getValue() + "%"));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.getValue()));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.getValue()));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case IN:
                                predicates.add(builder.and(expression.in((Object[]) filter.getValue())));
                                break;
                            case NOTEQ:
                                predicates.add(builder.notEqual(expression, filter.getValue()));
                                break;
                            case ISNOTNULL:
                                predicates.add(builder.isNotNull(expression));
                                break;
                            default:
                                break;
                        }
                    }

                    // 将所有条件用 and 联合起来
                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }

        };
    }
    
}
