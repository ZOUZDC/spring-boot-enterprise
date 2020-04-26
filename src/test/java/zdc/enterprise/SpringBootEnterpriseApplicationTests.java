package zdc.enterprise;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

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
