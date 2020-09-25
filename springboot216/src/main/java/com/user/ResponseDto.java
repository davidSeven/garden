package com.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 服务端返回数据对象的结构化封装<br>
 *
 * @param <T>
 */
@Getter
@Setter
public class ResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 2992769554516491987L;
    /**
     * 消息代码
     */
    private String code;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 请求单据编号
     */
    private String oddNumber;
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
     * @param code    返回码
     * @param message 消息
     * @param data    数据对象
     */
    public ResponseDto(String code, String message, T data) {
        this(code, message, "", null);
    }

    /**
     * ResponseDto
     *
     * @param code      返回码
     * @param message   消息
     * @param requestId 请求ID
     * @param data      数据对象
     */
    protected ResponseDto(String code, String message, String requestId, T data) {
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
        return new ResponseDto<>("0", "", "", null);
    }
}
