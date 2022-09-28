package com.sky.system.client.feign;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.AccessDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.dto.VerificationDto;
import com.sky.system.api.remote.AuthenticationRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @date 2020-12-15 015 13:36
 */
@FeignClient(contextId = "FeignClient.AuthenticationFeign", name = SystemInterface.SERVICE, fallbackFactory = AuthenticationFeign.HystrixClientFallback.class)
public interface AuthenticationFeign extends AuthenticationRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<AuthenticationFeign> {

        @Override
        public AuthenticationFeign create(Throwable throwable) {
            return new AuthenticationFeign() {
                @Override
                public ResponseDto<UserLoginDto> verification(VerificationDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }

                @Override
                public ResponseDto<Void> access(AccessDto dto) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
