package com.sky.framework.api.context;

import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
public interface CommonConstant {

    String ENCODING = StandardCharsets.UTF_8.name();
    MediaType MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

    String SPLIT = "/";
}
