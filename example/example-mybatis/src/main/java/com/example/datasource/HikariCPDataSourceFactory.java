package com.example.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class HikariCPDataSourceFactory extends UnpooledDataSourceFactory {

    public HikariCPDataSourceFactory() {
        this.dataSource = new HikariDataSource();
    }
}
