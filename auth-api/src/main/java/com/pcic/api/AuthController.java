package com.pcic.api;

import com.pcic.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/19 14:46
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private HttpServletRequest request;

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
