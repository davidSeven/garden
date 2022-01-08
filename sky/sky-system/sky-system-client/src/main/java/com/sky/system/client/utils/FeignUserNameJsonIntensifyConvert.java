package com.sky.system.client.utils;

import cn.hutool.cache.impl.TimedCache;
import com.sky.system.client.service.UserClientService;
import com.sky.system.utils.AbstractUserNameJsonIntensifyConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("FeignUserNameJsonIntensifyConvert")
public class FeignUserNameJsonIntensifyConvert extends AbstractUserNameJsonIntensifyConvert {

    @Autowired
    private UserClientService userClientService;

    public FeignUserNameJsonIntensifyConvert() {
        // 缓存时间1天，单位毫秒
        super(new TimedCache<>(86400000));
    }

    @Override
    public String getName(String value) {
        return this.userClientService.getNameByCode(value);
    }

}
