package com.sky.framework.json.spring;

import com.sky.framework.json.JsonView;

public class JsonResult {
    private static final JsonResult instance = new JsonResult();
    private static final ThreadLocal<JsonView<?>> current = new ThreadLocal<>();

    private JsonResult() {
    }

    public static JsonResult instance() {
        return instance;
    }

    static JsonView<?> get() {
        return current.get();
    }

    static void unset() {
        current.remove();
    }

    /**
     * Use the provided {@code JsonView} object to serialize
     * the return value.
     *
     * @param view JsonView used to render JSON
     * @param <E>  Type of object being rendered
     * @return ResultWrapper to provide return value
     */
    public <E> ResultWrapper<E> use(JsonView<E> view) {
        current.set(view);
        return new ResultWrapper<>(view);
    }

    public static class ResultWrapper<T> {
        private final JsonView<T> obj;

        private ResultWrapper(JsonView<T> obj) {
            this.obj = obj;
        }

        /**
         * Returns the object being serialized
         *
         * @return the object
         */
        public T returnValue() {
            return obj.getValue();
        }
    }
}
