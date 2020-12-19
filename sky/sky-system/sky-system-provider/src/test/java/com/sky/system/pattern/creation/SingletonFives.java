package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:35
 */
public enum SingletonFives implements SingletonInterFace {

    INSTANCE,

    ;

    public void singletonOperation() {

    }

    @Override
    public SingletonInterFace newInstance() {
        return SingletonFives.INSTANCE;
    }
}