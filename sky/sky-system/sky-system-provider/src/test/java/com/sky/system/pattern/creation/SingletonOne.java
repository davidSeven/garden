package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:35
 */
public class SingletonOne implements SingletonInterFace {
    private static final SingletonOne instance = new SingletonOne();

    private SingletonOne() {
    }

    public static SingletonOne getInstance() {
        return instance;
    }

    @Override
    public SingletonInterFace newInstance() {
        return getInstance();
    }
}