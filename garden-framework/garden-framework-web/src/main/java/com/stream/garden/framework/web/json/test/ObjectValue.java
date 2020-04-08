package com.stream.garden.framework.web.json.test;

import java.io.Serializable;

/**
 * @author garden
 * @date 2020-04-07 19:59
 */
public class ObjectValue implements Serializable {

    private Integer id;
    private String name;

    public ObjectValue(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
