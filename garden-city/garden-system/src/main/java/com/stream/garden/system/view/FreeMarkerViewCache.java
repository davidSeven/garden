package com.stream.garden.system.view;

import com.stream.garden.framework.util.CollectionUtil;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author garden
 * @date 2019-09-30 14:08
 */
public class FreeMarkerViewCache {

    private static Map<String, String> viewCacheMap = new HashMap<>();

    public static String getTemplate(String url) {
        return viewCacheMap.get(url);
    }

    public static void reload(IView view) {
        if (null != view) {
            Map<String, String> viewMap = view.getView();
            if (null != viewMap) {
                viewCacheMap.putAll(viewMap);
            }
        }
    }

    public static void init(ApplicationContext applicationContext) {
        Map<String, IView> viewMap = applicationContext.getBeansOfType(IView.class);
        if (!CollectionUtil.isEmpty(viewMap)) {
            for (IView view : viewMap.values()) {
                FreeMarkerViewCache.reload(view);
            }
        }
    }
}
