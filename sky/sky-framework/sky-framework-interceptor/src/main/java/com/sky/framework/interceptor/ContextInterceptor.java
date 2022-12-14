package com.sky.framework.interceptor;

import com.sky.framework.api.context.CommonConstant;
import com.sky.framework.api.context.RequestContext;
import com.sky.framework.interceptor.util.IpUtil;
import com.sky.framework.interceptor.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;

/**
 * ContextInterceptor
 */
@Component
public class ContextInterceptor implements HandlerInterceptor, Ordered {

    private final Logger logger = LoggerFactory.getLogger(ContextInterceptor.class);

    /**
     * profile
     */
    @Value("${spring.profiles.active:local}")
    private String profile;

    /**
     * preHandle
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String uri = request.getRequestURI();
        // logger.info(uri + " Start");
        // Reset pre-context
        RequestContext context = RequestContext.getCurrentContext();
        context.reset();
        // requestId
        String requestId = MDC.get("X-requestId");
        if (StringUtils.isBlank(requestId)) {
            requestId = request.getHeader(RequestContext.REQUEST_ID);
        }
        if (StringUtils.isBlank(requestId)) {
            requestId = UUIDUtil.uuid();
        }
        MDC.put("X-requestId", requestId);
        // requestStartTime
        Timestamp requestStartTime;
        String startTime = request.getHeader(RequestContext.REQUEST_START_TIME);
        if (StringUtils.isBlank(startTime)) {
            requestStartTime = new Timestamp(System.currentTimeMillis());
        } else {
            requestStartTime = new Timestamp(Long.parseLong(startTime));
        }
        // clientIpAddress
        String clientIpAddress = request.getHeader(RequestContext.CLIENT_IP_ADDRESS);
        if (StringUtils.isEmpty(clientIpAddress)) {
            clientIpAddress = IpUtil.getClientRealAddress(request);
        }
        // token
        String token = request.getHeader(RequestContext.TOKEN);
        String userId = request.getHeader(RequestContext.USER_ID);
        if (StringUtils.isBlank(userId)) {
            userId = "1";
        }
        String userCode = request.getHeader(RequestContext.USER_CODE);
        if (StringUtils.isBlank(userCode)) {
            userCode = "admin";
        }
        String userName = request.getHeader(RequestContext.USER_NAME);
        // originApp?????????threadLocal?????????referer???
        String originApp = request.getHeader(RequestContext.ORIGIN_APP);
        if (StringUtils.isEmpty(originApp)) {
            String refer = decode(request.getHeader(RequestContext.REFERER));
            originApp = getAppByReferer(refer);
        }
        context.setRequestId(requestId);
        context.setUri(uri);
        context.setUserId(userId);
        context.setUserCode(userCode);
        context.setUserName(userName);
        context.setRequestStartTime(requestStartTime);
        context.setToken(token);
        context.setClientIpAddress(clientIpAddress);
        context.setOriginApp(originApp);
        // logger.info("IP:{},UserCode:{}", context.getClientIpAddress(), context.getUserCode());

        // ???????????????swagger
        if (profile.equalsIgnoreCase("production")) {
            if (uri.equalsIgnoreCase("/swagger-ui.html") || uri.equalsIgnoreCase("/swagger-resources/configuration/ui") || uri.equalsIgnoreCase("/swagger-resources/configuration/security") || uri.equalsIgnoreCase("/swagger-resources")) {
                response.sendRedirect("/swagger.html");
            }
            if (uri.equalsIgnoreCase("/swagger.html")) {
                response.reset();
                response.setCharacterEncoding(CommonConstant.ENCODING);
                response.setContentType(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.write("<h1>?????????[" + profile + "]???swagger???????????????</h1>");
                pw.flush();
                pw.close();
                return false;
            }
        }
        return true;
    }

    /**
     * postHandle
     */
    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
    }

    /**
     * afterCompletion
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        RequestContext.getCurrentContext().close();
        // logger.info(request.getRequestURI() + " End");
    }

    /**
     * getAppByReferer
     *
     * @param referer String
     * @return String
     */
    protected String getAppByReferer(String referer) {
        URL url;
        try {
            url = new URL(referer);
        } catch (MalformedURLException e) {
            return "unknown";
        }
        if (logger.isTraceEnabled()) {
            logger.trace("URL ??? " + url.toString());
            logger.trace("????????? " + url.getProtocol());
            logger.trace("???????????? " + url.getFile());
            logger.trace("????????? " + url.getHost());
            logger.trace("????????? " + url.getPath());
            logger.trace("???????????? " + url.getPort());
            logger.trace("?????????????????? " + url.getDefaultPort());
        }
        String[] arr = url.getPath().split(CommonConstant.SPLIT);
        if (arr.length > 2) {
            return arr[2];
        }
        return "unknown";
    }

    /**
     * decode
     *
     * @param str String
     * @return String
     */
    protected String decode(String str) {
        try {
            if (StringUtils.isNotBlank(str)) {
                str = URLDecoder.decode(str, CommonConstant.ENCODING);
                return str;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
