package zdc.enterprise;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@ComponentScan({"zdc.enterprise.*"})
@EnableTransactionManagement
//因为freemark是用来生成模版的不需要自动配置
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.class})
@MapperScan("zdc.enterprise.mapper") //mapper的接口包路径
@Slf4j
public class SpringBootEnterpriseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnterpriseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 5; i++) {
            log.info("启动成功");
        }
        log.info("当前数据库连接池为{}",dataSource.getClass());
    }

    @Autowired
    private DataSource dataSource;
}
