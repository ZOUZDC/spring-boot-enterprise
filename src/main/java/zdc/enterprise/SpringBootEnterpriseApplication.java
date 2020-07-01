package zdc.enterprise;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootEnterpriseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnterpriseApplication.class, args);

        for (int i = 0; i < 1000; i++) {
            log.info("HELLO");
        }
    }

}
