package com.pcic.api.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * XxxFeignServiceImpl
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:54
 */
@Slf4j
@RestController
public class XxxFeignServiceImpl implements XxxFeignService {

    @Override
    public String handle() {
        return "OK";
    }
}
