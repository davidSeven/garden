package com.sky.system.pattern.creation.builder;

/**
 * @author garden
 * @date 2020/12/27 11:35
 */
public class ChineseBuilder extends AbstractBuilder {

    private House chineseHouse = new House();

    public void buildDoor() {
        chineseHouse.add("ChineseDoor");
    }

    public void buildWall() {
        chineseHouse.add("ChineseWall");
    }

    public void buildWindows() {
        chineseHouse.add("ChineseWindows");
    }

    public void buildHouseCeiling() {
        chineseHouse.add("ChineseHouserCeiling");
    }

    public House getHouse() {
        return chineseHouse;
    }
}
