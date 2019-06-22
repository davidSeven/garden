package com.stream.garden.jdbc;

import java.util.List;

/**
 * @author garden
 * @date 2019-06-22 9:03
 */
public class Table {

    private String name;

    private List<Column> columnList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
}
