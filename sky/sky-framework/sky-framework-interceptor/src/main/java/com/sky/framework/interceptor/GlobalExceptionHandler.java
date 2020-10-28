package com.sky.framework.interceptor;

import com.sky.framework.api.context.RequestContext;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.api.exception.AuthenticationException;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.api.exception.HystrixFeignException;
import feign.RetryableException;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.UnexpectedTypeException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ControllerExceptionHandler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * render
     *
     * @param httpStatus HttpStatus
     * @param exception  Throwable
     */
    protected ResponseDto<Map<String, Object>> render(HttpStatus httpStatus, Throwable exception) {
        while (exception.getClass().equals(InvocationTargetException.class)) {
            exception = ((InvocationTargetException) exception).getTargetException();
        }
        RequestContext context = RequestContext.getCurrentContext();
        int code = 0;
        String message = null;
        String requestId = context.getRequestId();
        if (exception instanceof CommonException) {
            CommonException commonException = (CommonException) exception;
            code = commonException.getCode();
            message = convertMessage(code, commonException.getMessage(), exception.getMessage(), commonException.getValues());
        } else if (exception instanceof HystrixFeignException) {
            HystrixFeignException hystrix = (HystrixFeignException) exception;
            code = hystrix.getCode();
            message = convertMessage(code, hystrix.getMessage(), exception.getMessage(), hystrix.getValues());
        } else if (exception instanceof RetryableException) {
            code = HttpStatus.REQUEST_TIMEOUT.value();
            message = HttpStatus.REQUEST_TIMEOUT.getReasonPhrase();
        } else if (exception instanceof BindException) {
            BindException bindException = (BindException) exception;
            List<ObjectError> list = bindException.getAllErrors();
            if (!CollectionUtils.isEmpty(list)) {
                for (ObjectError oe : list) {
                    message = oe.getDefaultMessage();
                    if (!StringUtils.isEmpty(message)) {
                        break;
                    }
                }
            }
        } else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manv = (MethodArgumentNotValidException) exception;
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = "参数[" + manv.getParameter().getParameterName() + "]校验不通过";
        } else if (exception instanceof IllegalArgumentException) {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = exception.getMessage();
            if (StringUtils.isEmpty(message)) {
                message = "不合法的参数异常";
            }
        } else if (exception instanceof UnexpectedTypeException) {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = "不合法的参数异常";
        } else if (exception instanceof NoSuchMethodException) {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = "该方法不支持";
        } else if (exception instanceof ClassNotFoundException) {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = "找不到类";
        } else if (exception instanceof JSONException) {
            JSONException jsonException = (JSONException) exception;
            message = jsonException.getMessage();
        } else if (exception instanceof DataAccessException) {
            Throwable cause = exception.getCause();
            if (cause instanceof SQLException) {
                SQLException sqlEx = (SQLException) cause;
                code = HttpStatus.INTERNAL_SERVER_ERROR.value();
                String sqlErrMsg = sqlEx.getMessage();
                message = "数据异常:" + sqlErrMsg;
            } else {
                code = HttpStatus.INTERNAL_SERVER_ERROR.value();
                message = "数据异常:" + cause.getMessage();
            }
        }
        if (0 == code) {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        if (StringUtils.isEmpty(message)) {
            message = exception.getMessage();
            if (StringUtils.isEmpty(message)) {
                message = "系统内部错误";
            }
        }
        ResponseDto<Map<String, Object>> result = new ResponseDto<>();
        result.setCode(code);
        result.setMessage(message);
        result.setRequestId(requestId);


        // 记录异常日志
        logger.error(requestId + " Trace Start");
        logger.error("HTTP Status:" + requestId + httpStatus.getReasonPhrase());
        logger.error(code + ":" + message, exception);
        logger.error(requestId + " Trace End");

        return result;
    }

    /**
     * convertMessage
     *
     * @param code           int
     * @param message        String
     * @param defaultMessage String
     * @param args           Object[]
     * @return String
     */
    protected String convertMessage(int code, String message, String defaultMessage, Object[] args) {
        if (StringUtils.isEmpty(message)) {
            message = defaultMessage;
        } else {
            if (message.contains("{}") && args != null && args.length > 0) {
                for (Object arg : args) {
                    if (message.contains("{}")) {
                        message = message.replaceFirst("\\{}", String.valueOf(arg));
                    }
                }
            }
        }
        if (StringUtils.isEmpty(message) && !StringUtils.isEmpty(code)) {
            message = code + ":";
        }
        return message;
    }

    /**
     * Authentication Exception
     *
     * @param cause AuthenticationException
     * @return ResponseDto
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResponseDto<Map<String, Object>> handleAuthenticationException(AuthenticationException cause) {
        return render(HttpStatus.UNAUTHORIZED, cause);
    }

    /**
     * Default Exception
     *
     * @param cause Throwable
     * @return ResponseDto
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseDto<Map<String, Object>> handleException(Throwable cause) {
        return render(HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }

    /**
     * handleHttpMessageNotReadableException
     *
     * @param cause HttpMessageNotReadableException
     * @return ResponseDto
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDto<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException cause) {
        return render(HttpStatus.BAD_REQUEST, cause);
    }

    /**
     * handleHttpRequestMethodNotSupportedException
     *
     * @param cause HttpRequestMethodNotSupportedException
     * @return ResponseDto
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseDto<Map<String, Object>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException cause) {
        return render(HttpStatus.BAD_REQUEST, cause);
    }

    /**
     * handleHttpMediaTypeNotSupportedException
     *
     * @param cause Exception
     * @return ResponseDto
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseDto<Map<String, Object>> handleHttpMediaTypeNotSupportedException(Exception cause) {
        return render(HttpStatus.UNSUPPORTED_MEDIA_TYPE, cause);
    }
}
