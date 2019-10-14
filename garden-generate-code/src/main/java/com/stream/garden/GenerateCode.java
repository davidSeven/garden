package com.stream.garden;

/**
 * @author garden
 * @date 2019-06-22 8:58
 */
public class GenerateCode {

    public static void main(String[] args) {
        // jdbc配置
        JdbcConfig jdbcConfig = JdbcConfig.readProperties("jdbc.properties");
        // table配置
        TableConfig tableConfig = new TableConfig("", "");
        // 调用
        CodeBuilder.run(jdbcConfig, tableConfig);
    }
}
