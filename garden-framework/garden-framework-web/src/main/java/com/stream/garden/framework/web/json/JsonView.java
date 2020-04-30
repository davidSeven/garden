package com.stream.garden.framework.web.json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author garden
 * @date 2020-04-07 19:52
 */
public class JsonView<T> {

    protected final T value;
    protected final Map<Class<?>, Match> matches = new HashMap<>();

    JsonView(T value) {
        this.value = value;
    }

    JsonView(T value, Match match) {
        this.value = value;
        matches.put(value.getClass(), match);
    }

    public static <E> JsonView<E> with(E value) {
        return new JsonView<>(value);
    }

    T getValue() {
        return value;
    }

    Match getMatch(Class<?> cls) {
        return matches.get(cls);
    }

    public JsonView<T> onClass(Class<?> cls, Match match) {
        matches.put(cls, match);
        return this;
    }
}
