package com.sky.system.pattern.creation.factory;

/**
 * @date 2020-12-21 021 9:09
 */
public class AudiCar implements Car, CreateFactory {

    @Override
    public String type() {
        return "奥迪";
    }

    @Override
    public Car create() {
        return new AudiCar();
    }
}
