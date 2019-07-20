package com.stream.garden.framework.api.model;

import com.stream.garden.framework.api.vo.OrderByObj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderBy implements Serializable {

    private static final long serialVersionUID = 1103089400305042567L;
    /**
     * 排序字段
     */
    private List<OrderByObj> orderByClauses;

    public List<OrderByObj> getOrderByClauses() {
        return orderByClauses;
    }

    public void setOrderByClauses(List<OrderByObj> orderByClauses) {
        this.orderByClauses = orderByClauses;
    }

    public OrderBy asOrderByUpdationDate() {
        return asOrderByUpdationDate(OrderByObj.DESC);
    }

    public OrderBy asOrderByUpdationDate(int orderByMode) {
        return asOrderBy("UPDATION_DATE", orderByMode);
    }

    public OrderBy asOrderBy(String field) {
        return asOrderBy(field, OrderByObj.DESC);
    }

    public OrderBy asOrderBy(String field, int orderByMode) {
        List<OrderByObj> orders = new ArrayList<>();
        orders.add(new OrderByObj(field, orderByMode));
        this.setOrderByClauses(orders);
        return this;
    }
}
