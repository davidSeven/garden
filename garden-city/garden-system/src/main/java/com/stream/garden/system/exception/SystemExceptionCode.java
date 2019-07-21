package com.stream.garden.system.exception;

import com.stream.garden.framework.api.exception.AppCode;

/**
 * @author garden
 * @date 2019-06-21 10:31
 */
public enum SystemExceptionCode implements AppCode {

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
     */

    /*---------------------------USER------------------------------*/
    USER_ADD_EXCEPTION(20000, "新增用户异常"),
    USER_CODE_REPEAT(20001, "用户编码[{0}]已存在"),
    /*---------------------------USER------------------------------*/

    /*---------------------------ROLE------------------------------*/
    ROLE_ADD_EXCEPTION(21000, "新增角色异常"),
    ROLE_CODE_REPEAT(21001, "角色编码[{0}]已存在"),
    /*---------------------------ROLE------------------------------*/

    /*---------------------------GROUP------------------------------*/
    GROUP_ADD_EXCEPTION(22000, "新增群组异常"),
    GROUP_EDIT_EXCEPTION(22001, "修改群组异常"),
    /*---------------------------GROUP------------------------------*/

    /*---------------------------MENU------------------------------*/
    MENU_ADD_EXCEPTION(23000, "新增菜单异常"),
    MENU_NAME_REPEAT(23001, "菜单[{0}]已存在"),
    MENU_EDIT_EXCEPTION(23002, "修改菜单异常"),
    MENU_EXISTS_CHILDREN_DELETE_EXCEPTION(23003, "存在子级菜单，不允许删除"),
    /*---------------------------MENU------------------------------*/

    /*---------------------------FUNCTION------------------------------*/
    FUNCTION_ADD_EXCEPTION(24000, "新增功能异常"),
    FUNCTION_EDIT_EXCEPTION(24001, "修改功能异常"),
    /*---------------------------FUNCTION------------------------------*/

    /*---------------------------LOOKUP------------------------------*/
    LOOKUP_ADD_EXCEPTION(25000, "新增Lookup异常"),
    LOOKUP_EDIT_EXCEPTION(25001, "修改Lookup异常"),

    LOOKUP_ITEM_ADD_EXCEPTION(25500, "新增Lookup子项异常"),
    LOOKUP_ITEM_EDIT_EXCEPTION(25501, "修改Lookup子项异常"),
    /*---------------------------LOOKUP------------------------------*/

    /*---------------------------ORGANIZATION------------------------------*/
    ORGANIZATION_ADD_EXCEPTION(26000, "新增组织异常"),
    ORGANIZATION_NAME_REPEAT(26001, "组织[{0}]已存在"),
    ORGANIZATION_EDIT_EXCEPTION(26002, "修改组织异常"),
    ORGANIZATION_EXISTS_CHILDREN_DELETE_EXCEPTION(26003, "存在子级组织，不允许删除"),
    /*---------------------------ORGANIZATION------------------------------*/

    /*---------------------------POSITION------------------------------*/
    POSITION_ADD_EXCEPTION(28000, "新增功能异常"),
    POSITION_EDIT_EXCEPTION(28001, "修改功能异常"),
    /*---------------------------POSITION------------------------------*/

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
