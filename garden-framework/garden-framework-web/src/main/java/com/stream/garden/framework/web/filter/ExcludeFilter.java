package com.stream.garden.framework.web.filter;

import com.stream.garden.framework.web.config.GlobalConfig;

/**
 * @author garden
 * @date 2019-07-20 10:58
 */
public class ExcludeFilter extends StaticExcludeFilter {

    @Override
    public void init(GlobalConfig globalConfig) {
        super.init(globalConfig);
        super.addExcludePath(globalConfig.getLoginPath());
    }
}
