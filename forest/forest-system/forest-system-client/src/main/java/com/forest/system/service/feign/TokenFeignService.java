package com.forest.system.service.feign;

import com.forest.system.ForestInterface;
import com.forest.system.service.TokenRemoteService;
import com.forest.system.service.fallback.TokenFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @date 2020-10-19 019 15:01
 */
@FeignClient(name = ForestInterface.SERVICE_NAME, fallback = TokenFeignServiceFallback.class)
public interface TokenFeignService extends TokenRemoteService {
}
