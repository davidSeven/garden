package com.stream.garden.framework.web.interceptor;

/**
 * @author garden
 * @date 2020-04-08 10:38
 */
public interface HandlerFieldSerializer {

    /**
     * 根据url过滤
     *
     * @param url url
     * @return FieldSerializer
     */
    FieldSerializer filterUrl(String url);
}
