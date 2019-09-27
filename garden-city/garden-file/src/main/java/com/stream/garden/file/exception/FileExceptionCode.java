package com.stream.garden.file.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * @author garden
 * @date 2019-06-21 10:31
 */
public enum FileExceptionCode implements AppCode {

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
     */

    /*---------------------------FILE------------------------------*/
    FILE_MANAGE_ADD_EXCEPTION(29000, "新增异常"),
    FILE_MANAGE_EDIT_EXCEPTION(29001, "修改异常"),
    FILE_MANAGE_ADD_REPEAT(29002, "编码[{0}]已存在"),

    FILE_MANAGE_CODE_NOT_NULL(29010, "文件码不能为空"),
    FILE_MANAGE_UNREGISTERED(29011, "文件码未[{0}]注册"),

    FILE_INFO_BIZ_CODE_NOT_NULL(29012, "业务编码不能为空"),
    /*---------------------------FILE------------------------------*/

    ;

    private int code;
    private String message;

    private FileExceptionCode(int code, String message) {
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
