package com.sky.system.pattern.creation.builder;

/**
 * @author garden
 * @date 2020/12/27 11:36
 */
public class EnglandBuilder extends AbstractBuilder {

    private House englandHouse = new House();

    public void buildDoor() {
        englandHouse.add("EnglandDoor");
    }

    public void buildWall() {
        englandHouse.add("EnglandWall");
    }

    public void buildWindows() {
        englandHouse.add("EnglandWindows");
    }

    public void buildHouseCeiling() {
        englandHouse.add("EnglandHouserCeiling");
    }

    public House getHouse() {
        return englandHouse;
    }
}
