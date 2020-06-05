package com.stream.garden.system.number.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.number.model.SerialNumber;

import java.util.List;

/**
 * @author garden
 * @date 2020-06-04 17:15
 */
public interface ISerialNumberService extends IBaseService<SerialNumber> {

    /**
     * 生成流水号
     *
     * @param code 业务编码
     * @return string
     * @throws ApplicationException ApplicationException
     */
    String generateNumber(String code) throws ApplicationException;

    /**
     * 批量生成流水号
     *
     * @param code 业务编码
     * @param num  生成个数
     * @return string
     * @throws ApplicationException ApplicationException
     */
    List<String> generateNumbers(String code, int num) throws ApplicationException;
}
