package zdc.enterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zdc.enterprise.config.formatter.DateFormatter;
import zdc.enterprise.config.formatter.LocalDateFormatter;
import zdc.enterprise.config.formatter.LocalDateTimeFormatter;
import zdc.enterprise.config.formatter.LocalTimeFormatter;
import zdc.enterprise.constants.MyHandlerInterceptor;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    //使用此方式注入interception 可以保证该类中调用的Autowired对象的使用
    @Bean
    public MyHandlerInterceptor getLoginHandlerInterceptor() {
        return new MyHandlerInterceptor();
    }


    /***
     * 跨域处理
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }


    /***
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/error","/","/static/**");//不拦截的路径

    }

    /***
     * 时间格式入参类型转换 StringUtils建议使用apache的isBlank做判断
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //LocalDate 类型
        registry.addFormatter(new LocalDateFormatter());
        //LocalDateTime
        registry.addFormatter(new LocalDateTimeFormatter());
        //LocalTime
        registry.addFormatter(new LocalTimeFormatter());
        //Date
        registry.addFormatter(new DateFormatter());
    }
}