package com.stream.garden.framework.web.filter;

import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.remote.RemoteAuthorizationUtil;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 判断是否为远程认证
     *
     * @param request request
     * @return boolean
     */
    public boolean isRemoteAuthorization(HttpServletRequest request) {
        if (null == request) {
            return false;
        }
        if (RemoteAuthorizationUtil.authorization(request)) {
            return true;
        }
        return false;
    }
}
