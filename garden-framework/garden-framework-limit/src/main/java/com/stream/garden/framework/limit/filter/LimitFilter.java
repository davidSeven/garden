package com.stream.garden.framework.limit.filter;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.limit.config.LimitConfig;
import com.stream.garden.framework.limit.core.HandlerLimit;
import com.stream.garden.framework.limit.core.HandlerLimitFactory;
import com.stream.garden.framework.limit.enums.LimitRule;
import com.stream.garden.framework.limit.exception.LimitExceptionCode;
import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.filter.ExcludeFilter;
import com.stream.garden.framework.web.filter.RequestFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 限流过滤
 *
 * @author garden
 * @date 2019-07-20 8:56
 */
public class LimitFilter extends ExcludeFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GlobalConfig globalConfig;
    private LimitConfig limitConfig;

    public LimitFilter(GlobalConfig globalConfig, LimitConfig limitConfig) {
        this.globalConfig = globalConfig;
        this.limitConfig = limitConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            super.init(globalConfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        /*
        String contentType = request.getHeader("Content-Type");
        logger.debug("Content-Type: {}", contentType);
        logger.debug("Content-Type: {}", res.getContentType());
        */
        boolean jump = super.exclude(uri);
        if (!jump && LimitRule.APPLICATION.equals(limitConfig.getLimitRule())) {
            HandlerLimit handlerLimit = HandlerLimitFactory.getInstance().builder(LimitRule.APPLICATION);
            if (null != handlerLimit && !handlerLimit.handle(null)) {
                logger.error("---{}", LimitExceptionCode.SYSTEM_BUSY.getMessage());
                if (RequestFilterUtils.isJsonRequest(request)) {
                    HttpServletResponse response = (HttpServletResponse) res;
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    Result<String> result = new Result<>(LimitExceptionCode.SYSTEM_BUSY);
                    response.getWriter().append(JSONObject.toJSONString(result));
                    return;
                } else {
                    throw new ServletException(LimitExceptionCode.SYSTEM_BUSY.getMessage());
                }
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        this.globalConfig = null;
        this.limitConfig = null;
    }
}
