package com.forest.framework.common.constant;

import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public interface CommonConstant {

    String ENCODING = StandardCharsets.UTF_8.name();
    MediaType MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

    String DEBUG = "DEBUG";
    String INFO = "INFO";
    String WARN = "WARN";
    String ERROR = "ERROR";

    String EMPTY = "";
    String BLANK = " ";
    String COMMA = ",";
    String DOT = ".";
    String SEMICOLON = ";";
    String COLON = ":";
    String SPLIT = "/";
    String WELL = "#";
    String AT = "@";
    String PLAINT = "#";
    String DOLLAR = "$";
    String PERCENT = "%";
    String AND = "&";
    String ASTERISK = "*";
    String VERTICAL = "|";
    String ADD = "+";
    String SUBTRACT = "-";
    String WAVE = "~";

    /**
     * 返回Response的field
     */
    String RESPONSE_FIELD_CODE = "code";
    String RESPONSE_FIELD_MESSAGE = "message";
    String RESPONSE_FIELD_DATA = "data";
    String RESPONSE_FIELD_REQUEST_ID = "requestId";

    /**
     * 地球半径
     */
    double EARTH_RADIUS = 6378.137;

    /**
     * 默认分页
     */
    int DEFAULT_PAGE_NUM = 1;
    int DEFAULT_PAGE_SIZE = 20;

    /**
     * 导出默认分页条数
     */
    int DEFAULT_EXPORT_PAGE_SIZE = 5000;

    /**
     * 全局错误码
     */
    String SUCCESS_CODE = "00000";
    String SUCCESS_MSG = "操作成功";
    String DEFAULT_ERROR_CODE = "00099";
    String DEFAULT_ERROR_MSG = "系统内部错误";
    String METHOD_ARGUMENT_NOT_VALID_CODE = "00098";
    String METHOD_ARGUMENT_NOT_VALID_MSG = "参数校验不通过";
    String NO_AUTHORITY_CODE = "00097";
    String NO_AUTHORITY_MSG = "没有权限";
    String GET_USER_FAILED_CODE = "00096";
    String GET_USER_FAILED_MSG = "获取用户信息失败";
    String NO_RECORD_EFFECTED_CODE = "00095";
    String NO_RECORD_EFFECTED_MSG = "更新失败(没有符合条件的记录)";
    String INFO_NOT_SUPPORT_CODE = "00094";
    String INFO_NOT_SUPPORT_MSG = "该方法不支持";
    String CANNOT_FIND_CLASS_CODE = "00093";
    String CANNOT_FIND_CLASS_MSG = "找不到类";
    String REQUEST_TIMEOUT_CODE = "00092";
    String REQUEST_TIMEOUT_MSG = "请求超时";
    String DATA_ERROR_CODE = "00091";
    String DATA_ERROR_MSG = "数据异常";
    String TOO_FREQUENT_CODE = "00090";
    String TOO_FREQUENT_MSG = "操作太频繁，请稍候再试。";
    String UNSUPPORT_DATATYPE_CODE = "00089";
    String UNSUPPORT_DATATYPE_MSG = "不支持数据类型";
    String EXCEL_IMPEXP_CODE = "00088";
    String EXCEL_IMPEXP_MSG = "导入导出错误";

    // 日期正则表达式
    String REG_DATE_FMT_Y = "^\\d{4}$";
    String REG_DATE_FMT_Y_M = "^\\d{4}-\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_H = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_HM = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_HMS = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_HMSSS = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{1,3}$";

}
