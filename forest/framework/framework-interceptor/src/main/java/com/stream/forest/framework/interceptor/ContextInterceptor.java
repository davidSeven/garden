package com.stream.forest.framework.interceptor;

import com.stream.forest.framework.common.constant.CommonConstant;
import com.stream.forest.framework.common.context.RequestContext;
import com.stream.forest.framework.utils.ProjectUtil;
import com.stream.forest.framework.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
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
 *
 * @author MAYUE
 */
@Component
@Slf4j
public class ContextInterceptor implements HandlerInterceptor {
    /**
     * 默认的用户ID（配置文件保存）
     */
    @Value("${info.config.loginUserId:}")
    private String configLoginUserId;
    /**
     * 默认的用户省码（配置文件保存）
     */
    @Value("${info.config.loginAddrCode:}")
    private String configLoginAddrCode;
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
        log.info(uri + " Start");
        // Reset pre-context
        RequestContext context = RequestContext.getCurrentContext();
        context.reset();
        // requestId
        String requestId = MDC.get("traceId");
        if (StringUtils.isBlank(requestId)) {
            requestId = request.getHeader(RequestContext.REQUEST_ID);
        }
        if (StringUtils.isBlank(requestId)) {
            requestId = UUIDUtil.uuid();
        }
        MDC.put("TID", requestId);
        // requestStartTime
        Timestamp requestStartTime;
        String startTime = request.getHeader(RequestContext.REQUEST_START_TIME);
        if (StringUtils.isBlank(startTime)) {
            requestStartTime = new Timestamp(System.currentTimeMillis());
        } else {
            requestStartTime = new Timestamp(Long.parseLong(startTime));
        }
        // clientIpAddress
        String clientIpAddress = ProjectUtil.getClientRealAddress(request);
        // token
        String token = request.getHeader(RequestContext.LOGIN_TOKEN);
        String loginUserId = request.getHeader(RequestContext.LOGIN_USER_ID);
        if (StringUtils.isBlank(loginUserId)) {
            loginUserId = this.configLoginUserId;
        }
        String loginAddrCode = request.getHeader(RequestContext.LOGIN_ADDR_CODE);
        if (StringUtils.isBlank(loginAddrCode)) {
            loginAddrCode = this.configLoginAddrCode;
        }
        String loginAppType = request.getHeader(RequestContext.LOGIN_APP_TYPE);
        // originApp，先从threadLocal，再从referer中
        String originApp = request.getHeader(RequestContext.ORIGIN_APP);
        if (StringUtils.isEmpty(originApp)) {
            String refer = decode(request.getHeader(RequestContext.REFERER));
            originApp = getAppByReferer(refer);
        }
        String eventPage = request.getHeader(RequestContext.EVENT_PAGE);
        if (StringUtils.isNotBlank(eventPage)) {
            eventPage = decode(eventPage);
        }
        String eventComponent = request.getHeader(RequestContext.EVENT_COMPONENT);
        if (StringUtils.isNotBlank(eventComponent)) {
            eventComponent = decode(eventComponent);
        }
        String eventType = request.getHeader(RequestContext.EVENT_TYPE);
        if (StringUtils.isNotBlank(eventType)) {
            eventType = decode(eventType);
        }
        String oddNumber = request.getHeader(RequestContext.ODD_NUMBER);
        if (StringUtils.isNotBlank(oddNumber)) {
            oddNumber = decode(oddNumber);
        }
        boolean clientDebugMode = "true".equalsIgnoreCase(request.getHeader(RequestContext.CLIENT_DEBUG_MODE));
        context.setClientDebugMode(clientDebugMode);
        context.setRequestId(requestId);
        context.setUri(uri);
        context.setLoginUserId(loginUserId);
        context.setLoginAddrCode(loginAddrCode);
        context.setLoginAppType(loginAppType);
        context.setRequestStartTime(requestStartTime);
        context.setToken(token);
        context.setClientIpAddress(clientIpAddress);
        context.setOriginApp(originApp);
        context.setEventPage(eventPage);
        context.setEventComponent(eventComponent);
        context.setEventType(eventType);
        context.setOddNumber(oddNumber);
        context.setServletRequest(request);
        context.setServletResponse(response);
        log.info("IP:{},User:{},Addr:{},EventPage:{},EventComp:{},EventType:{},oddNum:{},OriApp:{}", context.getClientIpAddress(), context.getLoginUserId(), context.getLoginAddrCode(), context.getEventPage(), context.getEventComponent(), context.getEventType(), context.getOddNumber(), context.getOriginApp());

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // Authorize annoAuthorize = handlerMethod.getMethodAnnotation(Authorize.class);
            // if (annoAuthorize != null && annoAuthorize.code().length > 0) {
            // // 如果定义了Authorize注解，进行权限检查
            // checkAuthorize(annoAuthorize.code());
            // }
            // 记录方法签名信息
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            context.setApiMethodParameters(methodParameters);
        }

        // 禁用生产的swagger
        if (profile.equalsIgnoreCase("production")) {
            if (uri.equalsIgnoreCase("/swagger-ui.html") || uri.equalsIgnoreCase("/swagger-resources/configuration/ui") || uri.equalsIgnoreCase("/swagger-resources/configuration/security") || uri.equalsIgnoreCase("/swagger-resources")) {
                response.sendRedirect("/swagger.html");
            }
            if (uri.equalsIgnoreCase("/swagger.html")) {
                response.reset();
                response.setCharacterEncoding(CommonConstant.ENCODING);
                response.setContentType(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8");
                PrintWriter pw = response.getWriter();
                pw.write("<h1>该环境[" + profile + "]的swagger禁止访问！</h1>");
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
        log.info(request.getRequestURI() + " End");
    }

    /**
     * getAppByReferer
     *
     * @param referer String
     * @return String
     */
    protected String getAppByReferer(String referer) {
        // "referer": "http://scmjd31.novalocal.com:8100/g4TD/web-plan/notice-demand-view?noticeType=1&noticeVer=f352cb36ae8a4e4aabb28de24197c375&taskListFlag=1&taskEntity.deleteFlag=2&desktopFlag=1&_r=19921081846636908608",
        URL url;
        try {
            url = new URL(referer);
        } catch (MalformedURLException e) {
            return "unknown";
        }
        if (log.isTraceEnabled()) {
            log.trace("URL 是 " + url.toString());
            log.trace("协议是 " + url.getProtocol());// http
            log.trace("文件名是 " + url.getFile());// /g4TD/web-plan/notice-demand-view?noticeType=1&noticeVer=f352cb36ae8a4e4aabb28de24197c375&taskListFlag=1&taskEntity.deleteFlag=2&desktopFlag=1&_r=19921081846636908608
            log.trace("主机是 " + url.getHost());// scmjd31.novalocal.com
            log.trace("路径是 " + url.getPath());// /g4TD/web-plan/notice-demand-view
            log.trace("端口号是 " + url.getPort());// 8100
            log.trace("默认端口号是 " + url.getDefaultPort());// 80
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
            log.error(e.getMessage(), e);
        }
        return "";
    }
}
