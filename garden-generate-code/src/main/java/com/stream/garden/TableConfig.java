package com.stream.garden;

/**
 * @author garden
 * @date 2019-06-22 9:09
 */
public class TableConfig {

    private String tableName;
    private String javaClassName;

    public TableConfig() {}

    public TableConfig(String tableName, String javaClassName) {
        this.tableName = tableName;
        this.javaClassName = javaClassName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }
}
