package com.stream.garden.framework.web.filter;

/**
 * @author garden
 * @date 2019-07-30 11:27
 */
public interface RequestFilterHandler {

    void after(String uri, long times);
}
