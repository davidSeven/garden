package com.sky.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.ProductDto;
import com.sky.base.api.dto.ProductQueryDto;
import com.sky.base.api.model.Product;
import com.sky.base.api.remote.ProductRemoteService;
import com.sky.base.service.ProductService;
import com.sky.framework.api.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "产品")
@ApiSort(200)
@RestController
public class ProductController implements ProductRemoteService {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseDto<Product> get(Long id) {
        return new ResponseDto<>(productService.getById(id));
    }

    @Override
    public ResponseDto<Product> getByCode(String code) {
        return new ResponseDto<>(productService.getByCode(code));
    }

    @Override
    public ResponseDto<Boolean> create(ProductDto dto) {
        return new ResponseDto<>(productService.create(dto));
    }

    @Override
    public ResponseDto<Boolean> update(ProductDto dto) {
        return new ResponseDto<>(productService.update(dto));
    }

    @Override
    public ResponseDto<Boolean> delete(ProductDto dto) {
        return new ResponseDto<>(productService.removeById(dto.getId()));
    }

    @Override
    public ResponseDto<IPage<Product>> page(ProductQueryDto dto) {
        return new ResponseDto<>(productService.page(dto));
    }

    @Override
    public ResponseDto<List<Product>> list(ProductDto dto) {
        return new ResponseDto<>(productService.list(dto));
    }
}
