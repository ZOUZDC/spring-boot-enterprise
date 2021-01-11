package zdc.enterprise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootEnterpriseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnterpriseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            log.info("nihao");
        }
    }
}
