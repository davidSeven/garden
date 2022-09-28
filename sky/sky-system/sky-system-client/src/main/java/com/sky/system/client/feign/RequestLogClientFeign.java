package com.sky.system.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.RequestLogQueryDto;
import com.sky.system.api.model.RequestLog;
import com.sky.system.api.remote.RequestLogRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(contextId = "FeignClient.RequestLogClientFeign", name = SystemInterface.SERVICE, fallbackFactory = RequestLogClientFeign.HystrixClientFallback.class)
public interface RequestLogClientFeign extends RequestLogRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<RequestLogClientFeign> {

        @Override
        public RequestLogClientFeign create(Throwable throwable) {
            return new RequestLogClientFeign() {
                @Override
                public ResponseDto<RequestLog> get(Long id) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> save(RequestLog requestLog) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> update(RequestLog requestLog) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<IPage<RequestLog>> page(RequestLogQueryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
