package com.sky.system.client.feign;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.model.I18nMiss;
import com.sky.system.api.remote.I18nMissRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(contextId = "FeignClient.I18nMissClientFeign", name = SystemInterface.SERVICE, fallbackFactory = I18nMissClientFeign.HystrixClientFallback.class)
public interface I18nMissClientFeign extends I18nMissRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<I18nMissClientFeign> {

        @Override
        public I18nMissClientFeign create(Throwable throwable) {
            return new I18nMissClientFeign() {
                @Override
                public ResponseDto<Boolean> save(I18nMiss i18nMiss) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
