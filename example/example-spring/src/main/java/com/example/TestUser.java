package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
@Configuration
@EnableAspectJAutoProxy
public class TestUser {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext(TestUser.class);
        // applicationContext.refresh();
        UserService userService = applicationContext.getBean(UserService.class);
        UserInterface userInterface = (UserInterface) userService;
        userInterface.test();
    }
}
