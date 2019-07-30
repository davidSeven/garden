package com.stream.garden.framework.web.filter.event;

import java.io.Serializable;
import java.util.Map;

/**
 * @author garden
 * @date 2019-07-30 11:40
 */
public class EventObject implements Serializable {
    private static final long serialVersionUID = -7820696154067981668L;

    private Map<String, Object> objectMap = null;
    private Object object = null;

    public EventObject(Map<String, Object> objectMap, Object object) {
        this.objectMap = objectMap;
        this.object = object;
    }

    public EventObject(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }

    public EventObject(Object object) {
        this.object = object;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
