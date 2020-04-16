package zdc.enterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class MySwagger2Config {


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger文档标题")
                .description("swagger描述")
                //.termsOfServiceUrl("www.baidu.com")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("第一分组")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("zdc.enterprise.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /*如果想分组,可以写多个bean ,如下*/
    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("第二分组")
                .apiInfo(apiInfo())
                .select()
                /*因为我这里controller没有分组,也没有分组规则,所以就写一样的了,正常来basepackage应该不一样*/
                .apis(RequestHandlerSelectors.basePackage("zdc.enterprise.controller"))
                .paths(PathSelectors.any())
                .build();
    }



}
