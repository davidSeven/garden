package com.stream.garden.framework.web.filter;

import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.constant.GlobalConstant;
import com.stream.garden.framework.web.permission.IPermissionData;
import com.stream.garden.framework.web.permission.PermissionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限过滤
 *
 * @author garden
 * @date 2019-09-28 15:19
 */
public class PermissionFilter extends ExcludeFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GlobalConfig globalConfig;
    private IPermissionData permissionData;

    public PermissionFilter(GlobalConfig globalConfig, IPermissionData permissionData) {
        this.globalConfig = globalConfig;
        this.permissionData = permissionData;
        if (null != permissionData) {
            PermissionContext.reloadPermissionUrl(permissionData.getSystemPermissionList());
        }
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
        boolean jump = super.exclude(uri);

        if (!jump) {
            if (GlobalConstant.OPTIONS.equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                // 判断是否要验证
                String urlCode = PermissionContext.getUrlCode(uri);
                // 判断是否有权限
                if (null != urlCode && null != permissionData && !permissionData.validPermission(urlCode)) {
                    throw new ServletException("无权访问");
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
