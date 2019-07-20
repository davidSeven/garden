package com.stream.garden.framework.api.vo;

import com.stream.garden.framework.api.model.PageSize;

import java.util.ArrayList;
import java.util.List;

public class BasePageVO<T, ID> extends BaseVO<T, ID> {

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

    public BasePageVO<T, ID> asOrderByUpdationDate() {
        return asOrderByUpdationDate(OrderByObj.DESC);
    }

    public BasePageVO<T, ID> asOrderByUpdationDate(int orderByMode) {
        List<OrderByObj> orders = new ArrayList<>();
        orders.add(new OrderByObj("UPDATION_DATE", orderByMode));
        this.getCriteria().setOrderByClauses(orders);
        return this;
    }
}
