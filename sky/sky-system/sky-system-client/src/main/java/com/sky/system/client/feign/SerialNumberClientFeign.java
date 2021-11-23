package com.sky.system.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.GenerateNumberDto;
import com.sky.system.api.dto.SerialNumberQueryDto;
import com.sky.system.api.model.SerialNumber;
import com.sky.system.api.remote.SerialNumberRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(contextId = "FeignClient.SerialNumberClientFeign", name = SystemInterface.SERVICE, fallbackFactory = SerialNumberClientFeign.HystrixClientFallback.class)
public interface SerialNumberClientFeign extends SerialNumberRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<SerialNumberClientFeign> {

        @Override
        public SerialNumberClientFeign create(Throwable throwable) {
            return new SerialNumberClientFeign() {
                @Override
                public ResponseDto<SerialNumber> get(Long id) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> save(SerialNumber serialNumber) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> update(SerialNumber serialNumber) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<IPage<SerialNumber>> page(SerialNumberQueryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<String> generateNumber(GenerateNumberDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<String>> generateNumbers(GenerateNumberDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
