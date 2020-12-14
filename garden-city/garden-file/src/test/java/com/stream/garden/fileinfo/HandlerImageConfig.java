package com.stream.garden.fileinfo;

import com.stream.garden.config.FileConfig;
import com.stream.garden.config.StorageConfig;
import com.stream.garden.dto.FileParameterDto;

import javax.servlet.http.HttpServletRequest;

public class HandlerImageConfig {

    public static final String COMPRESSION_ENABLED = "compressionEnabled";

    /**
     * 存储配置
     */
    private StorageConfig storageConfig;
    /**
     * 是否压缩
     */
    private boolean compressionEnabled;
    /**
     * 超过多少开始压缩
     */
    private long compressionMinSize;
    /**
     * 压缩比例
     */
    private float compressionScale;
    /**
     * 压缩质量
     */
    private float compressionQuality;

    /**
     * 默认配置
     *
     * @param fileConfig 上传配置
     * @return HandlerImageConfig
     */
    public static HandlerImageConfig defaultHandlerImageConfig(FileConfig fileConfig) {
        HandlerImageConfig config = new HandlerImageConfig();
        config.setStorageConfig(fileConfig.getStorageConfig());
        config.setCompressionEnabled(fileConfig.isCompressionEnabled());
        config.setCompressionMinSize(fileConfig.getCompressionMinSize());
        config.setCompressionScale(fileConfig.getCompressionScale());
        config.setCompressionQuality(fileConfig.getCompressionQuality());
        return config;
    }

    /**
     * 自定义配置
     *
     * @param fileConfig 系统上传配置
     * @param request    request
     * @return HandlerImageConfig
     */
    public static HandlerImageConfig requestHandlerImageConfig(FileConfig fileConfig, HttpServletRequest request) {
        HandlerImageConfig handlerImageConfig = defaultHandlerImageConfig(fileConfig);
        if (null != request.getParameter(COMPRESSION_ENABLED)) {
            handlerImageConfig.setCompressionEnabled(valueOfBoolean(request.getParameter(COMPRESSION_ENABLED)));
        } else if (null != request.getHeader(COMPRESSION_ENABLED)) {
            handlerImageConfig.setCompressionEnabled(valueOfBoolean(request.getHeader(COMPRESSION_ENABLED)));
        }
        return handlerImageConfig;
    }

    /**
     * 自定义配置
     *
     * @param fileConfig    系统上传配置
     * @param fileParameter 自定义配置 - 是否压缩，是否添加水印
     * @return HandlerImageConfig
     */
    public static HandlerImageConfig apiHandlerImageConfig(FileConfig fileConfig, FileParameterDto fileParameter) {
        HandlerImageConfig handlerImageConfig = defaultHandlerImageConfig(fileConfig);
        handlerImageConfig.setCompressionEnabled(fileParameter.isCompressionEnabled());
        return handlerImageConfig;
    }

    private static boolean valueOfBoolean(String value) {
        return Boolean.TRUE.toString().equals(value);
    }

    public StorageConfig getStorageConfig() {
        return storageConfig;
    }

    public void setStorageConfig(StorageConfig storageConfig) {
        this.storageConfig = storageConfig;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    public void setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }

    public long getCompressionMinSize() {
        return compressionMinSize;
    }

    public void setCompressionMinSize(long compressionMinSize) {
        this.compressionMinSize = compressionMinSize;
    }

    public float getCompressionScale() {
        return compressionScale;
    }

    public void setCompressionScale(float compressionScale) {
        this.compressionScale = compressionScale;
    }

    public float getCompressionQuality() {
        return compressionQuality;
    }

    public void setCompressionQuality(float compressionQuality) {
        this.compressionQuality = compressionQuality;
    }
}
