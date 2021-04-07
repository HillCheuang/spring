package huwei;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 09:31
 */
@Configuration
@ComponentScan(basePackages = {"huwei","mimi"})
public class AppConfig {

    @Bean
    public Random r(){
        return new Random();
    }
}
