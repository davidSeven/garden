package com.stream.garden.system.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author garden
 * @date 2019-09-30 14:52
 */
@Configuration
public class I18nConfig {

    private ApplicationContext applicationContext;

    @Autowired
    public I18nConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public MessageSource messageSource() {
        /*ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;*/
        SystemMessageSource messageSource = new SystemMessageSource(this.applicationContext);
        messageSource.reload();
        return messageSource;
    }
}
