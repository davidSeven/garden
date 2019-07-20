package com.stream.garden.framework.limit.interceptor;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.limit.annotation.Limit;
import com.stream.garden.framework.limit.config.LimitConfig;
import com.stream.garden.framework.limit.core.HandlerLimit;
import com.stream.garden.framework.limit.core.HandlerLimitFactory;
import com.stream.garden.framework.limit.enums.LimitRule;
import com.stream.garden.framework.limit.exception.LimitExceptionCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * 限流拦截器
 * <p>
 * 参考文档
 * <p>
 * 漏桶限流实现
 * https://www.cnblogs.com/carrychan/p/9435979.html
 * <p>
 * SpringCloud令牌桶限流
 * http://kailing.pub/article/index/arcid/251.html?tdsourcetag=s_pctim_aiomsg
 *
 * @author garden
 * @date 2019-07-15 9:08
 */
@Aspect
@Configuration
public class LimitInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LimitInterceptor.class);

    private final LimitConfig limitConfig;

    @Autowired
    public LimitInterceptor(LimitConfig limitConfig) {
        this.limitConfig = limitConfig;
    }

    @Around("execution(public * *(..)) && @annotation(com.stream.garden.framework.limit.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws ApplicationException {
        if (LimitRule.METHOD.equals(limitConfig.getLimitRule())
                || LimitRule.APPLICATION_AND_METHOD.equals(limitConfig.getLimitRule())
                || LimitRule.APPLICATION_CONFIG.equals(limitConfig.getLimitRule())) {
            HandlerLimit handlerLimit = HandlerLimitFactory.getInstance().builder(limitConfig.getLimitRule());
            if (null != handlerLimit) {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                Method method = signature.getMethod();
                Limit limit = method.getAnnotation(Limit.class);
                if (null != limit && !handlerLimit.handle(limit)) {
                    throw new ApplicationException(LimitExceptionCode.SYSTEM_BUSY);
                }
            }
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
            throw new ApplicationException(LimitExceptionCode.SYSTEM_EXCEPTION);
        }
    }

    private LimitRule getLimitRule() {
        if (LimitRule.METHOD.equals(limitConfig.getLimitRule())) {
            return LimitRule.METHOD;
        }
        return null;
    }
}
