package com.sky.system.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import com.sky.system.api.remote.UserRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @date 2020-10-28 028 14:53
 */
@FeignClient(contextId = "FeignClient.UserClientFeign", name = SystemInterface.SERVICE, fallbackFactory = UserClientFeign.HystrixClientFallback.class)
public interface UserClientFeign extends UserRemoteService {

    @Component
    class HystrixClientFallback implements FallbackFactory<UserClientFeign> {

        @Override
        public UserClientFeign create(Throwable throwable) {
            return new UserClientFeign() {
                @Override
                public ResponseDto<UserDto> get(UserDto dto, String code, Long id) {
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

                @Override
                public ResponseDto<String> getNameByCode(String code) {
                    return ResponseDto.convertResultJson(throwable);
                }
            };
        }
    }
}
