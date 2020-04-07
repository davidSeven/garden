package com.stream.garden.framework.web.json.test;

import java.util.Date;
import java.util.List;

/**
 * @author garden
 * @date 2020-04-07 19:58
 */
public class User {

    private Integer id;
    private String name;
    private Boolean gender;
    private String email;
    private Date validDate;

    private ObjectValue objectValue;
    private List<ObjectValue> objectValueList;

    public User() {
    }

    public User(Integer id, String name, Boolean gender, String email, Date validDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.validDate = validDate;
    }

    public User(Integer id, String name, Boolean gender, String email, Date validDate, ObjectValue objectValue, List<ObjectValue> objectValueList) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.validDate = validDate;
        this.objectValue = objectValue;
        this.objectValueList = objectValueList;
    }
}
