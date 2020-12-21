package com.sky.system.pattern.creation.singleton;

/**
 * @date 2020-12-19 019 16:35
 */
public class SingletonThree {
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

}