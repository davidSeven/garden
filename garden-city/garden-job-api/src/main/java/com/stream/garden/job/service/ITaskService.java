package com.stream.garden.job.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.job.model.Task;

/**
 * @author garden
 * @date 2019-10-22 16:55
 */
public interface ITaskService extends IBaseService<Task, String> {

    /**
     * 出售任务
     */
    void initJob() throws ApplicationException;
}
