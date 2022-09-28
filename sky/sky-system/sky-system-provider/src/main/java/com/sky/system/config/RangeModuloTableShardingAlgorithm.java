package com.sky.system.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangyuyuan
 * @date 2021-07-09 15:31
 */
public class RangeModuloTableShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        Range<Long> valueRange = shardingValue.getValueRange();
        Set<String> tableSet = new HashSet<>(availableTargetNames.size());
        Long lowerEndpoint = valueRange.lowerEndpoint();
        if (lowerEndpoint <= 10) {
            tableSet.add(shardingValue.getLogicTableName() + 0);
        }
        Long upperEndpoint = valueRange.upperEndpoint();
        if (upperEndpoint > 10) {
            tableSet.add(shardingValue.getLogicTableName() + 1);
        }
        return tableSet;
    }
}
