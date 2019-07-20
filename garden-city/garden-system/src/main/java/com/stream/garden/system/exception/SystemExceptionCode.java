package com.stream.garden.system.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * @author garden
 * @date 2019-06-21 10:31
 */
public enum SystemExceptionCode implements AppCode {

    /*
     * 20000-29999
     * USER     20000 - 22999 (3000)
     * ROLE     23000 - 25999 (3000)
     * GROUP    26000 - 28999 (3000)
     * MENU     29000 - 31999 (3000)
     * FUNCTION 32000 - 34999 (3000)
     * LOOKUP   35000 - 37999 (3000)
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

    GROUP_ADD_EXCEPTION(26000, "新增群组异常"),
    GROUP_EDIT_EXCEPTION(26001, "修改群组异常"),

    MENU_ADD_EXCEPTION(20900, "新增菜单异常"),
    MENU_NAME_REPEAT(20901, "菜单[{0}]已存在"),
    MENU_EDIT_EXCEPTION(20902, "修改菜单异常"),
    MENU_EXISTS_CHILDREN_DELETE_EXCEPTION(20903, "存在子级菜单，不允许删除"),

    FUNCTION_ADD_EXCEPTION(32000, "新增功能异常"),
    FUNCTION_EDIT_EXCEPTION(32001, "修改功能异常"),

    LOOKUP_ADD_EXCEPTION(35000, "新增Lookup异常"),
    LOOKUP_EDIT_EXCEPTION(35001, "修改Lookup异常"),

    LOOKUP_ITEM_ADD_EXCEPTION(35002, "新增Lookup子项异常"),
    LOOKUP_ITEM_EDIT_EXCEPTION(35003, "修改Lookup子项异常"),
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
