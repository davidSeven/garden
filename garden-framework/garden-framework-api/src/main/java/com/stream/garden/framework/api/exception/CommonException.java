package com.stream.garden.framework.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author garden
 * @date 2020-06-05 15:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -1411235866001927995L;

    private String code;
    private String message;
    private Throwable throwable;

    public CommonException(String message) {
        this.message = message;
    }

    public CommonException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
