package com.stream.garden.file.config;

import lombok.Data;

/**
 * @author garden
 * @date 2019-11-13 14:49
 */
@Data
public class FastDfsConfig {

    /**
     * 是否启用fastDfs，默认不启用
     */
    private boolean enabled = false;

    /**
     * 连接超时时间
     */
    private int connectTimeout = 2;

    private int networkTimeout = 30;

    private String charSet = "UTF-8";

    private int httpTrackerHttpPort = 8080;

    private String httpAntiStealToken = "no";

    private String httpSecretKey;

    private String trackerServer;
}
