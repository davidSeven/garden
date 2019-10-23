package com.stream.garden.job.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * 异常编码
 */
public enum JobExceptionCode implements AppCode {

    /*
     * 20000-29999
     * USER         20000 - 20999 (1000)
     * ROLE         21000 - 21999 (1000)
     * GROUP        22000 - 22999 (1000)
     * MENU         23000 - 23999 (1000)
     * FUNCTION     24000 - 24999 (1000)
     * LOOKUP       25000 - 25499 (500)
     * LOOKUP_ITEM  25500 - 25999 (500)
     * ORGANIZATION 26000 - 26999 (1000)
     * DATA_SCOPE   27000 - 27999 (1000)
     * POSITION     28000 - 28999 (1000)
     * FILE         29000 - 29499 (500)
     * JOB          29500 - 29999 (500)
     * DICTIONARY   30000 - 30499 (500)
     */

    /*---------------------------LOOKUP------------------------------*/
    TASK_ADD_EXCEPTION(29500, "新增任务异常"),
    TASK_EDIT_EXCEPTION(29501, "修改任务异常"),
    TASK_ADD_REPEAT(29502, "编码[{0}]已存在"),
    TASK_CRON_EXCEPTION(29503, "任务Cron格式错误"),
    TASK_INIT_EXCEPTION(29504, "任务初始化异常"),
    /*---------------------------LOOKUP------------------------------*/
    ;

    private int code;
    private String message;

    private JobExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
