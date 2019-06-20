package com.stream.garden.framework.api.model;

import java.util.List;

/**
 * 分页数据信息
 *
 * @param <T>
 */
public class PageInfo<T> extends PageSize {

    // 当前返回的记录列表
    private List<T> rows = null;
    // 总记录数
    private int rowTotal = 0;
    // 总页数
    private int pageTotal = 0;

    public PageInfo() {
    }

    public PageInfo(PageSize pageSize) {
        super(pageSize);
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(int rowTotal) {
        this.rowTotal = rowTotal;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

}
