package com.pcic.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * IndexController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 16:46
 */
@ApiIgnore
@Api(tags = "应用首页", description = "应用首页")
@RestController
@RequestMapping("/")
public class IndexController {

    @Value("${spring.application.name}")
    private String applicationName;

    @ApiOperation(value = "应用首页-展示", notes = "应用首页-展示")
    @GetMapping("/")
    public String index() {
        return "Hello World " + applicationName;
    }

}
