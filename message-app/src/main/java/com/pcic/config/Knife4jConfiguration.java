package com.pcic.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ADAPT
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/23 9:27
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class Knife4jConfiguration {

    /**
     * knife4jApi
     *
     * @param
     * @return springfox.documentation.spring.web.plugins.Docket
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 11:59
     */
    @Bean("knife4jApi")
    public Docket knife4jApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Mid平台系统Api接口")
                        .description("Mid平台系统Api对外接口")
                        // .termsOfServiceUrl("http://www.xx.com/")
                        // .contact(new Contact("作者", "地址", "邮箱或联系方式"))
                        .version("1.0")
                        .build())
                .groupName("Api接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pcic.api"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * knife4jWebApi
     *
     * @param
     * @return springfox.documentation.spring.web.plugins.Docket
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 11:59
     */
    @Bean("knife4jWebApi")
    public Docket knife4jWebApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Mid平台系统Web接口")
                        .description("Mid平台系统Web前端接口")
                        // .termsOfServiceUrl("http://www.xx.com/")
                        // .contact(new Contact("作者", "地址", "邮箱或联系方式"))
                        .version("1.0")
                        .build())
                .groupName("Web接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pcic.web"))
                .paths(PathSelectors.any())
                .build();
    }

}
