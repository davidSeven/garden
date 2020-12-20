package com.sky.system.pattern.creation;

import org.junit.Test;

/**
 * @date 2020-12-19 019 16:37
 */
public class TestSingleton {

    @Test
    public void test() {
//        testThread(SingletonOne.getInstance());
//        testThread(SingletonTow.getInstance());
//        testThread(SingletonThree.getInstance());
//        testThread(SingletonFour.getInstance());
//        testThread(SingletonFives.INSTANCE);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new SingletonThread();
            threads[i].start();
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
