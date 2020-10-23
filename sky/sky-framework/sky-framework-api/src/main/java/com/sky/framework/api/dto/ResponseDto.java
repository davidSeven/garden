package com.sky.framework.api.dto;

import com.sky.framework.api.enums.AppCode;
import com.sky.framework.api.enums.ExceptionCode;

import java.io.Serializable;

/**
 * 服务端返回数据对象的结构化封装<br>
 *
 * @param <T>
 */
public class ResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 2992769554516491987L;
    /**
     * 消息代码
     */
    private int code;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 业务数据对象
     */
    private T data;

    /**
     * 构造器
     */
    public ResponseDto() {
    }

    /**
     * 构造器
     *
     * @param appCode 返回码
     */
    public ResponseDto(AppCode appCode) {
        this.code = appCode.getCode();
        this.message = appCode.getMessage();
    }

    /**
     * 构造器
     *
     * @param code    返回码
     * @param message 消息
     * @param data    数据对象
     */
    public ResponseDto(int code, String message, T data) {
        this(code, message, null, data);
    }

    /**
     * 构造器
     *
     * @param appCode 返回码
     * @param data    数据对象
     */
    public ResponseDto(AppCode appCode, T data) {
        this(appCode);
        this.data = data;
    }

    /**
     * ResponseDto
     *
     * @param code      返回码
     * @param message   消息
     * @param requestId 请求ID
     * @param data      数据对象
     */
    protected ResponseDto(int code, String message, String requestId, T data) {
        this.code = code;
        this.message = message;
        this.requestId = requestId;
        this.data = data;
    }

    /**
     * 静态创建方法
     *
     * @param data T
     * @return ResponseDto
     */
    public static <T> ResponseDto<T> getSuccessResponseDto(T data) {
        return new ResponseDto<>(ExceptionCode.SUCCESS, data);
    }

    /**
     * 返回data
     *
     * @param responseDto responseDto
     * @param <T>         T
     * @return T
     */
    public static <T> T getData(ResponseDto<T> responseDto) {
        if (null != responseDto) {
            return responseDto.getData();
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getData() {
        return data;
    }

    public ResponseDto<T> setData(T data) {
        this.data = data;
        return this;
    }
}
