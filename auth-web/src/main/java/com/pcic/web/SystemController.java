package com.pcic.web;

import com.pcic.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/19 14:46
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private BusinessService businessService;

    /**
     * msg
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 11:48
     */
    @GetMapping("/msg")
    public String msg() {
        return businessService.handle();
    }

}
