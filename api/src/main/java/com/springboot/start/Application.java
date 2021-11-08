package com.springboot.start;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author FireWang
 * @date 2021/11/08
 * 启动类，也就是项目入口
 */
@SpringBootApplication
@Import({CoreConfiguration.class, DataConfiguration.class, UtilsConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}