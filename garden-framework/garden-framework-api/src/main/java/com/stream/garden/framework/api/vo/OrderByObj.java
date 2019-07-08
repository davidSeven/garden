package com.stream.garden.framework.api.vo;

public class OrderByObj {

    // 排序字段
    private String field;
    // 0：ASC，1：DESC
    private int orderByMode;

    public OrderByObj() {
    }

    public OrderByObj(String field) {
        this.field = field;
    }

    public OrderByObj(String field, int orderByMode) {
        this.field = field;
        this.orderByMode = orderByMode;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getOrderByMode() {
        return orderByMode;
    }

    public void setOrderByMode(int orderByMode) {
        this.orderByMode = orderByMode;
    }
}
