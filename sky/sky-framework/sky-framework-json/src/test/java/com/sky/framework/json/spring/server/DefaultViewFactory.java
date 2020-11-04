package com.sky.framework.json.spring.server;

import com.sky.framework.json.model.User;
import com.sky.framework.json.spring.DefaultView;

import static com.sky.framework.json.Match.match;

public class DefaultViewFactory {
    private static final DefaultView defaultView = DefaultView.create().onClass(User.class, match().exclude("age"));

    public static DefaultView instance() {
        return defaultView;
    }
}
