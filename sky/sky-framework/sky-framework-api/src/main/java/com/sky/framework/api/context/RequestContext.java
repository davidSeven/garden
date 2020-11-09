package com.sky.framework.api.context;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2020-10-28 028 9:24
 */
public class RequestContext {

    // 允许通过HTTP HEADER传递的参数
    public static final String REQUEST_ID = "requestId";
    public static final String USER_ID = "userId";
    public static final String USER_CODE = "userCode";
    public static final String USER_NAME = "userName";
    public static final String TOKEN = "token";
    public static final String REQUEST_START_TIME = "requestStartTime";
    public static final String CLIENT_IP_ADDRESS = "clientIpAddress";
    public static final String ORIGIN_APP = "originApp";
    public static final String REFERER = "referer";

    /**
     * ThreadLocal instance
     */
    private static final ThreadLocal<RequestContext> contextHolder = new ThreadLocal<>();

    private String requestId;
    private String uri;
    private String userId;
    private String userCode;
    private String userName;
    private String token;
    private Timestamp requestStartTime;
    private String clientIpAddress;
    private String originApp;

    private Map<String, Object> parameterMap = new HashMap<>();

    private JsonContent jsonContent;

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
            contextHolder.set(new RequestContext());
            context = contextHolder.get();
        }
        return context;
    }

    /**
     * close
     */
    public void close() {
        this.parameterMap.clear();
        contextHolder.remove();
    }

    /**
     * reset
     */
    public void reset() {
        this.requestId = null;
        this.uri = null;
        this.userId = null;
        this.userCode = null;
        this.userName = null;
        this.token = null;
        this.requestStartTime = null;
        this.clientIpAddress = null;

        this.parameterMap.clear();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getRequestStartTime() {
        return requestStartTime;
    }

    public void setRequestStartTime(Timestamp requestStartTime) {
        this.requestStartTime = requestStartTime;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getOriginApp() {
        return originApp;
    }

    public void setOriginApp(String originApp) {
        this.originApp = originApp;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public JsonContent getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(JsonContent jsonContent) {
        this.jsonContent = jsonContent;
    }
}
