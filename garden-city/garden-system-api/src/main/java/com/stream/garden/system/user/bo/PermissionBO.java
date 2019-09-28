package com.stream.garden.system.user.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-09-28 10:15
 */
@Data
public class PermissionBO implements Serializable {
    private static final long serialVersionUID = 1858559024100210595L;

    private String value;
}
