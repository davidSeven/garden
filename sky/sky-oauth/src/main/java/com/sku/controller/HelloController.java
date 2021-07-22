package com.sku.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyuyuan
 * @date 2021-07-22 16:28
 */
@RestController
public class HelloController {

    @GetMapping("/echo")
    public String echo() {
        return "echo ... ";
    }
}
