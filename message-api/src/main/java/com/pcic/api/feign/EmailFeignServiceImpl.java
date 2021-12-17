package com.pcic.api.feign;

import org.springframework.web.bind.annotation.RestController;

/**
 * EmailServiceImpl
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:54
 */
@RestController
public class EmailFeignServiceImpl implements EmailFeignService {

    @Override
    public String send() {
        return "OK";
    }
}
