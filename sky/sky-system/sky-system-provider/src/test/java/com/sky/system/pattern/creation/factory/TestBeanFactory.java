package com.sky.system.pattern.creation.factory;

import org.junit.Test;

/**
 * @date 2020-12-21 021 9:11
 */
public class TestBeanFactory {

    @Test
    public void testBeanCreate() {
        // 创建工厂类
        CreateFactory benzCarFactory = new BenzCar();
        Car benzCar = benzCarFactory.create();
        System.out.println(benzCar.type());

        CreateFactory audiCarFactory = new AudiCar();
        Car audiCar = audiCarFactory.create();
        System.out.println(audiCar.type());
    }

    @Test
    public void testBeanFactory() {
        // 工厂类
        BeanFactory beanFactory = new BeanFactory();

        Car benzCar = beanFactory.createCar("benz");
        System.out.println(benzCar.type());

        Car audiCar = beanFactory.createCar("audi");
        System.out.println(audiCar.type());
    }
}
