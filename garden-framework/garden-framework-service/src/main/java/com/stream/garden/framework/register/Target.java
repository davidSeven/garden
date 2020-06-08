package com.stream.garden.framework.register;

/**
 * 代理
 *
 * @param <T>
 */
public interface Target<T> {

    Class<T> type();

    class HardCodedTarget<T> implements Target<T> {

        private final Class<T> type;

        public HardCodedTarget(Class<T> type) {
            this.type = type;
        }

        @Override
        public Class<T> type() {
            return type;
        }
    }
}
