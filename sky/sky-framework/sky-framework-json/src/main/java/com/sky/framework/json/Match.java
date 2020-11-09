package com.sky.framework.json;

import java.util.*;
import java.util.function.BiFunction;

public class Match {
    private final Set<String> includes = new HashSet<>();
    private final Set<String> excludes = new HashSet<>();
    private final Map<String, BiFunction<Object, Object, Object>> transforms = new HashMap<>();
    private FieldExpandInterface fieldExpandInterface = null;

    Match() {

    }

    public static Match match() {
        return new Match();
    }

    /**
     * Mark fields for inclusion during serialization.
     *
     * @param fields The fields to include
     * @return Match
     */
    public Match include(String... fields) {
        if (fields != null) {
            includes.addAll(Arrays.asList(fields));
        }
        return this;
    }

    public Match include(Set<String> fields) {
        if (fields != null) {
            includes.addAll(fields);
        }
        return this;
    }

    /**
     * Mark fields for exclusion during serialization.
     *
     * @param fields The fields to exclude
     * @return Match
     */
    public Match exclude(String... fields) {
        if (fields != null) {
            excludes.addAll(Arrays.asList(fields));
        }
        return this;
    }

    public Match exclude(Set<String> fields) {
        if (null != fields) {
            excludes.addAll(fields);
        }
        return this;
    }

    /**
     * Mark a field for transformation during serialization.
     *
     * @param field       The fields to include
     * @param transformer The function to transform the field. Will be provided with the whole object and the field.
     * @param <X>         The object being serialized
     * @param <Y>         The field being serialized
     * @param <Z>         The value of the field to serialize
     * @return Match
     */
    @SuppressWarnings("unchecked")
    public <X, Y, Z> Match transform(String field, BiFunction<X, Y, Z> transformer) {
        transforms.put(field, (BiFunction<Object, Object, Object>) transformer);
        return this;
    }

    /**
     * 增加字段扩展
     *
     * @param fieldExpandInterface fieldExpandInterface
     * @return Match
     */
    public Match fieldExpandInterface(FieldExpandInterface fieldExpandInterface) {
        this.fieldExpandInterface = fieldExpandInterface;
        return this;
    }

    Set<String> getIncludes() {
        return includes;
    }

    Set<String> getExcludes() {
        return excludes;
    }

    Map<String, BiFunction<Object, Object, Object>> getTransforms() {
        return transforms;
    }

    public FieldExpandInterface getFieldExpandInterface() {
        return fieldExpandInterface;
    }

    public void setFieldExpandInterface(FieldExpandInterface fieldExpandInterface) {
        this.fieldExpandInterface = fieldExpandInterface;
    }

    @Override
    public String toString() {
        return "Match{" +
                "includes=" + includes +
                ", excludes=" + excludes +
                ", transforms=" + transforms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (includes != null ? !includes.equals(match.includes) : match.includes != null) return false;
        if (excludes != null ? !excludes.equals(match.excludes) : match.excludes != null) return false;
        return transforms != null ? transforms.equals(match.transforms) : match.transforms == null;
    }

    @Override
    public int hashCode() {
        int result = includes != null ? includes.hashCode() : 0;
        result = 31 * result + (excludes != null ? excludes.hashCode() : 0);
        result = 31 * result + (transforms != null ? transforms.hashCode() : 0);
        return result;
    }
}
