package com.sky.inv.client.feign;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.inv.api.InventoryInterface;
import com.sky.inv.api.dto.InventoryStatementBatchDto;
import com.sky.inv.api.remote.InventoryRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @date 2020-10-28 028 14:53
 */
@FeignClient(contextId = "FeignClient.InventoryClientFeign", name = InventoryInterface.SERVICE, fallbackFactory = InventoryClientFeign.HystrixClientFallback.class)
public interface InventoryClientFeign extends InventoryRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<InventoryClientFeign> {

        @Override
        public InventoryClientFeign create(Throwable throwable) {
            return new InventoryClientFeign() {
                @Override
                public ResponseDto<Boolean> in(InventoryStatementBatchDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> out(InventoryStatementBatchDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> occ(InventoryStatementBatchDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> unOcc(InventoryStatementBatchDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
