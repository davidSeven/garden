package com.forest.framework.common.context;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于HttpRequest的上下文信息传递工具<br>
 */
@Getter
@Setter
@Slf4j
public class RequestContext {
    // 允许通过HTTP HEADER传递的参数
    public static final String REQUEST_ID = "requestId";
    public static final String LOGIN_USER_ID = "loginUserId";
    public static final String LOGIN_ADDR_CODE = "loginAddrCode";
    public static final String LOGIN_APP_TYPE = "loginAppType";
    public static final String LOGIN_TOKEN = "AUTHSSOTGC";
    public static final String REQUEST_START_TIME = "requestStartTime";
    public static final String CLIENT_IP_ADDRESS = "clientIpAddress";
    public static final String CLIENT_DEBUG_MODE = "clientDebugMode";
    public static final String EVENT_PAGE = "eventPage";
    public static final String EVENT_COMPONENT = "eventComponent";
    public static final String EVENT_TYPE = "eventType";
    public static final String ORIGIN_APP = "originApp";
    public static final String REFERER = "referer";
    public static final String ODD_NUMBER = "oddNumber";

    /**
     * ThreadLocal instance
     */
    private static final ThreadLocal<RequestContext> contextHolder = new ThreadLocal<>();

    /**
     * 如果需要增加属性，必须同时修改其他文件：ContextInterceptor,FeignRequestInterceptor
     */
    private String requestId;
    private String uri;
    private String loginUserId;
    private String loginAddrCode;
    private String loginAppType;
    private String token;
    private Timestamp requestStartTime;
    private String clientIpAddress;
    private String eventPage;
    private String eventComponent;
    private String eventType;
    private String originApp;
    private String oddNumber;
    private boolean clientDebugMode;

    /**
     * 如果需要增加属性，必须同时修改其他文件：ContextInterceptor,FeignRequestInterceptor
     */
    private String dataSourceId;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private Map<String, Object> parameterMap = new HashMap<>();

    // 当前请求线程是否开启操作日志记录的标志
    private boolean logOperationEnable;
    private String logOperationType;
    private String logOperationContent;

    private Integer defaultPageNum;
    private Integer defaultPageSize;

    // API方法的实参对象
    private Object[] apiMethodArguments;
    // API方法的形参描述
    private MethodParameter[] apiMethodParameters;
    // API的调用堆栈
    private List<String> apiCallStacks = new ArrayList<>();

    /**
     * RequestContext
     */
    private RequestContext() {
    }

    /**
     * getCurrentContext
     *
     * @return RequestContext
     */
    public static synchronized RequestContext getCurrentContext() {
        RequestContext context = contextHolder.get();
        if (context == null) {
            log.info("New RequestContext:" + Thread.currentThread().getName());
            contextHolder.set(new RequestContext());
            context = contextHolder.get();
        }
        return context;
    }

    /**
     * close
     */
    public void close() {
        /**
         *  Can not invoke reset here because it will due to child-thread lost RequestContext data
         * reset(); 
         */

        /**
         *  Must invoke clear() to avoid memory leak
         */
        this.apiCallStacks.clear();
        this.parameterMap.clear();

        /**
         *  remove current thread local
         */
        contextHolder.remove();
    }

    /**
     * reset
     */
    public void reset() {
        log.info("RequestContext reset:" + this.requestId + "," + this.loginUserId + "," + this.loginAddrCode);
        this.requestId = null;
        this.uri = null;
        this.loginUserId = null;
        this.loginAddrCode = null;
        this.loginAppType = null;
        this.token = null;
        this.dataSourceId = null;
        this.requestStartTime = null;
        this.clientIpAddress = null;
        this.eventPage = null;
        this.eventComponent = null;
        this.eventType = null;
        this.originApp = null;
        this.oddNumber = null;
        this.clientDebugMode = false;

        this.logOperationEnable = false;
        this.logOperationType = null;
        this.logOperationContent = null;

        this.defaultPageNum = null;
        this.defaultPageSize = null;

        this.servletRequest = null;
        this.servletResponse = null;

        this.apiMethodArguments = null;
        this.apiMethodParameters = null;

        this.parameterMap.clear();
        this.apiCallStacks.clear();
    }

}
