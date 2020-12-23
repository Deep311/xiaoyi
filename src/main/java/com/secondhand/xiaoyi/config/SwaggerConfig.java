package com.secondhand.xiaoyi.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/3 21:57
 * @Version 1.0
 */
@Configuration //配置类
@EnableSwagger2 //swagger注解
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-校易网API文档")
                .description("本文档描述了校易网二手交易平台接口定义")
                .version("1.0")
                .contact(new Contact("Gaosl", "https://www.cnblogs.com/gaolight/", "18010958619@163.com"))
                .build();
    }


}
