package com.sky.base.client.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.ProductDto;
import com.sky.base.api.dto.ProductQueryDto;
import com.sky.base.api.model.Product;
import com.sky.base.client.feign.ProductClientFeign;
import com.sky.base.client.service.ProductClientService;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductClientServiceImpl implements ProductClientService {

    @Autowired
    private ProductClientFeign productClientFeign;

    @Override
    public Product get(Long id) {
        return ResponseDto.getDataAndException(productClientFeign.get(id));
    }

    @Override
    public Product getByCode(String code) {
        return ResponseDto.getDataAndException(productClientFeign.getByCode(code));
    }

    @Override
    public boolean create(ProductDto dto) {
        return ResponseDto.getDataBAndException(productClientFeign.create(dto));
    }

    @Override
    public boolean update(ProductDto dto) {
        return ResponseDto.getDataBAndException(productClientFeign.update(dto));
    }

    @Override
    public boolean delete(ProductDto dto) {
        return ResponseDto.getDataBAndException(productClientFeign.delete(dto));
    }

    @Override
    public IPage<Product> page(ProductQueryDto dto) {
        return ResponseDto.getDataAndException(productClientFeign.page(dto));
    }

    @Override
    public List<Product> list(ProductDto dto) {
        return ResponseDto.getDataAndException(productClientFeign.list(dto));
    }
}
