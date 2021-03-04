package com.sky.system.pattern.creation.builder;

import org.junit.Test;

/**
 * @author garden
 * @date 2020/12/27 11:36
 */
public class TestBuilderPattern {

    @Test
    public void test() {
        AbstractBuilder chineseBuilder = new ChineseBuilder();
        AbstractBuilder englandBuilder = new EnglandBuilder();
        Director director = new Director();
        director.construct(chineseBuilder);
        House chineseHouse = chineseBuilder.getHouse();
        chineseHouse.show();
        director.construct(englandBuilder);
        House englandHouse = englandBuilder.getHouse();
        englandHouse.show();
    }
}
