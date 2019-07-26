package com.stream.garden.framework.web.filter;

import com.github.pagehelper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author garden
 */
public class RequestFilterUtils {

    private static final String METHOD_GET = "GET";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String REQUEST_ACCEPT_CONTENT_TYPE = "application/x-www-form-urlencoded,application/json,text/xml";
    private static final String RESPONSE_ACCEPT_CONTENT_TYPE = "text/xml,text/plain,application/json";
    private static final String SEPARATOR_STR = ",";

    private RequestFilterUtils() {
    }

    /**
     * 判断是否包装过滤请求
     * 当request时只tracer请求体类型为application/x-www-form-urlencoded,application/json,text/xml的请求
     * 当response时只tracer响应体类型为text/xml,text/plain,application/json的响应
     *
     * @param request  request
     * @param response response
     * @return boolean
     */
    public static boolean shouldTracer(HttpServletRequest request, HttpServletResponse response) {
        boolean tracerFlag = false;
        if (null != request) {
            // get不处理
            if (METHOD_GET.equalsIgnoreCase(request.getMethod())) {
                return true;
            }
            String requestContentType = request.getHeader(CONTENT_TYPE);
            // 判断请求头的CONTENT_TYPE是否符合规则
            if (StringUtil.isNotEmpty(requestContentType)) {
                String[] acceptContentTypes = REQUEST_ACCEPT_CONTENT_TYPE.split(SEPARATOR_STR);
                for (String acceptContentType : acceptContentTypes) {
                    if (requestContentType.contains(acceptContentType)) {
                        return true;
                    }
                }
            }
        }

        if (null != response) {
            String responseContentType = response.getContentType();
            // 判断响应头的CONTENT_TYPE是否符合规则
            if (StringUtil.isNotEmpty(responseContentType)) {
                String[] acceptContentTypes = RESPONSE_ACCEPT_CONTENT_TYPE.split(SEPARATOR_STR);
                for (String acceptContentType : acceptContentTypes) {
                    if (responseContentType.contains(acceptContentType)) {
                        return true;
                    }
                }
            }
        }
        return tracerFlag;
    }
}
