package com.springboot.start.api;


import com.springboot.start.core.CoreConfiguration;
import com.springboot.start.data.DataConfiguration;
import com.springboot.start.utils.UtilsConfiguration;
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