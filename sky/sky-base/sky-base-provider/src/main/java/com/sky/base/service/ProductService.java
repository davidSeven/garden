package com.sky.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.base.api.dto.ProductDto;
import com.sky.base.api.dto.ProductQueryDto;
import com.sky.base.api.model.Product;

import java.util.List;

public interface ProductService extends IService<Product> {

    Product getByCode(String code);

    boolean create(ProductDto dto);

    boolean update(ProductDto dto);

    IPage<Product> page(ProductQueryDto dto);

    List<Product> list(ProductDto dto);
}
