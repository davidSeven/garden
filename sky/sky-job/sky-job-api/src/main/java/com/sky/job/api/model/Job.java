package com.sky.job.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.framework.dao.model.BaseModel;
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
@TableName("sys_job")
@ApiModel(value = "Job", description = "Job信息")
public class Job extends BaseModel<Job> {

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务状态 1=启用 2=禁用")
    private Integer enable;

    @ApiModelProperty(value = "表达式")
    private String cronTime;

    @ApiModelProperty(value = "是否启动重试机制")
    private Integer hasRedo;

    @ApiModelProperty(value = "截止重新执行次数")
    private Integer endRedoTimes;

    @ApiModelProperty(value = "当前第几次自动重试执行")
    private Integer currentRedoTime;

    @ApiModelProperty(value = "是否正在执行中,0--没有正在执行,1--正在手动/自动执行 2--执行失败")
    private Integer processStatus;

    @ApiModelProperty(value = "触发时间")
    private Date processDate;

    @ApiModelProperty(value = "结束时间")
    private Date processEndDate;

    @ApiModelProperty(value = "耗时")
    private Long processStamp;

    @ApiModelProperty(value = "执行信息")
    private String processMsg;

    @ApiModelProperty(value = "任务分组")
    private String taskGroup;

    @ApiModelProperty(value = "请求方式")
    private String httpMethod;

    @ApiModelProperty(value = "调用地址")
    private String timingCallUrl;
}
