package com.sky.system.pattern.creation.factory;

/**
 * @date 2020-12-21 021 9:08
 */
public class BenzCar implements Car, CreateFactory {

    @Override
    public String type() {
        return "奔驰";
    }

    @Override
    public Car create() {
        return new BenzCar();
    }
}
