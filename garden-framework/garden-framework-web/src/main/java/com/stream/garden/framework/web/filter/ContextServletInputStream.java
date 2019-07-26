package com.stream.garden.framework.web.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author garden
 */
public class ContextServletInputStream extends ServletInputStream {

    private ServletInputStream servletInputStream;

    private StringBuilder buffer;

    public ContextServletInputStream(ServletInputStream servletInputStream) {
        this.servletInputStream = servletInputStream;
        this.buffer = new StringBuilder();

        try {
            byte[] readBytes = readBytes(servletInputStream);
            System.out.println(readBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static byte[] readBytes(InputStream in) throws IOException {
        BufferedInputStream bufin = new BufferedInputStream(in);
        int buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);

        byte[] temp = new byte[buffSize];
        int size = 0;
        while ((size = bufin.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        bufin.close();

        return out.toByteArray();
    }
}
