package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:54
 */
public class SingletonThread implements Runnable {

    private Class<? extends SingletonInterFace> singletonInterFace;

    public SingletonThread(Class<? extends SingletonInterFace> singletonInterFace) {
        this.singletonInterFace = singletonInterFace;
    }

    @Override
    public void run() {
        System.out.println();
    }
}
