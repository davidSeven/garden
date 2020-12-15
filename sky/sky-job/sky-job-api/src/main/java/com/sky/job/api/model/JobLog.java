package com.sky.job.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.api.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @date 2020-12-14 014 14:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_job_log")
@ApiModel(value = "JobLog", description = "JobLog信息")
public class JobLog extends BaseModel<JobLog> {

    @ApiModelProperty(value = "任务ID")
    private Long taskId;

    @ApiModelProperty(value = "响应信息")
    private String responseMsg;

    @ApiModelProperty(value = "开始时间")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "耗时")
    private Long consumeTime;
}
