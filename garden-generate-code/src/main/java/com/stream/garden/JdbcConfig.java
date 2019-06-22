package com.stream.garden;

/**
 * @author garden
 * @date 2019-06-22 9:09
 */
public class JdbcConfig {

    private String driver;
    private String url;
    private String username;
    private String password;

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
