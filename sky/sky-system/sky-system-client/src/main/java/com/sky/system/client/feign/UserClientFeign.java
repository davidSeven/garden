package com.sky.system.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import com.sky.system.api.remote.UserRemoteService;
import com.sky.system.api.SystemInterface;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @date 2020-10-28 028 14:53
 */
@FeignClient(name = SystemInterface.SERVICE, fallback = UserClientFeign.HystrixClientFallback.class)
public interface UserClientFeign extends UserRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<UserClientFeign> {

        @Override
        public UserClientFeign create(Throwable throwable) {
            return new UserClientFeign() {
                @Override
                public ResponseDto<UserDto> get(UserDto dto) {
                    return null;
                }

                @Override
                public ResponseDto<Boolean> save(UserDto dto) {
                    return null;
                }

                @Override
                public ResponseDto<Boolean> update(UserDto dto) {
                    return null;
                }

                @Override
                public ResponseDto<IPage<UserDto>> pageList(UserQueryDto queryDto) {
                    return null;
                }
            };
        }
    }
}
