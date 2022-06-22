package com.pcic.web;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 16:46
 */
@Hidden
@Tag(name = "应用首页")
@RestController
@RequestMapping("/")
public class IndexController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Operation(summary = "应用首页", description = "应用首页-展示")
    @GetMapping("/")
    public String index() {
        return "Hello World " + applicationName;
    }

}
