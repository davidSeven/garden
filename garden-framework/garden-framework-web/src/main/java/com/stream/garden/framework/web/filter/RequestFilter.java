package com.stream.garden.framework.web.filter;

import com.github.pagehelper.util.StringUtil;
import com.stream.garden.framework.web.config.GlobalConfig;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author garden
 * @date 2019-07-26 13:45
 */
@WebFilter(urlPatterns = RequestFilter.URL_PATTERNS, filterName = RequestFilter.FILTER_NAME)
public class RequestFilter extends StaticExcludeFilter implements Filter {
    public static final String URL_PATTERNS = "/*";
    public static final String FILTER_NAME = "requestFilter";
    private static final int CONTENT_LENGTH = 40960;
    private static final String METHOD_GET = "GET";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GlobalConfig globalConfig;

    public RequestFilter(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init:{}", filterConfig.getFilterName());
        try {
            super.init(globalConfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServletException(e);
        }
    }

    public String getBodyString(final ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String bodyString = getBodyString(servletRequest);

        // byte[] bytes1 = IOUtils.toByteArray(servletRequest.getInputStream());
/*
        RequestFacade requestFacade = (RequestFacade) servletRequest;
        BufferedReader bufferedReader = requestFacade.getReader();
        char[] chars = new char[1024];
        bufferedReader.read(chars, 0, 1024);*/


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        boolean jump = super.exclude(uri);
        if (!jump) {
            String method = request.getMethod();
            long startTime = System.currentTimeMillis();
            ContextHttpServletRequestWrapper requestWrapper = new ContextHttpServletRequestWrapper(request);
            ContextHttpServletResponseWrapper responseWrapper = new ContextHttpServletResponseWrapper(response);

            filterChain.doFilter(requestWrapper, responseWrapper);

            if (logger.isDebugEnabled()) {
                String requestBody = requestParam(requestWrapper);
                String responseBody = responseParam(responseWrapper);

                StringBuilder builder = new StringBuilder();
                if (StringUtil.isEmpty(requestBody)) {
                    requestBody = "{}";
                }
                if (StringUtil.isEmpty(responseBody)) {
                    responseBody = "{}";
                }
                builder.append("{\"requestBody\":" + requestBody + ",");
                builder.append("\"responseBody\":" + responseBody + "}");
                logger.debug(builder.toString());
            }

            long endTime = System.currentTimeMillis();
            logger.info(">>>请求路径：{}[{}]，开始时间：{}，结束时间：{}，总耗时：{}",
                    uri, method, startTime, endTime, (endTime - startTime));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        logger.info("destroy");
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
                if (contextServletInputStream != null) {
                    body = new String(contextServletInputStream.getContent().getBytes(), StandardCharsets.UTF_8);
                }
            }
        }
        return body;
    }

    private String responseParam(ContextHttpServletResponseWrapper responseWrapper) throws IOException {
        ContextServletOutputStream traceOutputStream = responseWrapper.getContextServletOutputStream();
        if (null != traceOutputStream && StringUtil.isNotEmpty(traceOutputStream.getContent())) {
            return new String(traceOutputStream.getContent().getBytes(), StandardCharsets.UTF_8);
        }
        return null;
    }

}
