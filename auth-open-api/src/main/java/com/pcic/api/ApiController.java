package com.pcic.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/19 14:46
 */
@Tag(name = "OpenApi示例")
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * msg
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 11:48
     */
    @Operation(summary = "OpenApi示例-XXX接口", description = "接口描述")
    @GetMapping("/msg")
    public String msg() {
        return "OpenApi";
    }

}
