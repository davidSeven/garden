package com.sky.system.utils;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.LRUCache;
import com.sky.framework.json.JsonIntensifyConvert;

public abstract class AbstractUserNameJsonIntensifyConvert implements JsonIntensifyConvert {

    protected static int defaultCapacity = 1000;
    protected Cache<String, String> cache;
    protected long version;

    public AbstractUserNameJsonIntensifyConvert() {
        this(defaultCapacity);
    }

    public AbstractUserNameJsonIntensifyConvert(int capacity) {
        this(new LRUCache<>(capacity));
    }

    public AbstractUserNameJsonIntensifyConvert(Cache<String, String> cache) {
        this.cache = cache;
    }

    @Override
    public String convert(String value) {
        String text = this.cache.get(value);
        if (null == text) {
            text = this.getName(value);
            this.cache.put(value, text);
        }
        return text;
    }

    public abstract String getName(String value);

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
