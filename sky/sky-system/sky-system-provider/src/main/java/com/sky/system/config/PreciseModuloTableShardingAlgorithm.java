package com.sky.system.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author zhangyuyuan
 * @date 2021-07-01 21:20
 */
public class PreciseModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        // 对于库的分片collection存放的是所有的库的列表，这里代表flow_01~flow_12
        // 配置的分片的sharding-column对应的值
        Long idValue = preciseShardingValue.getValue();
        // 分库时配置的sharding-column
        // String idColumn = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        // 需要分库的逻辑表
        int tableIndex = 0;
        if (idValue > 10) {
            tableIndex = 1;
        }
        for (String tableName : collection) {
            if (tableName.equals(logicTableName + tableIndex)) {
                return tableName;
            }
        }
        return null;
    }
}
