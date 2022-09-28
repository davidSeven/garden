package com.sky.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.base.api.constant.ProductConstant;
import com.sky.base.api.dto.ProductDto;
import com.sky.base.api.dto.ProductQueryDto;
import com.sky.base.api.model.Product;
import com.sky.base.dao.ProductDao;
import com.sky.base.service.ProductService;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.client.service.SerialNumberClientService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

    @Autowired
    private SerialNumberClientService serialNumberClientService;

    @Override
    public Product getByCode(String code) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.<Product>lambdaQuery().eq(Product::getCode, code);
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean create(ProductDto dto) {
        Product product = BeanHelpUtil.convertDto(dto, Product.class);
        String code = serialNumberClientService.generateNumber(ProductConstant.PRODUCT_CODE);
        product.setCode(code);
        return super.save(product);
    }

    @Override
    public boolean update(ProductDto dto) {
        Product product = BeanHelpUtil.convertDto(dto, Product.class);
        return super.updateById(product);
    }

    @Override
    public IPage<Product> page(ProductQueryDto dto) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getCode()), Product::getCode, dto.getCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCodeLike()), Product::getCode, dto.getCodeLike());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getName()), Product::getName, dto.getName());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getCustomerCode()), Product::getCustomerCode, dto.getCustomerCode());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getWarehouseCode()), Product::getWarehouseCode, dto.getWarehouseCode());
        IPage<Product> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        return super.page(iPage, queryWrapper);
    }

    @Override
    public List<Product> list(ProductDto dto) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getCode()), Product::getCode, dto.getCustomerCode());
        queryWrapper.in(CollectionUtils.isNotEmpty(dto.getCodeList()), Product::getCode, dto.getCodeList());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getCustomerCode()), Product::getCustomerCode, dto.getCustomerCode());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getWarehouseCode()), Product::getWarehouseCode, dto.getWarehouseCode());
        return super.list(queryWrapper);
    }
}
