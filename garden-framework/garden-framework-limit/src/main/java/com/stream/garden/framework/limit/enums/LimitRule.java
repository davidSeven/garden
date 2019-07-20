package com.stream.garden.framework.limit.enums;

/**
 * 限流规则
 *
 * @author garden
 * @date 2019-07-20 9:08
 */
public enum LimitRule {

    /**
     * 不做任何处理
     */
    NONE,

    /**
     * 根据应用内Controller上的Limit注解进行拦截限流
     * <p>
     * 每个方法都可以定义限流规则
     */
    METHOD,

    /**
     * 对整个应用进行限流处理
     * <p>
     * 所有的接口请求限流规则都使用同一个
     */
    APPLICATION,

    /**
     * 对整个应用定义了Limit注解进行限流处理
     * <p>
     * 统一规则处理
     */
    APPLICATION_AND_METHOD,

    /**
     * 应用内自己配置限流规则
     */
    APPLICATION_CONFIG,

    ;
}
