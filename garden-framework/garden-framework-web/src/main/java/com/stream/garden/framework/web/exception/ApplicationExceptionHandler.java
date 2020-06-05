package com.stream.garden.framework.web.exception;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author garden
 * @date 2019-06-24 10:22
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Result<String> baseErrorHandler(HttpServletRequest req, Exception e) {
        Result<String> result = new Result<>();
        logger.error("Exception Handler：Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        logger.error("process is error!", e);
        if (BindException.class.isAssignableFrom(e.getClass())) {
            BindException bindException = (BindException) e;
            List<ObjectError> allErrors = bindException.getAllErrors();
            List<String> stringList = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            result.setMsg(stringList.toString());
        } else if (e instanceof UndeclaredThrowableException) {
            Throwable throwable = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
            result.setCode(ExceptionCode.UNKOWN_EXCEPTION.getCode());
            result.setMsg(throwable.getMessage());
        } else if (e instanceof ApplicationException) {
            result = new Result<>(e, ExceptionCode.UNKOWN_EXCEPTION);
        } else {
            result.setCode(ExceptionCode.UNKOWN_EXCEPTION.getCode());
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
