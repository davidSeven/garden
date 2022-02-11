package com.sky.system.config;

import com.sky.system.api.service.DictionaryProperty;
import com.sky.system.api.service.DictionaryPropertyConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = ResourceApplicationConfig.CONFIG_PREFIX)
@DictionaryPropertyConfiguration
public class ResourceApplicationConfig implements DictionaryProperty {

    static final String CONFIG_PREFIX = "com.sky.system.resource.application";

    /**
     * 忽略的应用名称
     */
    public Set<String> ignore;

    /**
     * 应用列表
     */
    public List<String> values;

    /**
     * 自动获取
     */
    public boolean discovery;

    @Override
    public String prefix() {
        return CONFIG_PREFIX;
    }
}
