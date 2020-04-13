package zdc.enterprise.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zdc.enterprise.config.Formatter.DateFormatter;
import zdc.enterprise.config.Formatter.LocalDateFormatter;
import zdc.enterprise.config.Formatter.LocalDateTimeFormatter;
import zdc.enterprise.config.Formatter.LocalTimeFormatter;
import zdc.enterprise.constants.MyHandlerInterceptor;

import java.nio.charset.Charset;
import java.util.List;

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
     * 返回数据使用fastjson序列化一下
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = fastJsonHttpMessageConverter.getFastJsonConfig();
        config.setCharset(Charset.forName("UTF-8"));
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converters.add(0,fastJsonHttpMessageConverter);
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