package com.stream.garden.framework.api.model;

import java.io.Serializable;

/**
 * 分页
 * @author garden
 */
public class PageSize implements Serializable {
    private static final long serialVersionUID = 1536869730417328357L;

    /** 默认每页页数 */
    public static final int DEFAULT_PAGESIZE = 10;

    /** 页码 */
    protected int page;
    /** 每页记录数 */
    protected int pageSize = DEFAULT_PAGESIZE;

    private boolean count = true;

    public PageSize() {

    }

    public PageSize(PageSize pageSize) {
        this(pageSize.getPage(), pageSize.getPageSize(), pageSize.isCount());
    }

    public PageSize(int page, int pageSize, boolean count) {
        this.page = page;
        this.pageSize = pageSize;
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }
}
