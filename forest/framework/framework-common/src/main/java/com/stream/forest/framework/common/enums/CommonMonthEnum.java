package com.stream.forest.framework.common.enums;

@SuppressWarnings("unused")
public enum CommonMonthEnum implements BaseEnum {

    jan("january", "一月"), //
    feb("february", "二月"),//
    mar("march", "三月"), //
    apr("april", "四月"), //
    may("may", "五月"), //
    jun("june", "六月"), //
    jul("july", "七月"), //
    aug("august", "八月"), //
    sep("september", "九月"), //
    oct("october", "十月"), //
    nov("november", "十一月"), //
    dec("december", "十二月"), //
    ;

    private final String key;
    private final String value;

    CommonMonthEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

}
