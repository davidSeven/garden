package com.sky.system.pattern.creation.factory;

/**
 * @date 2020-12-21 021 9:10
 */
public class BeanFactory {

    public Car createCar(String type) {
        switch (type) {
            case "benz":
                return new BenzCar();
            case "audi":
                return new AudiCar();
            default:
                break;
        }
        return null;
    }
}
