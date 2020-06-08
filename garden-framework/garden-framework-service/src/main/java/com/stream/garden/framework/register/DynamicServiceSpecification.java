package com.stream.garden.framework.register;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public class DynamicServiceSpecification implements Specification {

    private String name;

    private Class<?>[] configuration;

    public DynamicServiceSpecification() {
    }

    public DynamicServiceSpecification(String name, Class<?>[] configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?>[] getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Class<?>[] configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicServiceSpecification that = (DynamicServiceSpecification) o;
        return Objects.equals(name, that.name) &&
                Arrays.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, configuration);
    }

    @Override
    public String toString() {
        return "ClientServiceSpecification{" +
                "name='" + name + "', " +
                "configuration=" + Arrays.toString(configuration) +
                "}";
    }

}
