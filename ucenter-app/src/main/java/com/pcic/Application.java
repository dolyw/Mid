package com.pcic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Message消息中心
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/10/27 11:32
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.pcic.feign"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
