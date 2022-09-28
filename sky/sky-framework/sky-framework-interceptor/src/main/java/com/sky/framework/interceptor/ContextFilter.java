package com.sky.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2020-11-06 006 9:04
 */
public class ContextFilter implements Filter {
    private static final int CONTENT_LENGTH = 40960;
    private static final String METHOD_GET = "GET";
    private final Logger logger = LoggerFactory.getLogger(ContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String method = request.getMethod();
        long startTime = System.currentTimeMillis();

        ContextHttpServletRequestWrapper requestWrapper = new ContextHttpServletRequestWrapper(request);
        ContextHttpServletResponseWrapper responseWrapper = new ContextHttpServletResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        if (logger.isInfoEnabled()) {
            String requestBody = requestParam(requestWrapper);
            String responseBody = responseParam(responseWrapper);

            StringBuilder builder = new StringBuilder();
            if (StringUtil.isEmpty(requestBody)) {
                requestBody = "{}";
            } else {
                requestBody = requestBody.replaceAll("\t", "")
                        .replaceAll(" ", "")
                        .replaceAll("\n", "")
                        .replaceAll("\r", "");
            }
            if (StringUtil.isEmpty(responseBody)) {
                responseBody = "{}";
            } else {
                responseBody = responseBody.replaceAll("\t", "")
                        .replaceAll(" ", "")
                        .replaceAll("\n", "")
                        .replaceAll("\r", "");
            }

            String requestHeader = requestHeader(requestWrapper);
            String responseHeader = responseHeader(responseWrapper);

            builder.append("{");
            // builder.append("\"requestHeader\":").append(requestHeader).append(",");
            builder.append("\"requestBody\":").append(requestBody).append(",");
            // builder.append("\"responseHeader\":").append(responseHeader).append(",");
            builder.append("\"responseBody\":").append(responseBody);
            builder.append("}");
            logger.info(builder.toString());
        }

        long endTime = System.currentTimeMillis();
        long times = (endTime - startTime);
        if (logger.isInfoEnabled()) {
            logger.info(">>>请求路径：{}[{}]，开始时间：{}，结束时间：{}，总耗时：{}", uri, method, startTime, endTime, times);
        }
    }

    private String requestHeader(ContextHttpServletRequestWrapper requestWrapper) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = requestWrapper.getHeaderNames();
        if (null != headerNames) {
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                headerMap.put(key, requestWrapper.getHeader(key));
            }
        }
        return JSON.toJSONString(headerMap);
    }

    private String requestParam(ContextHttpServletRequestWrapper requestWrapper) {
        String body = "";
        String method = requestWrapper.getMethod();
        // 处理GET请求参数
        if (method.equalsIgnoreCase(METHOD_GET)) {
            body = requestWrapper.getQueryString();
            if (StringUtil.isNotEmpty(body) && Base64.isBase64(body)) {
                body = new String(Base64.decodeBase64(body), StandardCharsets.UTF_8);
            }
        } else {
            // 处理POST请求参数
            int size = requestWrapper.getContentLength();
            if (size < CONTENT_LENGTH) {
                ContextServletInputStream contextServletInputStream = requestWrapper.getContextServletInputStream();
                // 1.这里无法读取到流的信息，就直接获取request中的参数就行了
                // 2.虽然修改了Filter的顺序，或者是重写HiddenHttpMethodFilter，也无法读取到流
                if (contextServletInputStream != null) {
                    body = new String(contextServletInputStream.getContent().getBytes(), StandardCharsets.UTF_8);
                } else {
                    Map<String, Object> objectMap = WebUtils.getParametersStartingWith(requestWrapper, null);
                    body = JSON.toJSONString(objectMap);
                }
            }
        }
        return body;
    }

    private String responseHeader(ContextHttpServletResponseWrapper responseWrapper) {
        Map<String, String> headerMap = new HashMap<>();
        Collection<String> headerNames = responseWrapper.getHeaderNames();
        if (null != headerNames) {
            for (String headerName : headerNames) {
                headerMap.put(headerName, responseWrapper.getHeader(headerName));
            }
        }
        return JSON.toJSONString(headerMap);
    }

    private String responseParam(ContextHttpServletResponseWrapper responseWrapper) {
        ContextServletOutputStream traceOutputStream = responseWrapper.getContextServletOutputStream();
        if (null != traceOutputStream && StringUtil.isNotEmpty(traceOutputStream.getContent())) {
            return new String(traceOutputStream.getContent().getBytes(), StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("ContextFilter.init");
    }

    @Override
    public void destroy() {
        logger.info("ContextFilter.destroy");
    }
}
