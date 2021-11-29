package com.pcic.api.feign;

import org.springframework.web.bind.annotation.RestController;

/**
 * Mid
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:54
 */
@RestController
public class ApiServiceImpl implements ApiService {

    @Override
    public String msg() {
        return "message";
    }

}
