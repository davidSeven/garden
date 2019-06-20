package com.stream.garden.framework.api.bo;

/**
 * 业务封装使用
 *
 * @param <T> 业务对象
 * @author garden
 */
public class BaseBO<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
