package com.sky.system.pattern.creation.builder;

/**
 * @author garden
 * @date 2020/12/27 11:33
 */
public abstract class AbstractBuilder {

    public abstract void buildDoor();

    public abstract void buildWall();

    public abstract void buildWindows();

    public abstract void buildHouseCeiling();

    public abstract House getHouse();
}
