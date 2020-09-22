package com.forest.system.service;

import com.forest.system.ForestInterface;
import com.forest.system.service.fallback.UserFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @date 2020-09-22 022 15:17
 */
@FeignClient(name = ForestInterface.SERVICE_NAME, fallback = UserFeignServiceFallback.class)
public interface UserFeignService extends UserRemoteService {
}
