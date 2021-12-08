package com.sky.base.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.ProductDto;
import com.sky.base.api.dto.ProductQueryDto;
import com.sky.base.api.model.Product;

import java.util.List;

public interface ProductClientService {

    Product get(Long id);

    Product getByCode(String code);

    boolean create(ProductDto dto);

    boolean update(ProductDto dto);

    boolean delete(ProductDto dto);

    IPage<Product> page(ProductQueryDto dto);

    List<Product> list(ProductDto dto);
}
