package com.sky.system.config;

import com.sky.system.api.service.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = I18nConfiguration.CONFIG_PREFIX)
@DictionaryPropertyConfiguration
public class I18nConfiguration implements DictionaryAware, EnvironmentAware {

    static final String CONFIG_PREFIX = "com.sky.system.i18n";
    @DictionaryPropertyIgnore
    private String applicationName;
    // com.sky.system.i18n.applicationName.values
    @DictionaryPropertyPrefix(value = "applicationPrefix")
    private List<String> values;
    // com.sky.system.i18n.values
    @DictionaryPropertyName(value = "values")
    private List<String> rootValues;

    public String applicationPrefix() {
        return CONFIG_PREFIX + "." + applicationName;
    }

    @Override
    public String prefix() {
        return CONFIG_PREFIX;
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.applicationName = environment.getProperty("spring.application.name");
    }
}
