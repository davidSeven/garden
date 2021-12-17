package com.sky.system.config;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.LRUCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sky.system.api.constant.I18nConstant;
import com.sky.system.api.model.I18n;
import com.sky.system.service.I18nService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class I18nMessageSource extends AbstractMessageSource implements ResourceLoaderAware {
    private final int cacheSize = 1000;
    private final Map<Locale, Cache<String, String>> cacheMap = new HashMap<>(8);
    private final I18nService i18nService;

    public I18nMessageSource(I18nService i18nService) {
        this.i18nService = i18nService;
    }

    public void refreshAll() {
        List<Locale> localeList = this.i18nService.getLocaleList();
        for (Locale locale : localeList) {
            this.refreshAll(locale);
        }
    }

    public void refreshAll(Locale locale) {
        this.cacheMap.remove(locale);
        LambdaQueryWrapper<I18n> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(I18n::getCode, I18n::getValue);
        queryWrapper.eq(I18n::getLanguageType, locale.toLanguageTag());
        queryWrapper.eq(I18n::getState, I18nConstant.State.$1.getCode());
        queryWrapper.last("limit " + this.cacheSize);
        List<I18n> i18nList = this.i18nService.list(queryWrapper);
        Cache<String, String> cache = new LRUCache<>(this.cacheSize);
        if (CollectionUtils.isNotEmpty(i18nList)) {
            for (I18n i18n : i18nList) {
                cache.put(i18n.getCode(), i18n.getValue());
            }
        }
        this.cacheMap.put(locale, cache);
    }

    public void refresh(String code) {
        this.refresh(code, null);
    }

    public void refresh(String code, Locale locale) {
        if (null != locale) {
            List<I18n> i18nList = this.i18nService.getList(code);
            if (CollectionUtils.isNotEmpty(i18nList)) {
                for (I18n i18n : i18nList) {
                    this.refresh(i18n);
                }
            }
        } else {
            I18n i18n = this.i18nService.get(code, locale);
            this.refresh(i18n);
        }
    }

    private void refresh(I18n i18n) {
        if (null == i18n) {
            return;
        }
        Locale toLocale = LocaleUtils.toLocale(i18n.getLanguageType());
        Cache<String, String> cache = this.cacheMap.get(toLocale);
        if (null != cache) {
            cache.put(i18n.getCode(), i18n.getValue());
        } else {
            cache = new LRUCache<>(this.cacheSize);
            cache.put(i18n.getCode(), i18n.getValue());
            this.cacheMap.put(toLocale, cache);
        }
    }

    @Override
    protected MessageFormat resolveCode(@NonNull String code, @NonNull Locale locale) {
        String msg = this.getSourceFromCache(code, null, locale);
        return new MessageFormat(msg, locale);
    }

    @Override
    protected String resolveCodeWithoutArguments(@NonNull String code, @NonNull Locale locale) {
        return this.getSourceFromCache(code, null, locale);
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
        Cache<String, String> cache = cacheMap.get(locale);
        if (null != cache && cache.containsKey(code)) {
            return cache.get(code);
        } else {
            String value = "";
            try {
                if (null != this.getParentMessageSource()) {
                    value = this.getParentMessageSource().getMessage(code, args, locale);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            if (StringUtils.isEmpty(value)) {
                I18n i18n = i18nService.get(code, locale);
                this.refresh(i18n);
                if (null != i18n) {
                    value = i18n.getValue();
                } else {
                    logger.info("miss code:" + code);
                    value = code;
                }
            }
            return value;
        }
    }

    @Override
    public void setResourceLoader(@NonNull ResourceLoader resourceLoader) {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("i18n/messages");
        resourceBundleMessageSource.setDefaultEncoding("utf-8");
        resourceBundleMessageSource.setFallbackToSystemLocale(true);
        String message = resourceBundleMessageSource.getMessage("common.message", null, Locale.SIMPLIFIED_CHINESE);
        super.setParentMessageSource(resourceBundleMessageSource);
        logger.info("common.message:" + message);
    }
}
