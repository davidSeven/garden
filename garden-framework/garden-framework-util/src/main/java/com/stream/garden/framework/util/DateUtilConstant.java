package com.stream.garden.framework.util;

/**
 * @author garden
 * @date 2020-06-05 17:14
 */
public interface DateUtilConstant {

    // 日期正则表达式
    String REG_DATE_FMT_Y = "^\\d{4}$";
    String REG_DATE_FMT_Y_M = "^\\d{4}-\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_H = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_HM = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_HMS = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
    String REG_DATE_FMT_Y_M_D_HMSSS = "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{1,3}$";
}
