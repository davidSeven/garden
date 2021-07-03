package com.sky.system.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author zhangyuyuan
 * @date 2021-07-01 21:20
 */
public class PreciseModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        //对于库的分片collection存放的是所有的库的列表，这里代表flow_01~flow_12
        //配置的分片的sharding-column对应的值
        String timeValue = preciseShardingValue.getValue();
        //分库时配置的sharding-column
        String time = preciseShardingValue.getColumnName();
        //需要分库的逻辑表
        String table = preciseShardingValue.getLogicTableName();
        if (StringUtils.isBlank(timeValue)) {
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        //按月路由
        for (String each : collection) {
            String value = StringUtils.substring(timeValue, 4, 6); //获取到月份
            if (each.endsWith(value)) {
                //这里返回回去的就是最终需要查询的表名
                return each;
            }
        }
        return null;
    }
}
