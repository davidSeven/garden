package com.stream.garden.framework.cache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取配置文件
 *
 * @author garden
 * @date 2019-10-14 10:31
 */
public class PropertiesUtil {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
    }

    /**
     * 读取配置文件
     *
     * @param propPath 配置文件地址
     * @return Properties
     */
    public static Properties readProperties(String propPath) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        Properties prop = null;
        try {
            prop = new Properties();
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propPath);
            if (null != inputStream) {
                inputStreamReader = new InputStreamReader(inputStream, DEFAULT_ENCODING);
                prop.load(inputStreamReader);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return prop;
    }
}
