package com.stream.garden.job.constants;

/**
 * @author garden
 * @date 2019-09-25 19:39
 */
public class JobConstant {

    /**
     * 任务数据键-url
     */
    public static final String JOB_DATA_KEY_URL = "URL";
    /**
     * 任务数据键-params
     */
    public static final String JOB_DATA_KEY_PARAMS = "PARAMS";
    /**
     * 任务组
     */
    public static final String JOB_GROUP_DEFAULT = "JOB_GROUP_DEFAULT";
    /**
     * 任务状态，启用
     */
    public static final String JOB_TASK_STATE_ENABLED = "1";
    /**
     * 任务状态，禁用
     */
    public static final String JOB_TASK_STATE_DISABLED = "0";

    /**
     * 任务日志类型，成功
     */
    public static final String JOB_TASK_LOG_TYPE_SUCCESS = "1";
    /**
     * 任务日志类型，失败
     */
    public static final String JOB_TASK_LOG_TYPE_FAIL = "0";

    private JobConstant() {
    }
}
