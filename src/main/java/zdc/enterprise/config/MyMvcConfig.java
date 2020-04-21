package zdc.enterprise.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zdc.enterprise.config.formatter.DateFormatter;
import zdc.enterprise.config.formatter.LocalDateFormatter;
import zdc.enterprise.config.formatter.LocalDateTimeFormatter;
import zdc.enterprise.config.formatter.LocalTimeFormatter;
import zdc.enterprise.constants.MyHandlerInterceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    //使用此方式注入interception 可以保证该类中调用的Autowired对象的使用
    @Bean
    public MyHandlerInterceptor getLoginHandlerInterceptor() {
        return new MyHandlerInterceptor();
    }


    /***
     * 跨域处理 如果有图片上传canvas显示之类的功能的话,最好在tomcat中做跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*").exposedHeaders();
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

        config.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,        // 是否输出值为null的字段,默认为false,我们将它打开
                SerializerFeature.WriteNullListAsEmpty,     // 将Collection类型字段的字段空值输出为[]
                SerializerFeature.WriteNullStringAsEmpty,   // 将字符串类型字段的空值输出为空字符串
                //SerializerFeature.WriteNullNumberAsZero,    // 将数值类型字段的空值输出为0
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect    // 禁用循环引用
        );
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");


        //解决中文乱码
        config.setCharset(Charset.forName("UTF-8"));

        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

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