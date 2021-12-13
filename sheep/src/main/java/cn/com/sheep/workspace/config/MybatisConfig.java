package cn.com.sheep.workspace.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"cn.com.sheep.workspace.dao"})
public class MybatisConfig {

}
