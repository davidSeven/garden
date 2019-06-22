package com.stream.garden.framework.web.filter;

import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.constant.GlobalConstant;
import com.stream.garden.framework.web.model.Context;
import com.stream.garden.framework.web.util.ContextUtil;
import com.stream.garden.framework.web.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 * @date 2019-06-22 14:04
 */
public class ContextFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GlobalConfig globalConfig;
    private PathMatcher pathMatcher = new AntPathMatcher();
    private List<String> excludePath = new ArrayList<>();

    public ContextFilter() {
    }

    public ContextFilter(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (StringUtils.isNotEmpty(globalConfig.getLoginPath())) {
            excludePath.add(globalConfig.getLoginPath());
        }
        if (CollectionUtil.isNotEmpty(globalConfig.getExcludePath())) {
            excludePath.addAll(globalConfig.getExcludePath());
        }
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
        for (String path : excludePath) {
            if (pathMatcher.match(path, uri)) {
                jump = true;
                break;
            }
        }

        if (!jump) {
            if (GlobalConstant.OPTIONS.equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                try {
                    final Claims claims = JwtHelper.parseJWT(request, globalConfig.getJwt().getBase64Secret());
                    if (claims == null) {
                        throw new ServletException("登录过期");
                    }
                    Context context = new Context();
                    context.setUserName((String) claims.get("unique_name"));
                    ContextUtil.setContext(context);
                } catch (Exception e) {
                    // throw new ServletException("登录认证失败");
                    String contextPath = request.getContextPath();
                    response.sendRedirect(contextPath + globalConfig.getLoginPath());
                    return;
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
