package com.pcic.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ApiController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/19 14:46
 */
@ApiIgnore
@Api(tags = "OpenApi示例", description = "OpenApi示例")
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(ApiController.class);

    /**
     * msg
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 11:48
     */
    @ApiOperation(value = "OpenApi示例-展示", notes = "OpenApi示例-展示")
    @GetMapping("/msg")
    public String msg() {
        return "OpenApi";
    }

}
