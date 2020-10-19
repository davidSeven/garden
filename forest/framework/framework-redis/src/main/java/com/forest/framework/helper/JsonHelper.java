package com.forest.framework.helper;

import com.forest.framework.util.JsonUtil;

/**
 * @author forest
 * @date 2019-07-15 10:07
 */
public class JsonHelper {

    private JsonHelper() {
    }

    /**
     * Java对象序列化为JSON字符串
     *
     * @param obj Java对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        return JsonUtil.objectToJson(obj);
    }
}
