package cn.com.sheep.week05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name = "hello03")
    public Hello getInstance(){
        Hello hello = new Hello(123);
        return hello;
    }
}
