package com.stream.garden.framework.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.framework.api.model.Context;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.util.ContextUtil;
import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.constant.GlobalConstant;
import com.stream.garden.framework.web.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤
 *
 * @author garden
 * @date 2019-06-22 14:04
 */
public class ContextFilter extends ExcludeFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GlobalConfig globalConfig;

    public ContextFilter(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
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
        final HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        logger.debug("-------------------------------------------");
        logger.debug("request uri: {}", uri);

        boolean jump = super.exclude(uri);

        // 跳过认证
        boolean isRemoteAuthorization = super.isRemoteAuthorization(request);
        if (isRemoteAuthorization) {
            jump = true;
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
                    context.setUserCode((String) claims.get("userCode"));
                    context.setUserId((String) claims.get("userId"));
                    context.setRoleId((String) claims.get("roleId"));
                    ContextUtil.setContext(context);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    String contextPath = request.getContextPath();
                    if (RequestFilterUtils.isJsonRequest(request)) {
                        response.setHeader("Content-Type", "application/json;charset=UTF-8");
                        Result<String> result = new Result<String>().setData(e.getMessage());
                        response.getWriter().append(JSONObject.toJSONString(result));
                        return;
                    } else {
                        response.sendRedirect(contextPath + globalConfig.getLoginPath());
                    }
                    return;
                }
            }
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        this.globalConfig = null;
    }
}
