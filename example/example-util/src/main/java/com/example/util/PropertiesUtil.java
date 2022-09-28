package com.example.util;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    public static Properties load(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        Properties properties = new Properties();
        try {
            properties.load(classPathResource.getStream());
            System.out.println("读取配置文件完成");
            System.out.println(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
