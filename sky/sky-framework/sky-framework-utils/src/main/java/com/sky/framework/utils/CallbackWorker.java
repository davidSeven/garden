package com.sky.framework.utils;

/**
 * @author garden
 * @date 2021/11/20 22:39
 */
@FunctionalInterface
public interface CallbackWorker<E> {

    E execute();
}
