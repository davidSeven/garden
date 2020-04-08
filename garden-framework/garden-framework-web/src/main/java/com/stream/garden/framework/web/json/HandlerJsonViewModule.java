package com.stream.garden.framework.web.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author garden
 * @date 2020-04-07 20:01
 */
public class HandlerJsonViewModule extends SimpleModule {

    private static final long serialVersionUID = 1741077246505553071L;

    public HandlerJsonViewModule() {
        this(new HandlerJsonViewSerializer());
    }

    public HandlerJsonViewModule(HandlerJsonViewSerializer serializer) {
        super(new Version(0, 14, 0, "", "com.stream.garden.framework.web.json", "handler-json-view"));
        addSerializer(JsonView.class, serializer);
    }
}
