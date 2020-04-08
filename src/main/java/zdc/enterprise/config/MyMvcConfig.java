package zdc.enterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.MyHandlerInterceptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

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
        registry.addFormatter(new Formatter<LocalDate>(){
            @Override
            public String print(LocalDate localDate, Locale locale) {
                return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            @Override
            public LocalDate parse(String s, Locale locale) {
                if(StringUtils.isEmpty(s)){
                    return null;
                }
                return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        });
        //LocalDateTime
        registry.addFormatter(new Formatter<LocalDateTime>(){
            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
            @Override
            public LocalDateTime parse(String s, Locale locale) {
                if(StringUtils.isEmpty(s)){
                    return null;
                }
                return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        });
        //LocalTime
        registry.addFormatter(new Formatter<LocalTime>(){
            @Override
            public String print(LocalTime localTime, Locale locale) {
                return localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
            @Override
            public LocalTime parse(String s, Locale locale) {
                if(StringUtils.isEmpty(s)){
                    return null;
                }
                return LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        });
        //Date
        registry.addFormatter(new Formatter<Date>(){
            //年月日
            String pattern_date ="^[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}$";
            //年月日时分秒
            String pattern_datetime ="^[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}[ ][\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}$";
            //秒
            String pattern_second ="^[\\d]{10}$";
            //毫秒
            String pattern_millsecond ="^[\\d]{10}$";

            SimpleDateFormat  sdf_date =new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat  sdf_datetime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            @Override
            public Date parse(String s, Locale locale) throws ParseException {

                if(StringUtils.isEmpty(s)){
                    return null;
                }

                if (Pattern.matches(pattern_date,s)) {
                    return sdf_date.parse(s);
                }
                if (Pattern.matches(pattern_datetime,s)) {
                    return sdf_datetime.parse(s);
                }
                if (Pattern.matches(pattern_second,s)) {
                    return new Date(Long.parseLong(s)*1000);
                }
                if (Pattern.matches(pattern_millsecond,s)) {
                    return new Date(Long.parseLong(s));
                }
                throw  new CustomException("未能识别的日期格式");
            }

            @Override
            public String print(Date date, Locale locale) {
                return sdf_datetime.format(date);
            }
        });
    }
}