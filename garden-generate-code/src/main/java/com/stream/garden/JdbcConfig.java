package com.stream.garden;

import com.stream.garden.util.PropertiesUtil;

import java.util.Properties;

/**
 * @author garden
 * @date 2019-06-22 9:09
 */
public class JdbcConfig {

    private String driver;
    private String url;
    private String username;
    private String password;

    public static JdbcConfig readProperties(String path) {
        JdbcConfig jdbcConfig = null;
        try {
            Properties properties = PropertiesUtil.readProperties(path);
            jdbcConfig = new JdbcConfig();
            jdbcConfig.setDriver(properties.getProperty("jdbc.driver"));
            jdbcConfig.setUrl(properties.getProperty("jdbc.url"));
            jdbcConfig.setUsername(properties.getProperty("jdbc.username"));
            jdbcConfig.setPassword(properties.getProperty("jdbc.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jdbcConfig;
    }

    public String getDriver() {
        return driver;
    }

    public JdbcConfig setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public JdbcConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public JdbcConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JdbcConfig setPassword(String password) {
        this.password = password;
        return this;
    }
}
