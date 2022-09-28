package com.sky.base.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.ProductDto;
import com.sky.base.api.dto.ProductQueryDto;
import com.sky.base.api.model.Product;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductRemoteService {

    @GetMapping(value = "/product/{id}")
    ResponseDto<Product> get(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/product/code/{code}")
    ResponseDto<Product> getByCode(@PathVariable(value = "code") String code);

    @PostMapping(value = "/product")
    ResponseDto<Boolean> create(@RequestBody ProductDto dto);

    @PutMapping(value = "/product")
    ResponseDto<Boolean> update(@RequestBody @Validated ProductDto dto);

    @DeleteMapping(value = "/product")
    ResponseDto<Boolean> delete(@RequestBody @Validated ProductDto dto);

    @PostMapping(value = "/product/page")
    ResponseDto<IPage<Product>> page(@RequestBody ProductQueryDto dto);

    @PostMapping(value = "/product/list")
    ResponseDto<List<Product>> list(@RequestBody ProductDto dto);
}
