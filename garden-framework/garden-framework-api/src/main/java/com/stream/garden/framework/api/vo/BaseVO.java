package com.stream.garden.framework.api.vo;

import java.io.Serializable;

/**
 * 分页
 *
 * @param <T> 业务对象
 * @author city
 */
public class BaseVO<T> implements Serializable {

    private static final long serialVersionUID = 48442414440502818L;
    private T data;

    private Serializable[] ids;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Serializable[] getIds() {
        return ids;
    }

    public void setIds(Serializable[] ids) {
        this.ids = ids;
    }

    public Long[] getLongIds() {
        if (null == this.ids) {
            return new Long[0];
        }
        Long[] longs = new Long[this.ids.length];
        for (int i = 0; i < this.ids.length; i++) {
            longs[i] = Long.valueOf((String) this.ids[i]);
        }
        return longs;
    }
}
