package by.javaguru.spring.config;

import by.javaguru.spring.utils.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

import java.sql.Connection;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @Profile("prod")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public Connection connectionPoolProd(@Value("${db.url}") String url,
                                         @Value("${db.username}") String username,
                                         @Value("${db.pool.size}") Integer poolSize,
                                         @Value("${db.password}") String password) {
        return ConnectionPool.getConnection(url, username, poolSize, password);
    }
}
