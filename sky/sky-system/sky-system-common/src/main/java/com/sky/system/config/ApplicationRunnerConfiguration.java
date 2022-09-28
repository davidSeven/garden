package com.sky.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerConfiguration implements ApplicationRunner {

    @Autowired
    private MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (messageSource instanceof I18nMessageSource) {
            I18nMessageSource messageSource = (I18nMessageSource) this.messageSource;
            messageSource.init();
            messageSource.refreshAll();
        }
    }
}
