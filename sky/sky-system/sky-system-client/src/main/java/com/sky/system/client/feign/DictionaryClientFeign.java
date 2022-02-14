package com.sky.system.client.feign;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.DictionaryDto;
import com.sky.system.api.remote.DictionaryRemoteService;
import com.sky.system.api.vo.DictionaryVO;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(contextId = "FeignClient.DictionaryClientFeign", name = SystemInterface.SERVICE, fallbackFactory = DictionaryClientFeign.HystrixClientFallback.class)
public interface DictionaryClientFeign extends DictionaryRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<DictionaryClientFeign> {

        @Override
        public DictionaryClientFeign create(Throwable throwable) {
            return new DictionaryClientFeign() {
                @Override
                public ResponseDto<Boolean> save(DictionaryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> update(DictionaryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<DictionaryVO>> list(DictionaryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<DictionaryVO>> tree(DictionaryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Integer> delete(DictionaryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<String> getValue(String code) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
