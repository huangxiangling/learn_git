package cn.com.sheep.config;

import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class QualityConfig {

    public QualityConfig(Map<String, Object> serviceMap){
        System.out.println("======== 打印config");
    }
}
