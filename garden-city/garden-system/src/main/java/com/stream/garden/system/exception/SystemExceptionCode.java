package com.stream.garden.system.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * @author garden
 * @date 2019-06-21 10:31
 */
public enum SystemExceptionCode implements AppCode {

    /*
     * 20000-29999
     * USER     20000 - 202999
     * ROLE     20300 - 205999
     * GROUP    20600 - 208999
     * MENU     20900 - 311999
     */

    /**
     * 用户新增失败
     */
    USER_INSERT_EXCEPTION(20000, "新增用户异常"),
    /**
     * 用户编码重复
     */
    USER_CODE_REPEAT(20001, "用户编码[{0}]已存在"),

    /**
     * 角色编码重复
     */
    ROLE_CODE_REPEAT(20301, "角色编码[{0}]已存在"),


    MENU_ADD_EXCEPTION(20900, "新增菜单异常"),
    MENU_NAME_REPEAT(20901, "菜单[{0}]已存在"),
    MENU_EDIT_EXCEPTION(20902, "修改菜单异常"),
    MENU_EXISTS_CHILDREN_DELETE_EXCEPTION(20903, "存在子级菜单，不允许删除"),
    ;

    private int code;
    private String message;

    private SystemExceptionCode(int code, String message) {
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
