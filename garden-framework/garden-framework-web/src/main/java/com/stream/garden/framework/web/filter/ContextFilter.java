package com.stream.garden.framework.web.filter;

import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.util.CookieUtil;
import com.stream.garden.framework.web.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author garden
 * @date 2019-06-22 14:04
 */
public class ContextFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GlobalConfig globalConfig;
    private PathMatcher pathMatcher = new AntPathMatcher();

    public ContextFilter() {}

    public ContextFilter(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        long startTime = System.currentTimeMillis();
        String uri = request.getRequestURI();
        logger.debug("-------------------------------------------");
        logger.debug("request uri: {}", uri);

        boolean jump = false;
        if (CollectionUtil.isNotEmpty(globalConfig.getExcludePath())) {
            for (String path : globalConfig.getExcludePath()) {
                if (pathMatcher.match(path, uri)) {
                    jump = true;
                    break;
                }
            }
        }

        if (!jump) {
            if ("OPTIONS".equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                String authHeader = request.getHeader("authorization");
                if (StringUtils.isEmpty(authHeader)) {
                    authHeader = CookieUtil.getUid(request, "authorization");
                }
                if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("bearer.")) {
                    // String contextPath = request.getContextPath();
                    // response.sendRedirect(contextPath + "/index/login");
                    // String token = JwtHelper.createJWT("", "", "", globalConfig.getJwt());
                    // token = "bearer." + token;
                    // CookieUtil.addCookie(response, "authorization", token, 24 * 60 * 60);
                    CookieUtil.addCookie(response, "authorization", "bearer.123", 24 * 60 * 60);
                    return;
                }
                final String token = authHeader.substring(7);
                try {
                    final Claims claims = JwtHelper.parseJWT(token, globalConfig.getJwt().getBase64Secret());
                    if (claims == null) {
                        throw new ServletException("登录过期");
                    }
                } catch (final Exception e) {
                    throw new ServletException("登录认证失败");
                }
            }
        }

        chain.doFilter(req, res);
        logger.debug("请求耗时：{}", (System.currentTimeMillis() - startTime));
        logger.debug("-------------------------------------------");
    }

    @Override
    public void destroy() {

    }
}
