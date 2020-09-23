package com.forest.framework.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class ContextHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private ContextServletOutputStream contextServletOutputStream;
    private HttpServletResponse response;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public ContextHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        // 如果响应头的ContentType不符合规则则直接返回
        if (!ContextFilterUtils.shouldTracer(null, response)) {
            return super.getOutputStream();
        }
        // 拦截响应流,获取响应body
        if (null == contextServletOutputStream) {
            contextServletOutputStream = new ContextServletOutputStream(super.getOutputStream());
        }
        return contextServletOutputStream;
    }

    /**
     * @return the traceOutputStream
     */
    public ContextServletOutputStream getTraceOutputStream() {
        return contextServletOutputStream;
    }

}
