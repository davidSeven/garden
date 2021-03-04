package com.sky.system.pattern.creation.builder;

import java.util.ArrayList;

/**
 * @author garden
 * @date 2020/12/27 11:34
 */
public class House {

    private ArrayList<String> parts = new ArrayList<>();

    public void add(String str) {
        parts.add(str);
    }

    public void show() {
        for (String part : parts) {
            System.out.println(part + "\t");
        }
        System.out.println("\n");
    }
}
