package com.example.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Mid
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:41
 */
@FeignClient(value = "mid")
@RequestMapping("/mid")
public interface ApiService {

    /**
     * msg
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 13:53
     */
    @GetMapping("/msg")
    String msg();

}
