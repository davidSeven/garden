package com.stream.garden.file.config;

import com.stream.garden.file.constants.StorageConfig;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @author garden
 * @date 2019-11-13 14:46
 */
@Data
@Component
@ConfigurationProperties(prefix = FileConfig.CONFIG_PREFIX)
public class FileConfig {
    private Logger logger = LoggerFactory.getLogger(FileConfig.class);
    static final String CONFIG_PREFIX = "garden.file";

    /**
     * 存储类型，配置项里面未配置的默认类型
     */
    private StorageConfig storageConfig = StorageConfig.FILE;

    /**
     * 文件存储路径
     */
    private String uploadPath;

    /**
     * fastDfs配置
     */
    @NestedConfigurationProperty
    private FastDfsConfig fastDfsConfig;
}