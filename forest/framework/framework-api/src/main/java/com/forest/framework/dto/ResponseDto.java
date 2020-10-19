package com.forest.framework.dto;

import com.forest.framework.common.constant.CommonConstant;
import com.forest.framework.common.context.DebugDataBean;
import com.forest.framework.common.context.RequestContext;
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
     * 调试追踪信息
     */
    private DebugDataBean debugData;

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
        this(code, message, null, data, null);
    }

    /**
     * ResponseDto
     *
     * @param code      返回码
     * @param message   消息
     * @param requestId 请求ID
     * @param data      数据对象
     * @param debugData 调试追踪信息
     */
    protected ResponseDto(String code, String message, String requestId, T data, DebugDataBean debugData) {
        if (requestId == null) {
            requestId = RequestContext.getCurrentContext().getRequestId();
        }
        this.code = code;
        this.message = message;
        this.requestId = requestId;
        this.data = data;
        this.debugData = debugData;
    }

    /**
     * 静态创建方法
     *
     * @param data T
     * @return ResponseDto
     */
    public static <T> ResponseDto<T> getSuccessResponseDto(T data) {
        String requestId = RequestContext.getCurrentContext().getRequestId();
        return new ResponseDto<>(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_MSG, requestId, data, null);
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
}
