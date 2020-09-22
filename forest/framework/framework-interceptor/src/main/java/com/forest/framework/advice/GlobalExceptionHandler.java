package com.forest.framework.advice;

import com.alibaba.fastjson.JSONException;
import com.forest.framework.common.constant.CommonConstant;
import com.forest.framework.common.context.DebugDataBean;
import com.forest.framework.common.context.RequestContext;
import com.forest.framework.common.exception.AuthenticationException;
import com.forest.framework.common.exception.BaseException;
import com.forest.framework.common.exception.HystrixFeignException;
import com.forest.framework.common.util.LogUtil;
import com.forest.framework.dto.ResponseDto;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ControllerExceptionHandler
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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
        String code = null;
        String message = null;
        String requestId = context.getRequestId();
        if (exception instanceof BaseException) {
            BaseException base = (BaseException) exception;
            code = base.getCode();
            message = convertMessage(code, base.getMessage(), exception.getMessage(), base.getValues());
        } else if (exception instanceof HystrixFeignException) {
            HystrixFeignException hystrix = (HystrixFeignException) exception;
            code = hystrix.getCode();
            message = convertMessage(code, hystrix.getMessage(), exception.getMessage(), hystrix.getValues());
        } else if (exception instanceof RetryableException) {
            code = CommonConstant.REQUEST_TIMEOUT_CODE;
            message = CommonConstant.REQUEST_TIMEOUT_MSG;
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
            code = CommonConstant.METHOD_ARGUMENT_NOT_VALID_CODE;
            message = "参数[" + manv.getParameter().getParameterName() + "]校验不通过";
        } else if (exception instanceof IllegalArgumentException) {
            code = CommonConstant.METHOD_ARGUMENT_NOT_VALID_CODE;
            message = exception.getMessage();
            if (StringUtils.isEmpty(message)) {
                message = CommonConstant.METHOD_ARGUMENT_NOT_VALID_MSG;
            }
        } else if (exception instanceof UnexpectedTypeException) {
            code = CommonConstant.METHOD_ARGUMENT_NOT_VALID_CODE;
            message = CommonConstant.METHOD_ARGUMENT_NOT_VALID_MSG;
        } else if (exception instanceof NoSuchMethodException) {
            code = CommonConstant.INFO_NOT_SUPPORT_CODE;
            message = CommonConstant.INFO_NOT_SUPPORT_MSG;
        } else if (exception instanceof ClassNotFoundException) {
            code = CommonConstant.CANNOT_FIND_CLASS_CODE;
            message = CommonConstant.CANNOT_FIND_CLASS_MSG;
        } else if (exception instanceof JSONException) {
            JSONException jsonException = (JSONException) exception;
            message = jsonException.getMessage();
        } else if (exception instanceof DataAccessException) {
            Throwable cause = exception.getCause();
            if (cause instanceof SQLException) {
                SQLException sqlEx = (SQLException) cause;
                code = CommonConstant.DATA_ERROR_CODE;
                String sqlErrMsg = sqlEx.getMessage();
                message = CommonConstant.DATA_ERROR_MSG + ":" + sqlErrMsg;
            } else {
                code = CommonConstant.DATA_ERROR_CODE;
                message = CommonConstant.DATA_ERROR_MSG + ":" + cause.getMessage();
            }
        }

        if (StringUtils.isEmpty(code)) {
            code = CommonConstant.DEFAULT_ERROR_CODE;
        }
        if (StringUtils.isEmpty(message)) {
            message = exception.getMessage();
            if (StringUtils.isEmpty(message)) {
                message = CommonConstant.DEFAULT_ERROR_MSG;
            }
        }
        ResponseDto<Map<String, Object>> result = new ResponseDto<>();
        result.setCode(code);
        result.setMessage(message);
        result.setRequestId(requestId);
        result.setOddNumber(context.getOddNumber());

        DebugDataBean debugData = new DebugDataBean();
        debugData.setEventPage(context.getEventPage());
        debugData.setEventComp(context.getEventComponent());
        debugData.setLoginUserId(context.getLoginUserId());
        debugData.setLoginAddrCode(context.getLoginAddrCode());
        debugData.setLoginAppType(context.getLoginAppType());
        debugData.setClientIpAddress(context.getClientIpAddress());
        debugData.setOriApp(context.getOriginApp());
        debugData.setApiCallStacks(context.getApiCallStacks());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        debugData.setExceptionStackTrace(sw.toString());

        // 返回debugData给客户端
        result.setDebugData(debugData);
        // 记录异常日志
        log.error(requestId + " Trace Start");
        log.error("HTTP Status:" + requestId + httpStatus.getReasonPhrase());
        log.error(code + ":" + message, exception);
        log.error(LogUtil.objToJSONString(debugData, 1000));
        log.error(requestId + " Trace End");

        return result;
    }

    /**
     * convertMessage
     *
     * @param code           String
     * @param message        String
     * @param defaultMessage String
     * @param args           Object[]
     * @return String
     */
    protected String convertMessage(String code, String message, String defaultMessage, Object[] args) {
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
