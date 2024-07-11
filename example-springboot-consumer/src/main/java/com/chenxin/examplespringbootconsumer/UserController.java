package com.chenxin.examplespringbootconsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fangchenxin
 * @description
 * @date 2024/7/11 12:49
 * @modify
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private ExampleServiceImpl exampleService;

    @GetMapping("/name")
    public String getUserName() {
        return exampleService.test();
    }
}
