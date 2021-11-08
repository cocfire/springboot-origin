package com.springboot.start.controller.test;

import com.springboot.start.service.HelloService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController
 * 定义为Restful风格的API控制器
 */
@RestController
@AllArgsConstructor
public class HelloController {

    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.sayhello();
    }
}