package com.stream.forest.framework.common.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DebugDataBean
 */
@Getter
@Setter
public class DebugDataBean implements Serializable {
    private static final long serialVersionUID = 2992769554516491987L;
    private String clientIpAddress;
    private String loginUserId;
    private String loginAddrCode;
    private String loginAppType;
    private String eventPage;
    private String eventComp;
    private String oriApp;
    private String exceptionStackTrace;
    private List<String> apiCallStacks = new ArrayList<>();
}
