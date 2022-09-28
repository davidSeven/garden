package com.sky.system.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.dto.I18nQueryDto;
import com.sky.system.api.model.I18n;
import com.sky.system.api.remote.I18nRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@FeignClient(contextId = "FeignClient.I18nClientFeign", name = SystemInterface.SERVICE, fallbackFactory = I18nClientFeign.HystrixClientFallback.class)
public interface I18nClientFeign extends I18nRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<I18nClientFeign> {

        @Override
        public I18nClientFeign create(Throwable throwable) {
            return new I18nClientFeign() {
                @Override
                public ResponseDto<I18n> get(I18nDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> save(I18nDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> saveBatch(List<I18nDto> list) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> update(I18nDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> updateBatch(List<I18nDto> list) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> delete(I18nDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> deleteBatch(I18nDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<IPage<I18n>> page(I18nQueryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<I18n>> list(I18nDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<Locale>> getLocaleList() {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
