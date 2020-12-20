package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:35
 */
public class SingletonFour {

    private SingletonFour() {
    }

    public static SingletonFour getInstance() {
        return SingletonFourInstance.instance;
    }

    private static class SingletonFourInstance {
        private static final SingletonFour instance = new SingletonFour();
    }

}