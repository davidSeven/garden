package com.stream.garden.system.group.model;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-06-22 11:03
 */
public class GroupUser implements Serializable {
    private static final long serialVersionUID = 5142173405432294933L;

    private String groupId;
    private String userId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
