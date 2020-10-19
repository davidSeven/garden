package com.forest.system.service.fallback;

import com.forest.framework.dto.ResponseDto;
import com.forest.system.dto.UserDto;
import com.forest.system.service.feign.TokenFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @date 2020-10-19 019 15:03
 */
@Component
public class TokenFeignServiceFallback implements FallbackFactory<TokenFeignService> {

    @Override
    public TokenFeignService create(Throwable throwable) {
        return new TokenFeignService() {
            @Override
            public ResponseDto<UserDto> valid(String token) {
                return null;
            }
        };
    }
}
