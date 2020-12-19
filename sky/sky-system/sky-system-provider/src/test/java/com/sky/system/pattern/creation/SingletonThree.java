package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:35
 */
public class SingletonThree implements SingletonInterFace {
    private volatile static SingletonThree instance;

    private SingletonThree() {
    }

    public static SingletonThree getInstance() {
        if (null == instance) {
            synchronized (SingletonThree.class) {
                if (null == instance) {
                    instance = new SingletonThree();
                }
            }
        }
        return instance;
    }

    @Override
    public SingletonInterFace newInstance() {
        return getInstance();
    }
}