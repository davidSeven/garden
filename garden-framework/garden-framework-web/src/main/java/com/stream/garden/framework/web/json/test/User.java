package com.stream.garden.framework.web.json.test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author garden
 * @date 2020-04-07 19:58
 */
public class User implements Serializable {

    private static final long serialVersionUID = -627151961491446653L;
    private Integer id;
    private String name;
    private Boolean gender;
    private String email;
    private Date validDate;

    private ObjectValue objectValue;
    private List<ObjectValue> objectValueList;

    private boolean success;

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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public ObjectValue getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(ObjectValue objectValue) {
        this.objectValue = objectValue;
    }

    public List<ObjectValue> getObjectValueList() {
        return objectValueList;
    }

    public void setObjectValueList(List<ObjectValue> objectValueList) {
        this.objectValueList = objectValueList;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return null != this.id;
    }
}
