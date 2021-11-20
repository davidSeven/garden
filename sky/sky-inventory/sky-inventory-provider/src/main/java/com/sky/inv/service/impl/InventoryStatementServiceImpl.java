package com.sky.inv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.inv.api.model.InventoryStatement;
import com.sky.inv.dao.InventoryStatementDao;
import com.sky.inv.service.InventoryStatementService;
import org.springframework.stereotype.Service;

@Service
public class InventoryStatementServiceImpl extends ServiceImpl<InventoryStatementDao, InventoryStatement> implements InventoryStatementService {
}
