package com.sky.system.pattern.creation;

/**
 * @date 2020-12-19 019 16:54
 */
public class SingletonThread extends Thread {

    public SingletonThread() {

    }

    @Override
    public void run() {
        System.out.println(SingletonTow.getInstance().hashCode());
    }
}
