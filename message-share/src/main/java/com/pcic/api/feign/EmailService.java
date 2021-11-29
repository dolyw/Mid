package com.pcic.api.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * EmailService
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:41
 */
@Api(tags = "Email服务", description = "Email服务")
@FeignClient(value = "${message.api.serviceName:message}")
@RequestMapping("/email")
public interface EmailService {

    /**
     * send
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 13:53
     */
    @ApiOperation(value = "Email服务-发送Email", notes = "Email服务-发送Email")
    @PostMapping("/send")
    String send();

}
