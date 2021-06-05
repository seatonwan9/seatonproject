package com.xearth.sp.seatonproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangxudong
 * @date 2020/3/13 16:10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //apiInfo指定测试文档基本信息，这部分将在页面展示
                .apiInfo(apiInfo())
                .select()
//                 apis() 控制哪些接口暴露给swagger，
//                 RequestHandlerSelectors.any() 所有都暴露
//                 RequestHandlerSelectors.basePackage("com.info.*") 指定包位置
                .apis(RequestHandlerSelectors.basePackage("com.xearth.sp.seatonproject.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //基本信息，页面展示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Seaton的学习接口")
                .description("接口描述")
                //联系人实体类
                .contact(
                        new Contact("wangxudong", "", "")
                )
                //版本号
                .version("1.0.0-SNAPSHOT")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")
                .addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
