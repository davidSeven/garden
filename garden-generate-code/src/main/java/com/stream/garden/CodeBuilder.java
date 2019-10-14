package com.stream.garden;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 * @date 2019-06-22 9:05
 */
public class CodeBuilder {

    private JdbcConfig jdbcConfig;

    private List<TableConfig> tableConfigList = null;

    public static void run(JdbcConfig jdbcConfig, TableConfig tableConfig) {
        List<TableConfig> tableConfigList = new ArrayList<>();
        tableConfigList.add(tableConfig);
        run(jdbcConfig, tableConfigList);
    }

    public static void run(JdbcConfig jdbcConfig, List<TableConfig> tableConfigList) {
        if (null == jdbcConfig) {
            throw new NullPointerException("jdbcConfig is null");
        }
        if (null == tableConfigList) {
            throw new NullPointerException("tableConfigList is null");
        }
        System.out.println(JSONObject.toJSONString(jdbcConfig));
    }
}
