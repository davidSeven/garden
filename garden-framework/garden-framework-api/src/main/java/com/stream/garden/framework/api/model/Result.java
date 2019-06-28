package com.stream.garden.framework.api.model;

import com.stream.garden.framework.api.exception.AppCode;
import com.stream.garden.framework.api.exception.ExceptionCode;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author garden
 * @param <T>
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1311214270787771061L;

    private int code;
    private String msg;
    private T data;
    private String traceId;

    public Result() {
        this(ExceptionCode.UNKOWN_EXCEPTION);
    }

    public Result(AppCode appCode) {
        setAppCode(appCode);
    }

    public Result(Exception exception) {

    }

    public void setAppCode(AppCode appCode) {
        this.code = appCode.getCode();
        this.msg = appCode.getMessage();
    }

    public Result<T> ok() {
        setAppCode(ExceptionCode.SUCCESS);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public boolean isSuccess() {
        return this.code == ExceptionCode.SUCCESS.getCode();
    }
}
