package com.pcic.api.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AuthService
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:41
 */
@Api(tags = "Auth服务", description = "Auth服务")
@FeignClient(value = "${auth.api.serviceName:auth}")
@RequestMapping("/auth")
public interface AuthService {

    /**
     * send
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 13:53
     */
    @ApiOperation(value = "Auth服务-鉴权", notes = "Auth服务-鉴权")
    @PostMapping("/auth")
    String auth();

}
