package com.sku.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyuyuan
 * @date 2021-07-22 16:28
 */
@RestController
public class HelloController {

    @GetMapping("/echo")
    @PreAuthorize("hasAuthority('zs')")
    public String echo() {
        return "echo ... zs ";
    }

    @GetMapping("/echo2")
    @PreAuthorize("hasAuthority('ls')")
    public String echo2() {
        return "echo ... ls ";
    }

    @PostMapping("/callback")
    public String callback(@RequestParam("code") String code) {
        System.out.println(code);
        return code;
    }
}
