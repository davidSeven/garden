package com.forest.framework.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

public class ContextServletInputStream extends ServletInputStream {

    ServletInputStream servletInputStream;

    private StringBuilder buffer;

    public ContextServletInputStream(ServletInputStream servletInputStream) {
        this.servletInputStream = servletInputStream;
        this.buffer = new StringBuilder();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }

    @Override
    public int read() throws IOException {
        int data = servletInputStream.read();
        buffer.append((char) data);
        return data;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int data = servletInputStream.read(b);
        if (data > 0) {
            buffer.append(new String(b));
        }
        return data;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int data = servletInputStream.read(b, off, len);
        if (data > 0) {
            buffer.append(new String(b, off, data));
        }
        return data;
    }

    @Override
    public int readLine(byte[] b, int off, int len) throws IOException {
        int data = servletInputStream.readLine(b, off, len);
        if (data > 0) {
            buffer.append(new String(b, off, data));
        }
        return data;
    }

    public String getContent() {
        return buffer.toString();
    }
}
