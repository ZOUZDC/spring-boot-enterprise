package zdc.enterprise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"zdc.enterprise.*"})
@SpringBootApplication
public class SpringBootEnterpriseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnterpriseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("启动成功");
        System.out.println("启动成功");
        System.out.println("启动成功");
        System.out.println("启动成功");
    }
}
