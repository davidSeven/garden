package com.stream.garden.warehouse.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.warehouse.dao.IMaterialDao;
import com.stream.garden.warehouse.model.Material;
import com.stream.garden.warehouse.service.IMaterialService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2020-01-02 19:02
 */
@Service
public class MaterialServiceImpl extends AbstractBaseService<Material, String, IMaterialDao> implements IMaterialService {

}
