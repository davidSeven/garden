package com.sky.system.api.dto;

import java.io.Serializable;

/**
 * @date 2020-12-15 015 13:34
 */
public class VerificationDto implements Serializable {

    // 授权token
    private String authorization;

    // 访问ip
    private String ip;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
