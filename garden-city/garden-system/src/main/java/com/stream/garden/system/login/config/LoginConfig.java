package com.stream.garden.system.login.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author garden
 * @date 2019-11-11 18:57
 */
@Data
@Component
@ConfigurationProperties(prefix = LoginConfig.CONFIG_PREFIX)
public class LoginConfig {
    private Logger logger = LoggerFactory.getLogger(LoginConfig.class);
    static final String CONFIG_PREFIX = "garden.login";

    /**
     * 安全验证类型
     * none     不需要验证
     * normal   正常验证，默认
     * need     全部需要验证
     */
    private String safetyType = "normal";

    /**
     * 登录失败超过设定次数，需要验证码
     */
    private boolean safetyFailEnabled = true;

    /**
     * 登录失败3次需要验证码
     */
    private int safetyFailCount = 3;

    /**
     * 登录失败5次锁定账户
     */
    private int lockFailCount = 5;
}
