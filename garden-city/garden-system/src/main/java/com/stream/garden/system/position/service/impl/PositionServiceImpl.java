package com.stream.garden.system.position.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.position.dao.IPositionDao;
import com.stream.garden.system.position.model.Position;
import com.stream.garden.system.position.service.IPositionService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019/7/21 12:26
 */
@Service
public class PositionServiceImpl extends AbstractBaseService<Position, IPositionDao> implements IPositionService {

}
