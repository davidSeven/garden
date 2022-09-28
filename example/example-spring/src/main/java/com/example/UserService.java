package com.example;

import org.springframework.stereotype.Component;

@Component
public class UserService implements UserInterface {

    @Override
    public void test() {
        System.out.println("test...");
    }

    public void test(String s) {
        System.out.println("test args...");
        throw new NullPointerException();
    }
}
