package com.sky.framework.json.spring;

import com.sky.framework.json.JsonView;

public class JsonResultRetriever {

    static boolean hasValue() {
        return JsonResult.get() != null;
    }

    static JsonView<?> retrieve() {
        JsonView<?> val = JsonResult.get();
        JsonResult.unset();
        return val;
    }
}
