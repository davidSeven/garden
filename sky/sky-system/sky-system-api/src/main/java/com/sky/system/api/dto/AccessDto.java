package com.sky.system.api.dto;

import java.io.Serializable;

/**
 * @date 2020-12-15 015 13:50
 */
public class AccessDto implements Serializable {

    // 授权token
    private String authorization;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
