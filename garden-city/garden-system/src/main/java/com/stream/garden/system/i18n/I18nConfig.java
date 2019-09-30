package com.stream.garden.system.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author garden
 * @date 2019-09-30 14:52
 */
@Configuration
public class I18nConfig {

    @Bean
    public MessageSource messageSource() {
        /*ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;*/
        SystemMessageSource messageSource = new SystemMessageSource();
        messageSource.reload();
        return messageSource;
    }
}
