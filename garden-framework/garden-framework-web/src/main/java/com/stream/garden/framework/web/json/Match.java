package com.stream.garden.framework.web.json;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author garden
 * @date 2020-04-07 19:54
 */
public class Match {

    private final Set<String> includes = new HashSet<>();
    private final Set<String> excludes = new HashSet<>();

    public Match() {

    }

    public static Match match() {
        return new Match();
    }

    public Match include(String... fields) {
        if (fields != null) {
            includes.addAll(Arrays.asList(fields));
        }
        return this;
    }

    public Match exclude(String... fields) {
        if (fields != null) {
            excludes.addAll(Arrays.asList(fields));
        }
        return this;
    }

    Set<String> getIncludes() {
        return includes;
    }

    Set<String> getExcludes() {
        return excludes;
    }

    @Override
    public String toString() {
        return "Match{" +
                "includes=" + includes +
                ", excludes=" + excludes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (includes != null ? !includes.equals(match.includes) : match.includes != null) return false;
        return excludes != null ? excludes.equals(match.excludes) : match.excludes == null;

    }

    @Override
    public int hashCode() {
        int result = includes != null ? includes.hashCode() : 0;
        result = 31 * result + (excludes != null ? excludes.hashCode() : 0);
        return result;
    }

}
