package com.stream.garden.framework.web.filter;

import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.web.config.GlobalConfig;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 * @date 2019-07-26 14:06
 */
public class StaticExcludeFilter {

    private PathMatcher pathMatcher = new AntPathMatcher();
    private List<String> excludePath = new ArrayList<>();

    public void addExcludePath(String path) {
        excludePath.add(path);
    }

    public void init(GlobalConfig globalConfig) {
        if (CollectionUtil.isNotEmpty(globalConfig.getExcludePath())) {
            excludePath.addAll(globalConfig.getExcludePath());
        }
    }

    public boolean exclude(String uri) {
        boolean jump = false;
        if (excludePath.isEmpty()) {
            return jump;
        }
        for (String path : excludePath) {
            if (pathMatcher.match(path, uri)) {
                jump = true;
                break;
            }
        }
        return jump;
    }
}
