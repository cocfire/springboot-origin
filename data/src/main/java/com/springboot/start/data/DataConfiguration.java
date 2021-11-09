package com.springboot.start.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author FireWang
 * @date 2021/11/08
 */
@ComponentScan
@Configuration
@MapperScan("com.springboot.start.data.mapper")
public class DataConfiguration {

}
