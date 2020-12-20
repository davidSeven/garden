package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:35
 */
public class SingletonOne {
    private static final SingletonOne instance = new SingletonOne();

    private SingletonOne() {
        try {
            // 模拟在创建对象之前做一些准备工作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static SingletonOne getInstance() {
        return instance;
    }

}