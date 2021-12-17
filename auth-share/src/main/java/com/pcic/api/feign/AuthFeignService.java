package com.pcic.api.feign;

import com.pcic.api.feign.fallback.AuthFallbackFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Auth服务
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:41
 */
@Api(tags = "Auth服务", description = "Auth服务")
@FeignClient(value = "${auth.api.serviceName:auth}", fallbackFactory = AuthFallbackFactory.class)
@RequestMapping("/auth")
public interface AuthFeignService {

    /**
     * 获取Token
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 13:53
     */
    @ApiOperation(value = "Auth服务-获取Token", notes = "Auth服务-获取Token")
    @PostMapping("/token")
    String token();

}
