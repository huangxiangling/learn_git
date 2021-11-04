package cn.com.sheep.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("cn.com.sheep.**.dao")
@Configuration
public class MybatisConfig {
}
