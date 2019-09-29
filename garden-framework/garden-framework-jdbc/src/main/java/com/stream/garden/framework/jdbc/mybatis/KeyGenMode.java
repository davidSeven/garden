package com.stream.garden.framework.jdbc.mybatis;

import org.apache.commons.lang3.StringUtils;

public enum KeyGenMode {

    /** 用户自定义 */
    CUSTOM("CUSTOM"),
    /** 数据库ID自增 */
    IDENTITY("IDENTITY"),
    /** UUID */
    UUID("UUID"),
    /** UUID */
    DB_UUID("DB_UUID"),
    /** MYCAT 序列自增 */
    MYCAT("MYCAT"),
    /** SNOW雪花算法 */
    SNOW("SNOW"),

    ;

    private String code;
    private String value;

    private KeyGenMode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return code;
    }

    public static KeyGenMode parse(String code) {
        KeyGenMode result = null;
        code = StringUtils.trim(code);

        for (KeyGenMode keyGenMode : values()) {
            if (keyGenMode.getCode().equals(code)) {
                result = keyGenMode;
            }
        }

        if (result == null) {
            result = KeyGenMode.IDENTITY;
        }

        return result;
    }
}
