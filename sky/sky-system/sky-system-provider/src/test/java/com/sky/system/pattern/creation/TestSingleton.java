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
    }

    public void testThread(Class<? extends SingletonInterFace> singletonInterFace) {
        System.out.println("--------------------");
        SingletonThread[] threads = new SingletonThread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new SingletonThread(singletonInterFace);
            threads[i].run();
        }
    }
}
