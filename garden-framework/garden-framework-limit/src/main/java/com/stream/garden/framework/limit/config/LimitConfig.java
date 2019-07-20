package com.stream.garden.framework.limit.config;

import com.stream.garden.framework.limit.enums.LimitRule;
import com.stream.garden.framework.limit.enums.LimitType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author garden
 * @date 2019-07-20 9:43
 */
@Component
@ConfigurationProperties(prefix = LimitConfig.CONFIG_PREFIX)
public class LimitConfig {

    static final String CONFIG_PREFIX = "garden.limit";

    /**
     * 规则
     * 默认NONE
     */
    private LimitRule limitRule = LimitRule.NONE;

    /*------------------------------------------------------------------*/
    /**
     * @see com.stream.garden.framework.limit.annotation.Limit
     */
    private String name = "";

    private String key = "";

    private String prefix = "";

    private int period = 1;

    private int count = 1;

    private LimitType limitType = LimitType.CUSTOMER;
    /*------------------------------------------------------------------*/

    public LimitRule getLimitRule() {
        return limitRule;
    }

    public void setLimitRule(LimitRule limitRule) {
        this.limitRule = limitRule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LimitType getLimitType() {
        return limitType;
    }

    public void setLimitType(LimitType limitType) {
        this.limitType = limitType;
    }
}
