package com.stream.forest.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    /**
     * the serialVersionUID
     */
    private static final long serialVersionUID = 1381325479896057076L;

    private String code;
    private String message;
    private Throwable throwable;
    private transient Object[] values;

}
