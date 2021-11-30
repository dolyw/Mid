package com.pcic.api.feign;

import org.springframework.web.bind.annotation.RestController;

/**
 * AuthServiceImpl
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:54
 */
@RestController
public class AuthServiceImpl implements AuthService {

    @Override
    public String auth() {
        return "OK";
    }
}
