package com.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zhangyuyuan
 * @date 2021-07-29 11:25
 */
public class TestBCryptPasswordEncoder {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("123456"));
    }
}
