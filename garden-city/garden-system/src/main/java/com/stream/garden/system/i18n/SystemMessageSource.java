package com.stream.garden.system.i18n;

import com.stream.garden.framework.api.interfaces.ISystemMessage;
import com.stream.garden.framework.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author garden
 * @date 2019-09-30 15:15
 */
public class SystemMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    // 这个是用来缓存数据库中获取到的配置的 数据库配置更改的时候可以调用reload方法重新加载
    // 当然 实际使用者也可以不使用这种缓存的方式
    private static final Map<String, Map<String, String>> LOCAL_CACHE = new ConcurrentHashMap<>(256);
    private final Logger logger = LoggerFactory.getLogger(SystemMessageSource.class);
    ResourceLoader resourceLoader;
    private ApplicationContext applicationContext;

    public SystemMessageSource(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 重新将数据库中的国际化配置加载
     */
    public void reload() {
        LOCAL_CACHE.clear();
        LOCAL_CACHE.putAll(loadAllMessageResourcesFromDB());
    }

    /**
     * 从数据库中获取所有国际化配置 这边可以根据自己数据库表结构进行相应的业务实现
     * 对应的语言能够取出来对应的值就行了 无需一定要按照这个方法来
     */
    public Map<String, Map<String, String>> loadAllMessageResourcesFromDB() {

        final Map<String, String> zhCnMessageResources = new HashMap<>();
        final Map<String, String> enUsMessageResources = new HashMap<>();

        zhCnMessageResources.put("message.login.topTitle", "后台登录2");
        zhCnMessageResources.put("message.login.loginTitle", "后台系统登录2");
        enUsMessageResources.put("message.login.topTitle", "Login2");
        enUsMessageResources.put("message.login.loginTitle", "System Login2");

        // 加载ISystemMessage中实现的国际化内容
        if (null != applicationContext) {
            Map<String, ISystemMessage> systemMessageMap = applicationContext.getBeansOfType(ISystemMessage.class);
            if (null != systemMessageMap) {
                for (ISystemMessage systemMessage : systemMessageMap.values()) {

                    Map<String, String> zhMessage = systemMessage.getMessage("zh");
                    if (!CollectionUtil.isEmpty(zhMessage)) {
                        zhCnMessageResources.putAll(zhMessage);
                    }

                    Map<String, String> enMessage = systemMessage.getMessage("en");
                    if (!CollectionUtil.isEmpty(enMessage)) {
                        enUsMessageResources.putAll(enMessage);
                    }
                }
            }
        }

        LOCAL_CACHE.put("zh", zhCnMessageResources);
        LOCAL_CACHE.put("en", enUsMessageResources);

        return new HashMap<>();
    }

    /**
     * 从缓存中取出国际化配置对应的数据 或者从父级获取
     *
     * @param code   code
     * @param args   args
     * @param locale locale
     * @return string
     */
    public String getSourceFromCache(String code, Object[] args, Locale locale) {
        String language = locale.getLanguage();
        Map<String, String> props = LOCAL_CACHE.get(language);
        if (null != props && props.containsKey(code)) {
            return props.get(code);
        } else {
            try {
                if (null != this.getParentMessageSource()) {
                    return this.getParentMessageSource().getMessage(code, args, locale);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return code;
        }
    }

    // 下面三个重写的方法是比较重要的
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader == null ? new DefaultResourceLoader() : resourceLoader);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getSourceFromCache(code, null, locale);
        return new MessageFormat(msg, locale);
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getSourceFromCache(code, null, locale);
    }
}
