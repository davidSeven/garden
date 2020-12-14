package com.stream.garden.fileinfo;

import com.stream.garden.config.StorageConfig;
import com.stream.garden.util.FileUtil;

public class HandlerFileFactory {
    /**
     * 存储配置
     */
    private StorageConfig storageConfig;
    /**
     * 文件保存地址
     */
    private String saveFilePath;
    /**
     * 文件后缀
     */
    private String extendName;
    /**
     * 文件字节流
     */
    private byte[] bytes;
    /**
     * 图片配置
     */
    private HandlerImageConfig config;

    /**
     * 处理文件工厂
     *
     * @param saveFilePath 文件存储路径
     * @param bytes        文件大小
     * @param extendName   文件扩展名
     * @param config       处理文件的配置
     */
    public HandlerFileFactory(String saveFilePath, byte[] bytes, String extendName, HandlerImageConfig config) {
        this.storageConfig = config.getStorageConfig();
        this.saveFilePath = saveFilePath;
        this.bytes = bytes;
        this.extendName = extendName;
        this.config = config;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public HandlerImageConfig getConfig() {
        return config;
    }

    public void setConfig(HandlerImageConfig config) {
        this.config = config;
    }

    public HandlerFile builder() {
        // 判断存储类型
        if (StorageConfig.FILE.equals(storageConfig)) {
            // 判断是否未图片，并且要压缩或者添加水印
            if (isImage() && hasHandlerImage()) {
                return new DefaultHandlerImage(saveFilePath, bytes, config, extendName);
            }
            return new DefaultHandlerFile(saveFilePath, bytes);
        }
        // TODO 正在实现中
        return null;
    }

    private boolean hasHandlerImage() {
        return (null != config && (config.isCompressionEnabled()));
    }

    private boolean isImage() {
        return FileUtil.isImage(extendName);
    }
}
