package com.sys.lunasysmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is description.
 *
 * @author wangfangrui
 * @date 2019/8/29 15:40
 */
@Configuration
@EnableSwagger2
public class Swagger2 {


    /**
     * 包路径
     */
    private final String SWAGGER2_API_BASEPACKAGE = "com.sys.lunasysmanagement.controller";

    /**
     * 页面标题
     */
    private final String SWAGGER2_API_TITLE = "智能合约开发者平台管理系统接口";

    /**
     * 描述
     */
    private final String SWAGGER2_API_DESCRIPTION = "用于提供用户管理相关接口";

    /**
     * 创建人
     */
    private final String SWAGGER2_API_CONTACT = "Ray";

    /**
     * 版本
     */
    private final String SWAGGER2_API_VERSION = "1.0";

    /**
     * createRestApi
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER2_API_BASEPACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * apiInfo
     *
     * @return
     */
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title(SWAGGER2_API_TITLE)
                .description(SWAGGER2_API_DESCRIPTION)
                .contact(SWAGGER2_API_CONTACT)
                .version(SWAGGER2_API_VERSION)
                .build();
    }
}
