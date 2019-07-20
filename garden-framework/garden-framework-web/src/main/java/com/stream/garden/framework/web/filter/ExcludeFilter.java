package com.stream.garden.framework.web.filter;

import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.web.config.GlobalConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 * @date 2019-07-20 10:58
 */
public class ExcludeFilter {

    private PathMatcher pathMatcher = new AntPathMatcher();
    private List<String> excludePath = new ArrayList<>();

    public void init(GlobalConfig globalConfig) {
        if (StringUtils.isNotEmpty(globalConfig.getLoginPath())) {
            excludePath.add(globalConfig.getLoginPath());
        }
        if (CollectionUtil.isNotEmpty(globalConfig.getExcludePath())) {
            excludePath.addAll(globalConfig.getExcludePath());
        }
    }

    public boolean exclude(String uri) {
        boolean jump = false;
        for (String path : excludePath) {
            if (pathMatcher.match(path, uri)) {
                jump = true;
                break;
            }
        }
        return jump;
    }
}
