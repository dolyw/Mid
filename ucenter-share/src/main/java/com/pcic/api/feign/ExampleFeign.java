package com.pcic.api.feign;

import com.pcic.api.feign.fallback.ExampleFallbackFactory;
import com.pcic.api.req.FeignReq;
import com.pcic.api.resp.FeignResp;
import com.pcic.core.common.dto.RequestMessage;
import com.pcic.core.common.dto.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ExampleFeign
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:41
 */
@Tag(name = "示例服务")
@FeignClient(value = "${ucenter.api.name:ucenter}", fallbackFactory = ExampleFallbackFactory.class)
@RequestMapping("/example")
public interface ExampleFeign {

    /**
     * 示例服务-XXX处理
     *
     * @param requestMessage
     * @return com.pcic.core.common.dto.ResponseMessage<com.pcic.api.resp.FeignResp>
     * @throws Exception e
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 13:53
     */
    @Operation(summary = "示例服务-XXX处理", description = "接口描述")
    @PostMapping("/handle")
    ResponseMessage<FeignResp> handle(@RequestBody RequestMessage<FeignReq> requestMessage) throws Exception;

    /**
     * 示例服务-Redis示例
     *
     * @param
     * @return com.pcic.core.common.dto.ResponseMessage
     * @throws Exception e
     * @author wliduo[i@dolyw.com]
     * @date 2022/5/31 19:05
     */
    @Operation(summary = "示例服务-Redis示例", description = "接口描述")
    @PostMapping("/info")
    ResponseMessage info() throws Exception;
}
