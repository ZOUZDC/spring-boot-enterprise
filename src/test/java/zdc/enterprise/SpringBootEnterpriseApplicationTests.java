package zdc.enterprise;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//@SpringBootTest
class SpringBootEnterpriseApplicationTests {

    @Test
    void contextLoads() {


        LocalDateTime now = LocalDateTime.now();
        //获取秒数
        Long second = now.toEpochSecond(ZoneOffset.of("+8"));
//获取毫秒数
        Long milliSecond = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();


        System.out.println(new Date(second));
        System.out.println(new Date(milliSecond));


    }

}
