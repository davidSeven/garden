package com.stream.garden.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = FileConfig.CONFIG_PREFIX)
public class FileConfig {

    static final String CONFIG_PREFIX = "file";

    /*----------------------------存储配置-----------------------------*/
    /**
     * 存储配置，默认文件存储
     */
    private StorageConfig storageConfig = StorageConfig.FILE;
    /*----------------------------存储配置-----------------------------*/

    /*----------------------------上传配置-----------------------------*/
    /**
     * 上传文件的key
     * 默认file
     */
    private String uploadFileKey = "file";
    /**
     * 图片上传地址
     */
    private String attachImagesFilePath;
    /**
     * 排除某类文件上传
     */
    private Set<String> excludes;
    /*----------------------------上传配置-----------------------------*/

    /*----------------------------压缩--------------------------------*/
    /**
     * 上传图片压缩
     * 默认false
     */
    private boolean compressionEnabled;
    /**
     * 上传图片超过多少就压缩
     * 默认1M(1024*1024=1048576)
     */
    private long compressionMinSize = 1048576;
    /**
     * 压缩比例
     * 默认1
     */
    private float compressionScale = 1f;
    /**
     * 上传图片压缩质量
     * 默认0.3
     */
    private float compressionQuality = 0.3f;
    /*----------------------------压缩--------------------------------*/
}
