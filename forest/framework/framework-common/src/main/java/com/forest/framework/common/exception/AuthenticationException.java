package com.forest.framework.common.exception;

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
    public AuthenticationException(String code) {
        super(code, null, null, null);
    }

    /**
     * AuthenticationException
     *
     * @param code    String
     * @param message String
     */
    public AuthenticationException(String code, String message) {
        super(code, message, null, null);
    }

    /**
     * AuthenticationException
     *
     * @param code    String
     * @param message String
     * @param values  Object[]
     */
    public AuthenticationException(String code, String message, Object[] values) {
        super(code, message, null, values);
    }

    /**
     * AuthenticationException
     *
     * @param code      String
     * @param message   String
     * @param throwable Throwable
     * @param values    Object[]
     */
    public AuthenticationException(String code, String message, Throwable throwable, Object[] values) {
        super(code, message, throwable, values);
    }

}
