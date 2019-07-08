package com.stream.garden.framework.api.vo;

import java.util.List;

public class Criteria<T> {

    private T vo;

    private String generic;
    private List<OrderByObj> orderByClauses;

    public T getVo() {
        return vo;
    }

    public void setVo(T vo) {
        this.vo = vo;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public List<OrderByObj> getOrderByClauses() {
        return orderByClauses;
    }

    public void setOrderByClauses(List<OrderByObj> orderByClauses) {
        this.orderByClauses = orderByClauses;
    }
}
