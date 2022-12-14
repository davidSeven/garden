package com.forest.system.service.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.forest.framework.dto.ResponseDto;
import com.forest.system.dto.UserDto;
import com.forest.system.model.User;
import com.forest.system.service.feign.UserFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @date 2020-09-22 022 15:21
 */
@Component
public class UserFeignServiceFallback implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable throwable) {
        return new UserFeignService() {
            @Override
            public ResponseDto<Boolean> save(UserDto dto) {
                return null;
            }

            @Override
            public ResponseDto<Boolean> update(UserDto dto) {
                return null;
            }

            @Override
            public ResponseDto<IPage<User>> page(UserDto dto) {
                return null;
            }
        };
    }
}
