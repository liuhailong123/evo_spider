package com.frameworks.core.web.result;

import com.alibaba.fastjson.JSON;

/**
 * @author caoyong
 * @FileName : DataResult.java
 * @Description :
 * @Copyright : Copyright (c) 2015
 * @date Jul 29, 2015 3:12:49 PM
 */
public class DataResult extends MsgResult {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private long total = 0;
    private Object rows;
    private long page;
    private long records;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object data) {
        //this.rows = JSON.parse(JSON.toJSONStringWithDateFormat(data, DEFAULT_DATE_FORMAT, SerializerFeature.WriteDateUseDateFormat));
        JSON.DEFFAULT_DATE_FORMAT = DEFAULT_DATE_FORMAT;
        this.rows = data;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }
}
