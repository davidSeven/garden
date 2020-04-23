package com.stream.garden.system.user.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 * @date 2020-04-22 17:38
 */
@Component
public class UserQueryResolver implements GraphQLQueryResolver {
    private Logger logger = LoggerFactory.getLogger(UserQueryResolver.class);

    @Autowired
    private IUserService userService;

    public List<User> findUsers() {
        User user = new User();
        try {
            return userService.list(user);
        } catch (ApplicationException e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }
}
