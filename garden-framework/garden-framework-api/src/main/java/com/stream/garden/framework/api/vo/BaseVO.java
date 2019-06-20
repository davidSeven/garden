package com.stream.garden.framework.api.vo;

/**
 * 分页
 *
 * @param <T> 业务对象
 * @author city
 */
public class BaseVO<T, ID> {

    private T data;

    private ID[] ids;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ID[] getIds() {
        return ids;
    }

    public Long[] getLongIds() {
        if(null == this.ids) {
            return null;
        }
        Long[] longs = new Long[this.ids.length];
        for (int i = 0; i < this.ids.length; i++) {
            longs[i] = Long.valueOf((String) this.ids[i]);
        }
        return longs;
    }

    public void setIds(ID[] ids) {
        this.ids = ids;
    }
}
