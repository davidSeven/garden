package com.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @author zhangyuyuan
 * @date 2021-07-29 11:25
 */
public class TestBCryptPasswordEncoder {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String s = UUID.randomUUID().toString().replaceAll("-", "");

        System.out.println(s);

        System.out.println(passwordEncoder.encode(s));
    }
}
