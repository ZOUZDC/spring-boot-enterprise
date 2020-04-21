package zdc.enterprise;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan({"zdc.enterprise.*"})
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("zdc.enterprise.mapper") //mapper的接口包路径
public class SpringBootEnterpriseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnterpriseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("启动成功");
        }
    }
}
