package com.sky.framework.api.context;

import com.sky.framework.api.enums.DataScopeEnum;

import java.util.List;

/**
 * @date 2020/11/21 20:47
 */
public class SqlContext {

    private DataScopeEnum dataScopeEnum;

    private List<SqlConditionContext> conditionContextList;

    public DataScopeEnum getDataScopeEnum() {
        return dataScopeEnum;
    }

    public void setDataScopeEnum(DataScopeEnum dataScopeEnum) {
        this.dataScopeEnum = dataScopeEnum;
    }

    public List<SqlConditionContext> getConditionContextList() {
        return conditionContextList;
    }

    public void setConditionContextList(List<SqlConditionContext> conditionContextList) {
        this.conditionContextList = conditionContextList;
    }
}
