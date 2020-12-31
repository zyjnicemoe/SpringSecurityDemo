package com.zyjblogs.springsecurity.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author zhuyijun
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * zhuyijun         2020/12/29 12:12               1.0
 * @program mydoc
 * @description
 * @create 2020/12/29 12:12
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
@ConditionalOnProperty(value = {"true"}, matchIfMissing = true)
public class Knife4jConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zyjblogs.springsecurity"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("zhuyijun", "http://localhost:9029/doc.html", "zhuyijun@hatech.com.cn");
        return new ApiInfoBuilder()
                .title("SpringBoot项目 后台服务API接口文档")
                .description("使用 knife4j 搭建的后台服务API接口文档")
                .termsOfServiceUrl("http://localhost:9029/")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
