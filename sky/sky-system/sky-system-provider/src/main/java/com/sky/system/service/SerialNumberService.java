package com.sky.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.SerialNumberQueryDto;
import com.sky.system.api.model.SerialNumber;

import java.util.List;

public interface SerialNumberService extends IService<SerialNumber> {

    /**
     * 分页
     *
     * @param dto dto
     * @return SerialNumber
     */
    IPage<SerialNumber> page(SerialNumberQueryDto dto);

    /**
     * 生成流水号
     *
     * @param code 业务编码
     * @return string
     */
    String generateNumber(String code);

    /**
     * 批量生成流水号
     *
     * @param code 业务编码
     * @param num  生成个数
     * @return string
     */
    List<String> generateNumbers(String code, int num);

    /**
     * 数据同步
     *
     * @return int
     */
    int asyncData();
}
