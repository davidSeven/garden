package com.stream.garden.job.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务
 *
 * @author garden
 * @date 2019-09-25 19:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Task extends BaseModel<String> {
    private static final long serialVersionUID = 5195957237496585721L;

    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务cron表达式
     */
    private String cron;
    /**
     * 任务请求路径
     */
    private String url;
    /**
     * 任务请求参数
     */
    private String params;
    /**
     * 任务状态
     */
    private String state;
}
