package com.sky.framework.api.context;

/**
 * @date 2020/11/21 20:58
 */
public class SqlConditionContext {

    private String column;

    private String condition;

    private Object value;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
