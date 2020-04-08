package com.stream.garden.framework.web.interceptor.test;

/**
 * @author garden
 * @date 2020-04-08 20:15
 */
public class Test {

    public static void main(String[] args) {

        Object b = new B();
        Object c = null;

        System.out.println(b instanceof B);
        System.out.println(b instanceof A);
        System.out.println(c instanceof A);
    }
}
