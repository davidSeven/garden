package com.stream.garden.framework.web.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author garden
 * @date 2019-06-19 17:32
 */
@Component
@ConfigurationProperties(prefix = GlobalConfig.CONFIG_PREFIX)
public class GlobalConfig {

    static final String CONFIG_PREFIX = "garden";

    // @NacosValue(value = "${garden.name}", autoRefreshed = true)
    private String name;
    // @NacosValue(value = "${garden.password}", autoRefreshed = true)
    private String password;

    // @NacosValue(value = "${garden.excludePath}", autoRefreshed = true)
    private List<String> excludePath;

    public static String path;

    public static String uploadPath;

    @Value("${garden.upload-dir}")
    public static String UPLOAD_DIR = "NONE";

    /**
     * 登录路径
     */
    // @NacosValue(value = "${garden.login-path}")
    private String loginPath;

    // @NacosProperty(value = "garden.jwt")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getExcludePath() {
        return excludePath;
    }

    public void setExcludePath(List<String> excludePath) {
        this.excludePath = excludePath;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        GlobalConfig.path = path;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        GlobalConfig.uploadPath = uploadPath;
    }

    public void setUploadDir(String uploadDir) {
        GlobalConfig.UPLOAD_DIR = uploadDir;
    }

    public static class JwtConfig {

        @NacosValue(value = "${garden.jwt.clientId}")
        private String clientId;
        @NacosValue(value = "${garden.jwt.base64Secret}")
        private String base64Secret;
        @NacosValue(value = "${garden.jwt.name}")
        private String name;
        @NacosValue(value = "${garden.jwt.expiresSecond}")
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
