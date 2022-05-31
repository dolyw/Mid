package com.pcic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4jConfiguration
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/23 9:27
 */
@Configuration
public class Knife4jConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * groupedWebApi接口分组
     *
     * @return
     */
    @Bean
    public GroupedOpenApi groupedWebApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"com.pcic.web"};
        return GroupedOpenApi.builder().group(applicationName + "系统Web接口")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    /**
     * groupedOpenApi接口分组
     *
     * @return
     */
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"com.pcic.api"};
        return GroupedOpenApi.builder().group(applicationName + "系统Api接口")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    /**
     * openApi信息
     *
     * @return
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info().title(applicationName + "系统接口文档").version("1.0"));
    }

}
