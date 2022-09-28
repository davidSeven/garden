package com.sky.system.client.service;

import java.util.List;

public interface SerialNumberClientService {

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
}
