package com.stream.garden.job.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.job.dao.ITaskLogDao;
import com.stream.garden.job.model.TaskLog;
import com.stream.garden.job.service.ITaskLogService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-10-23 15:58
 */
@Service
public class TaskLogService extends AbstractBaseService<TaskLog, ITaskLogDao> implements ITaskLogService {

}
