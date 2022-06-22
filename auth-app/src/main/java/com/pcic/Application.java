package com.pcic;

import com.sinosoft.cloud.common.security.annotation.EnableScSecuritySingle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Auth鉴权中心
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/10/27 11:32
 */
@SpringBootApplication
@EnableScSecuritySingle
@EnableFeignClients(basePackages = {"com.pcic.feign"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
