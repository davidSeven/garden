package com.stream.garden.system.group.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.group.dao.IGroupDao;
import com.stream.garden.system.group.model.Group;
import com.stream.garden.system.group.service.IGroupService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-06-22 11:16
 */
@Service
public class GroupService extends AbstractBaseService<Group, String, IGroupDao> implements IGroupService {

}
