package com.stream.garden.framework.web.permission;

/**
 * @author garden
 * @date 2019-09-28 15:58
 */
public class Permission {

    private String url;
    private String code;

    public Permission() {
    }

    public Permission(String url, String code) {
        this.url = url;
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
