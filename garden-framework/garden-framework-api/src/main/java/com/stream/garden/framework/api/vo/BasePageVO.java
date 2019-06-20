package com.stream.garden.framework.api.vo;

import com.stream.garden.framework.api.model.PageSize;

public class BasePageVO<T, ID> extends BaseVO<T, ID> {

    private PageSize pageSize = new PageSize();
    private Criteria<T> criteria;

    public BasePageVO() {
        if (null == criteria) {
            criteria = new Criteria<>();
        }
        criteria.setVo(super.getData());
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }

    public Criteria<T> getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria<T> criteria) {
        this.criteria = criteria;
    }
}
