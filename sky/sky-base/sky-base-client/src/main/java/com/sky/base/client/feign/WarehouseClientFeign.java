package com.sky.base.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.BaseInterface;
import com.sky.base.api.dto.WarehouseDto;
import com.sky.base.api.dto.WarehouseQueryDto;
import com.sky.base.api.model.Warehouse;
import com.sky.base.api.remote.WarehouseRemoteService;
import com.sky.framework.api.dto.ResponseDto;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(contextId = "FeignClient.WarehouseClientFeign", name = BaseInterface.SERVICE, fallbackFactory = WarehouseClientFeign.HystrixClientFallback.class)
public interface WarehouseClientFeign extends WarehouseRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<WarehouseClientFeign> {

        @Override
        public WarehouseClientFeign create(Throwable throwable) {
            return new WarehouseClientFeign() {

                @Override
                public ResponseDto<Warehouse> get(Long id) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Warehouse> getByCode(String code) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> create(WarehouseDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> update(WarehouseDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> delete(WarehouseDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<IPage<Warehouse>> page(WarehouseQueryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<Warehouse>> list(WarehouseDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
