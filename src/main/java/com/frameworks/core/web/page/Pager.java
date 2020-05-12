package com.frameworks.core.web.page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pager {

    /**
     * 默认每页记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    private int pageNumber = 1;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private long totalCount;

    private int totalPage = 1;

    private String sortName;

    private String sortOrder;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber > 0 ? pageNumber : 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.totalPage = (int) (totalCount - 1) / this.pageSize + 1;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Pageable toPageable() {
        if (StringUtils.isNotBlank(this.sortName)) {
            PageRequest pageRequest = new PageRequest(this.pageNumber - 1, this.pageSize, Sort.Direction.fromString(this.sortOrder), this.sortName);
            pageRequest.getSort().and(new Sort(Sort.Direction.DESC, "modifyDate"));
            return pageRequest;
        }
        return new PageRequest(this.pageNumber - 1, this.pageSize, Sort.Direction.DESC, "modifyDate");
    }
}
