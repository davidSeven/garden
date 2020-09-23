package com.forest.framework.filter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 实现Servlet的Filter
 */
@Component
public class ContextFilter implements Filter, Ordered {
    public static final int ContentLength = 40960;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String METHOD_GET = "GET";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("ContextFilter.init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("ContextFilter.doFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        long startTime = System.currentTimeMillis();

        ContextHttpServletRequestWrapper requestWrapper = new ContextHttpServletRequestWrapper(request);
        ContextHttpServletResponseWrapper responseWrapper = new ContextHttpServletResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        if (logger.isDebugEnabled()) {

            String requestBody = requestParam(requestWrapper),
                    responseBody = responseParam(responseWrapper);

            StringBuilder builder = new StringBuilder();
            if (StringUtils.isEmpty(requestBody)) {
                requestBody = "{}";
            } else {
                requestBody = requestBody.replaceAll("\t", "")
                        .replaceAll("\n", "")
                        .replaceAll("\r", "");
            }

            if (StringUtils.isEmpty(responseBody)) {
                responseBody = "{}";
            }
            builder.append("{\"requestBody\":").append(requestBody).append(",");
            builder.append("\"responseBody\":").append(responseBody).append("}");

            logger.debug(builder.toString());
        }

        long endTime = System.currentTimeMillis();
        logger.info(">>>请求路径：{}，开始时间：{}，结束时间：{}，总耗时：{}", uri, startTime, endTime, (endTime - startTime));
    }

    private String requestParam(ContextHttpServletRequestWrapper requestWrapper) throws UnsupportedEncodingException {
        String body = "";
        String method = requestWrapper.getMethod();
        // 处理GET请求参数
        if (method.equalsIgnoreCase(METHOD_GET)) {
            body = requestWrapper.getQueryString();
            if (StringUtils.isNotEmpty(body) && Base64.isBase64(body)) {
                body = new String(Base64.decodeBase64(body), StandardCharsets.UTF_8);
            }
        } else {
            // 处理POST请求参数
            int size = requestWrapper.getContentLength();
            if (size < ContentLength) {
                ContextServletInputStream traceInputStream = requestWrapper.getContextServletInputStream();
                if (traceInputStream != null) {
                    body = new String(traceInputStream.getContent().getBytes(), StandardCharsets.UTF_8);
                }
            }
        }
        return body;
    }

    private String responseParam(ContextHttpServletResponseWrapper responseWrapper) throws UnsupportedEncodingException {
        ContextServletOutputStream traceOutputStream = responseWrapper.getTraceOutputStream();
        if (null != traceOutputStream && StringUtils.isNotEmpty(traceOutputStream.getContent())) {
            return new String(traceOutputStream.getContent().getBytes(), StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override
    public void destroy() {
        logger.info("ContextFilter.destroy");
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
