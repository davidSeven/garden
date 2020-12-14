package com.stream.garden.fileinfo;

import com.stream.garden.framework.api.exception.CommonException;
import com.stream.garden.util.FileUtil;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 压缩图片
 */
public class DefaultHandlerImage extends DefaultHandlerFile implements HandlerFile {

    private final HandlerImageConfig config;

    private final String outputFormat;

    DefaultHandlerImage(String filePath, byte[] bytes, HandlerImageConfig config, String outputFormat) {
        super(filePath, bytes);
        this.config = config;
        this.outputFormat = outputFormat;
    }

    @Override
    public void write() {
        long startTime = 0L;
        if (logger.isDebugEnabled()) {
            startTime = System.currentTimeMillis();
        }
        if (config.isCompressionEnabled()) {
            builderBufferedImage(builderCompression(Thumbnails.of(getBufferedImage())));
        }
        if (logger.isDebugEnabled()) {
            logger.debug("处理图片耗时：{}", (System.currentTimeMillis() - startTime));
        }
        super.write();
    }

    private BufferedImage getBufferedImage() {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(super.getBytes());
            return ImageIO.read(byteArrayInputStream);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CommonException("999", "压缩图片，加载原文件流异常");
        } finally {
            FileUtil.close(byteArrayInputStream);
        }
    }

    private Thumbnails.Builder<BufferedImage> builderCompression(Thumbnails.Builder<BufferedImage> fileBuilder) {
        try {
            if (super.getSize() > config.getCompressionMinSize()) {
                // scale是可以指定图片的大小，值在0到1之间，1f就是原图大小，0.5就是原图的一半大小，这里的大小是指图片的长宽。
                // outputQuality是图片的质量，值在0到1，越接近于1质量越好，越接近于0质量越差。
                return fileBuilder.scale(config.getCompressionScale()).outputQuality(config.getCompressionQuality());
            }
            return fileBuilder.scale(config.getCompressionScale());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CommonException("999", "压缩图片，压缩图片异常");
        }
    }

    private void builderBufferedImage(Thumbnails.Builder<BufferedImage> fileBuilder) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            fileBuilder.outputFormat(outputFormat).toOutputStream(byteArrayOutputStream);
            super.setBytes(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new CommonException("999", "压缩图片，写入文件流异常");
        } finally {
            FileUtil.close(byteArrayOutputStream);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
