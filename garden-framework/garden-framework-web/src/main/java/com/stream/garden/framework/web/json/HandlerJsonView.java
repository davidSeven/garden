package com.stream.garden.framework.web.json;

/**
 * @author garden
 * @date 2020-04-07 19:49
 */
public class HandlerJsonView<T> extends JsonView<T> {

    private HandlerJsonViewFilter handlerJsonViewFilter;

    public HandlerJsonView(T value) {
        super(value);
    }

    public HandlerJsonViewFilter getHandlerJsonViewFilter() {
        return handlerJsonViewFilter;
    }

    public void setHandlerJsonViewFilter(HandlerJsonViewFilter handlerJsonViewFilter) {
        this.handlerJsonViewFilter = handlerJsonViewFilter;
    }
}
