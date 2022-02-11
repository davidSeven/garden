package com.sky.system.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_request_log")
public class RequestLog extends BaseModel<RequestLog> {

    @ApiModelProperty(value = "跨度ID")
    private Long spanId;

    @ApiModelProperty(value = "请求域名")
    private String requestDomain;

    @ApiModelProperty(value = "请求路径")
    private String requestUri;

    @ApiModelProperty(value = "请求方法")
    private String requestMethod;

    @ApiModelProperty(value = "请求ip")
    private String requestIp;

    @ApiModelProperty(value = "请求header")
    private String requestHeader;

    @ApiModelProperty(value = "请求body")
    private String requestBody;

    @ApiModelProperty(value = "请求body大小")
    private Long requestBodySize;

    @ApiModelProperty(value = "请求时间")
    private Date requestTime;

    @ApiModelProperty(value = "响应header")
    private String responseHeader;

    @ApiModelProperty(value = "响应body")
    private String responseBody;

    @ApiModelProperty(value = "响应body大小")
    private Long responseBodySize;

    @ApiModelProperty(value = "响应时间")
    private Date responseTime;

    @ApiModelProperty(value = "响应状态码")
    private Integer responseStatus;

    @ApiModelProperty(value = "请求耗时")
    private Long requestConsumeTime;
}
