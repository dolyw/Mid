package com.pcic.api.feign;

import com.pcic.api.feign.fallback.XxxFallbackFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * XxxFeignService
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:41
 */
@Tag(name = "XXX服务")
@FeignClient(value = "${ucenter.api.name:ucenter}", fallbackFactory = XxxFallbackFactory.class)
@RequestMapping("/xxx")
public interface XxxFeignService {

    /**
     * send
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 13:53
     */
    @Operation(summary = "XXX服务-XXX处理", description = "接口描述")
    @PostMapping("/handle")
    String handle();

}
