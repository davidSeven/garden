package com.stream.garden.framework.web.interceptor;

import java.util.Set;

/**
 * @author garden
 * @date 2020-04-08 10:40
 */
public class FieldSerializer {

    private Set<String> handlerFieldSet;
    private Set<String> sensitiveFieldSet;

    public FieldSerializer() {
    }

    public FieldSerializer(Set<String> handlerFieldSet, Set<String> sensitiveFieldSet) {
        this.handlerFieldSet = handlerFieldSet;
        this.sensitiveFieldSet = sensitiveFieldSet;
    }

    public Set<String> getHandlerFieldSet() {
        return handlerFieldSet;
    }

    public void setHandlerFieldSet(Set<String> handlerFieldSet) {
        this.handlerFieldSet = handlerFieldSet;
    }

    public Set<String> getSensitiveFieldSet() {
        return sensitiveFieldSet;
    }

    public void setSensitiveFieldSet(Set<String> sensitiveFieldSet) {
        this.sensitiveFieldSet = sensitiveFieldSet;
    }
}
