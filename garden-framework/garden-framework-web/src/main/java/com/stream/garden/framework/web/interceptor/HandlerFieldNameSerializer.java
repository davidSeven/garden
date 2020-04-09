package com.stream.garden.framework.web.interceptor;

/**
 * @author garden
 * @date 2020-04-09 9:10
 */
public interface HandlerFieldNameSerializer {

    /**
     * 根据code返回name
     *
     * @param code code
     * @return String
     */
    String getName(String code);
}
