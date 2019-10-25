package com.stream.garden.framework.api.interfaces;

import java.util.Map;

/**
 * 国际化资源实现接口
 *
 * @author garden
 * @date 2019-10-25 11:30
 */
public interface ISystemMessage {

    /**
     * 返回国际化键值对
     *
     * @param languageType 语言类型
     * @return map
     */
    Map<String, String> getMessage(String languageType);
}
