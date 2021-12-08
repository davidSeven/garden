package com.sky.base.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.BaseInterface;
import com.sky.base.api.dto.ProductDto;
import com.sky.base.api.dto.ProductQueryDto;
import com.sky.base.api.model.Product;
import com.sky.base.api.remote.ProductRemoteService;
import com.sky.framework.api.dto.ResponseDto;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(contextId = "FeignClient.ProductClientFeign", name = BaseInterface.SERVICE, fallbackFactory = ProductClientFeign.HystrixClientFallback.class)
public interface ProductClientFeign extends ProductRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<ProductClientFeign> {

        @Override
        public ProductClientFeign create(Throwable throwable) {
            return new ProductClientFeign() {

                @Override
                public ResponseDto<Product> get(Long id) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Product> getByCode(String code) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> create(ProductDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> update(ProductDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> delete(ProductDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<IPage<Product>> page(ProductQueryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<Product>> list(ProductDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
