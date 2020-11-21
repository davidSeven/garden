package com.sky.framework.api.context;

import com.sky.framework.api.enums.DataScopeEnum;

import java.util.List;

/**
 * 菜单配置 --> 配置接口URL --> 配置接口数据权限 --> 拦截器上获取到配置信息，将配置信息设置到上下文中 --> 在SQL拦截器中拼接SQL
 *
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
