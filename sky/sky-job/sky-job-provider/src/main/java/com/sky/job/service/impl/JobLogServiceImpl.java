package com.sky.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.job.api.model.JobLog;
import com.sky.job.dao.JobLogDao;
import com.sky.job.service.JobLogService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @date 2020-12-14 014 14:09
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogDao, JobLog> implements JobLogService {

    @Override
    public int clearLogs(int days) {
        if (days <= 0) {
            return 0;
        }
        Date date = DateUtils.addDays(new Date(), -days);
        return this.baseMapper.clearLogs(date);
    }
}
