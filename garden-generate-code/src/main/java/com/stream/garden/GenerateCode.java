package com.stream.garden;

/**
 * @author garden
 * @date 2019-06-22 8:58
 */
public class GenerateCode {

    public static void main(String[] args) {

        JdbcConfig jdbcConfig = new JdbcConfig()
                .setDriver("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/garden?useUnicode=true&characterEncoding=utf-8&useSSL=true")
                .setUsername("garden")
                .setPassword("123456");

        TableConfig tableConfig = new TableConfig();
        tableConfig.setTableName("");
        tableConfig.setJavaClassName("");

        CodeBuilder.run(jdbcConfig, tableConfig);
    }
}
