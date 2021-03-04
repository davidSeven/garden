package com.sky.system.pattern.creation.builder;

/**
 * @author garden
 * @date 2020/12/27 11:34
 */
public class Director {

    public void construct(AbstractBuilder builder) {
        builder.buildDoor();
        builder.buildWall();
        builder.buildWindows();
        builder.buildHouseCeiling();
    }
}
