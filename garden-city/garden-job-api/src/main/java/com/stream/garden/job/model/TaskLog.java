package com.stream.garden.job.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 任务执行日志
 *
 * @author garden
 * @date 2019-10-22 16:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskLog extends BaseModel<String> {
    private static final long serialVersionUID = 6640148419914833294L;

    private String taskId;
    /**
     * 任务执行类型，1成功，2失败
     */
    private String type;
    /**
     * 返回的内容
     */
    private String responseContent;
    /**
     * 任务开始执行时间
     */
    private Date beginDate;
    /**
     * 任务结束执行时间
     */
    private Date endDate;
    /**
     * 任务消耗的时间
     */
    private Long consumeTime;
}
