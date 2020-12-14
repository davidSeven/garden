package com.stream.garden.fileinfo;

import com.stream.garden.framework.api.exception.CommonException;
import com.stream.garden.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class DefaultHandlerFile implements HandlerFile {
    protected Logger logger = LoggerFactory.getLogger(DefaultHandlerFile.class);

    /**
     * 文件保存地址
     */
    private String filePath;

    /**
     * 文件字节流
     */
    private byte[] bytes;

    DefaultHandlerFile(String filePath, byte[] bytes) {
        this.filePath = filePath;
        this.bytes = bytes;
    }

    @Override
    public void write() {
        BufferedOutputStream stream = null;
        long startTime = 0L;
        if (logger.isDebugEnabled()) {
            startTime = System.currentTimeMillis();
        }
        try {
            stream = new BufferedOutputStream(new FileOutputStream(new java.io.File(filePath)));
            stream.write(bytes);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CommonException("999", "保存文件异常");
        } finally {
            FileUtil.close(stream);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("保存文件耗时：{}", (System.currentTimeMillis() - startTime));
        }
    }

    @Override
    public long getSize() {
        return this.bytes.length;
    }

    @Override
    public void clear() {
        this.bytes = null;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
