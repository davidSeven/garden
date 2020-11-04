package com.sky.framework.json.spring;

import com.sky.framework.json.spring.server.JavaConfigServer;
import org.junit.BeforeClass;

public class JavaConfigurationTest extends ConfigTest {

    @BeforeClass()
    public static void init() throws Exception {
        server = new JavaConfigServer();
        ConfigTest.start();
    }

}
