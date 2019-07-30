package com.stream.garden.framework.web.filter.event;

import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gardne
 * @date 2019-07-30 11:39
 */
public class RequestFilterHandlerEvent extends ApplicationEvent {

    public static final String URI = "uri";
    public static final String TIMES = "times";
    private static final long serialVersionUID = 7827558088590324497L;
    private EventObject eventObject;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public RequestFilterHandlerEvent(Object source, EventObject eventObject) {
        super(source);
        this.eventObject = eventObject;
    }

    public RequestFilterHandlerEvent(Object source, String uri, long times) {
        this(source, new EventObject(initObjectMap(uri, times)));
    }

    private static Map<String, Object> initObjectMap(String uri, long times) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put(URI, uri);
        objectMap.put(TIMES, times);
        return objectMap;
    }

    public EventObject getEventObject() {
        return eventObject;
    }

    public void setEventObject(EventObject eventObject) {
        this.eventObject = eventObject;
    }
}
