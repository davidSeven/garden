package com.stream.garden.framework.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author garden
 * @date 2019-06-19 17:32
 */
@ConfigurationProperties(prefix = GlobalConfig.CONFIG_PREFIX, ignoreInvalidFields = true, ignoreUnknownFields = false)
public class GlobalConfig {

    public static final String CONFIG_PREFIX = "garden";

    private String name;

    private JwtConfig jwt;

    public JwtConfig getJwt() {
        return jwt;
    }

    public void setJwt(JwtConfig jwt) {
        this.jwt = jwt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class JwtConfig {
        private String clientId;
        private String base64Secret;
        private String name;
        private int expiresSecond;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getBase64Secret() {
            return base64Secret;
        }

        public void setBase64Secret(String base64Secret) {
            this.base64Secret = base64Secret;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getExpiresSecond() {
            return expiresSecond;
        }

        public void setExpiresSecond(int expiresSecond) {
            this.expiresSecond = expiresSecond;
        }
    }
}
