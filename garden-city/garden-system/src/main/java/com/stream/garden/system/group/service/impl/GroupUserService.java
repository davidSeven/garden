package com.stream.garden.system.group.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.group.dao.IGroupUserDao;
import com.stream.garden.system.group.model.GroupUser;
import com.stream.garden.system.group.service.IGroupUserService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-06-22 11:17
 */
@Service
public class GroupUserService extends AbstractBaseService<GroupUser, IGroupUserDao> implements IGroupUserService {

}
