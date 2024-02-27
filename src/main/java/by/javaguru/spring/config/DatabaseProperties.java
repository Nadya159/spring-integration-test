package by.javaguru.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "db")
public record DatabaseProperties(String url,
                                 String username,
                                 String password,
                                 String driver,
                                 PoolProperties pool) {


    public record PoolProperties(Integer size,
                                        Integer timeout) {

    }
}
