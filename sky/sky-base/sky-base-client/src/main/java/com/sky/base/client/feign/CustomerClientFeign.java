package com.sky.base.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.BaseInterface;
import com.sky.base.api.dto.CustomerDto;
import com.sky.base.api.dto.CustomerQueryDto;
import com.sky.base.api.model.Customer;
import com.sky.base.api.remote.CustomerRemoteService;
import com.sky.framework.api.dto.ResponseDto;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(contextId = "FeignClient.CustomerClientFeign", name = BaseInterface.SERVICE, fallbackFactory = CustomerClientFeign.HystrixClientFallback.class)
public interface CustomerClientFeign extends CustomerRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<CustomerClientFeign> {

        @Override
        public CustomerClientFeign create(Throwable throwable) {
            return new CustomerClientFeign() {

                @Override
                public ResponseDto<Customer> get(Long id) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Customer> getByCode(String code) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> create(CustomerDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> update(CustomerDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Boolean> delete(CustomerDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<IPage<Customer>> page(CustomerQueryDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<List<Customer>> list(CustomerDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
