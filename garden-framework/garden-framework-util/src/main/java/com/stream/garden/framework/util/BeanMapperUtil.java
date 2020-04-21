package com.stream.garden.framework.util;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author garden
 * @date 2020-04-21 10:36
 */
public final class BeanMapperUtil {

    private static DozerBeanMapper mapper = (DozerBeanMapper) DozerBeanMapperBuilder.buildDefault();

    private BeanMapperUtil() {
    }

    public static DozerBeanMapper getMapper() {
        return mapper;
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return getMapper().map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        return sourceList.stream()
                .map(source -> getMapper().map(source, destinationClass))
                .collect(Collectors.toList());
    }

    public static void map(Object source, Object destinationObject) {
        getMapper().map(source, destinationObject);
    }

}
