package com.sky.framework.service.exception;

import com.alibaba.fastjson.JSON;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.api.exception.CommonException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @date 2020-10-29 029 11:51
 */
@Configuration
public class ExceptionErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                ResponseDto<?> responseDto = JSON.parseObject(body, ResponseDto.class);
                if (null != responseDto) {
                    throw new CommonException(responseDto.getCode(), responseDto.getMessage());
                }
            }
        } catch (Exception var4) {
            if (var4 instanceof CommonException) {
                return var4;
            }
            return new CommonException(10999, var4.getMessage());
        }
        return new CommonException(10999, "系统异常,请联系管理员");
    }
}
