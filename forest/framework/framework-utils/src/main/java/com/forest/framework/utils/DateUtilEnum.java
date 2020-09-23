package com.forest.framework.utils;

/**
 *
 */
public enum DateUtilEnum {

    DATE_FMT_Y("yyyy"),
    DATE_FMT_Y_M("yyyy-MM"),
    DATE_FMT_Y_M_D("yyyy-MM-dd"),
    DATE_FMT_Y_M_D_H("yyyy-MM-dd HH"),
    DATE_FMT_Y_M_D_HM("yyyy-MM-dd HH:mm"),
    DATE_FMT_Y_M_D_HMS("yyyy-MM-dd HH:mm:ss"),
    DATE_FMT_Y_M_D_HMSSS("yyyy-MM-dd HH:mm:ss.SSS"),

    DATE_FMT_Y_CN("yyyy年"),
    DATE_FMT_Y_M_CN("yyyy年MM月"),
    DATE_FMT_Y_M_D_CN("yyyy年MM月dd日"),
    DATE_FMT_Y_M_D_H_CN("yyyy年MM月dd日HH时"),
    DATE_FMT_Y_M_D_HM_CN("yyyy年MM月dd日HH时mm分"),
    DATE_FMT_Y_M_D_HMS_CN("yyyy年MM月dd日HH时mm分ss秒"),
    DATE_FMT_Y_M_D_HMSSS_CN("yyyy年MM月dd日HH时mm分ss秒SSS毫秒"),

    DATE_FMT_YM("yyyyMM"),
    DATE_FMT_YMD("yyyyMMdd"),
    DATE_FMT_YMDH("yyyyMMddHH"),
    DATE_FMT_YMDHM("yyyyMMddHHmm"),
    DATE_FMT_YMDHMS("yyyyMMddHHmmss"),
    DATE_FMT_YMDHMSSSSS("yyyyMMddHHmmssSSS"),

    DATE_FMT_H("HH"),
    DATE_FMT_H_M("HH:mm"),
    DATE_FMT_H_M_S("HH:mm:ss"),
    DATE_FMT_H_M_S_SSS("HH:mm:ss.SSS"),

    DATE_FMT_H_CN("HH时"),
    DATE_FMT_H_M_CN("HH时mm分"),
    DATE_FMT_H_M_S_CN("HH时mm分ss秒"),
    DATE_FMT_H_M_S_SSS_CN("HH时mm分ss秒SSS毫秒"),

    DATE_FMT_HM("HHmm"),
    DATE_FMT_HMS("HHmmss"),

    DATE_SHORT_FMT_YM("yyMM"),
    DATE_SHORT_FMT_YMD("yyMMdd"),
    DATE_SHORT_FMT_YMDH("yyMMddHH"),
    DATE_SHORT_FMT_YMDHM("yyMMddHHmm"),
    DATE_SHORT_FMT_YMDHMS("yyMMddHHmmss"),

    DATE_FMT_SLASH_MDY("MM/dd/yyyy"),

    ;

    private String value;

    DateUtilEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
