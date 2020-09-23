package com.forest.framework.filter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class ContextHttpServletRequestWrapper extends HttpServletRequestWrapper {

    ContextServletInputStream contextServletInputStream;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public ContextHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (null == contextServletInputStream) {
            contextServletInputStream = new ContextServletInputStream(super.getInputStream());
        }
        return contextServletInputStream;
    }

    public ContextServletInputStream getContextServletInputStream() {
        return contextServletInputStream;
    }
}
