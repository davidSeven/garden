package com.sky.framework.api.exception;

/**
 * AuthenticationException
 */
@SuppressWarnings("unused")
public class AuthenticationException extends CommonException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * AuthenticationException
     *
     * @param code String
     */
    public AuthenticationException(int code) {
        super(code, null);
    }

    /**
     * AuthenticationException
     *
     * @param code    int
     * @param message String
     */
    public AuthenticationException(int code, String message) {
        super(code, message);
    }

}
