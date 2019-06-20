package com.stream.garden.framework.jdbc.mybatis;

public class InvalidXmlMapperException extends RuntimeException {

    private static final long serialVersionUID = -3846556576046399727L;

    public InvalidXmlMapperException(String message) {
        super(message);
    }

    public InvalidXmlMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
