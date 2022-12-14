package com.stream.garden.framework.api.vo;

import com.stream.garden.framework.api.model.PageSize;

import java.util.ArrayList;
import java.util.List;

public class BasePageVO<T> extends BaseVO<T> {

    private static final long serialVersionUID = 7445342957138040313L;
    private PageSize pageSize = new PageSize();
    private Criteria<T> criteria;

    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }

    public Criteria<T> getCriteria() {
        if (null == criteria && null != super.getData()) {
            criteria = new Criteria<>();
            criteria.setVo(super.getData());
        }
        return criteria;
    }

    public void setCriteria(Criteria<T> criteria) {
        this.criteria = criteria;
    }

    public BasePageVO<T> asOrderByUpdationDate() {
        return this.asOrderByUpdationDate(OrderByObj.DESC);
    }

    public BasePageVO<T> asOrderByUpdationDate(int orderByMode) {
        return this.asOrderBy("UPDATION_DATE", orderByMode);
    }

    public BasePageVO<T> asOrderBy(String field) {
        return this.asOrderBy(field, OrderByObj.DESC);
    }

    public BasePageVO<T> asOrderBy(String field, int orderByMode) {
        if (null == this.criteria) {
            this.criteria = new Criteria<>();
        }
        List<OrderByObj> orderByClauses = this.criteria.getOrderByClauses();
        if (null == orderByClauses) {
            orderByClauses = new ArrayList<>();
        }
        orderByClauses.add(new OrderByObj(field, orderByMode));
        this.criteria.setOrderByClauses(orderByClauses);
        return this;
    }
}
