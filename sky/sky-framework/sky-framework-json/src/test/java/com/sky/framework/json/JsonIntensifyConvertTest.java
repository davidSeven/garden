package com.sky.framework.json;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonIntensifyConvertTest {

    @Test
    public void TestWrapJsonIntensifyConvert() {

        JsonIntensifyConvert jsonIntensifyConvert = JsonIntensifyUtil.loadClassConvert("com.sky.framework.json.JsonIntensifyConvert$WrapJsonIntensifyConvert", JsonIntensifyConvert.class);

        System.out.println(jsonIntensifyConvert.convert("123"));
    }

    @Test
    public void Test2() {

        JsonIntensifyAppend jsonIntensifyAppend = JsonIntensifyUtil.loadClassConvert("com.sky.framework.json.JsonIntensifyAppend$OriginalJsonIntensifyAppend", JsonIntensifyAppend.class);

        Map<String, String> map = new HashMap<>();
        map.put("code", "U001");

        System.out.println(jsonIntensifyAppend.convert(map));
    }
}
