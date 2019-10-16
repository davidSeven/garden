package com.stream.garden.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author garden
 * @date 2019-10-14 10:31
 */
public class PropertiesUtil {

    private static final String DEFAULT_ENCODING = "UTF-8";

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
            e.printStackTrace();
        } finally {
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
