package aj.redis.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RedisProjectSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisProjectSpringApplication.class, args);
    }
}
