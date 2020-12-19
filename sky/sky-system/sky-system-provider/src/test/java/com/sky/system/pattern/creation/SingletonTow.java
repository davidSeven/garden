package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:35
 */
public class SingletonTow implements SingletonInterFace {
    private static SingletonTow instance;

    private SingletonTow() {
    }

    public static synchronized SingletonTow getInstance() {
        if (null == instance) {
            instance = new SingletonTow();
        }
        return instance;
    }

    @Override
    public SingletonInterFace newInstance() {
        return getInstance();
    }
}