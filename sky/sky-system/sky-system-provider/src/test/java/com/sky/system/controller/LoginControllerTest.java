package com.sky.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.SafetyCheckDto;
import com.sky.system.api.dto.UserLoginDto;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * @date 2020-11-05 005 16:14
 */
public class LoginControllerTest {

    private static String url = "";

    @BeforeClass
    public static void init() {
        url = "http://localhost:18080";
    }

    @Test
    public void login() throws IOException {
        String asString = Request.Post(url + "/login/safetyCheck").execute().returnContent().asString();
        System.out.println(asString);
        ResponseDto<SafetyCheckDto> safetyCheckDtoResponseDto = JSON.parseObject(asString, new TypeReference<ResponseDto<SafetyCheckDto>>() {
        });
        String vcToken = null;
        boolean needVc = false;
        if (0 == safetyCheckDtoResponseDto.getCode()) {
            vcToken = safetyCheckDtoResponseDto.getData().getVcToken();
            needVc = safetyCheckDtoResponseDto.getData().isNeedVc();
        }

        String verifyCode = null;
        if (needVc) {
            String asString2 = Request.Post(url + "/login/verifyCode").addHeader("vcToken", vcToken).execute().returnContent().asString();
            ResponseDto<String> stringResponseDto = JSON.parseObject(asString2, new TypeReference<ResponseDto<String>>() {
            });
            if (0 == stringResponseDto.getCode()) {
                verifyCode = stringResponseDto.getData();
            }
        }

        String asString3 = Request.Post(url + "/login/login")
                .addHeader("vcToken", vcToken)
                .addHeader("lrTime", "16043446787731")
                .bodyString("{\"code\":\"admin\",\"password\":\"123\",\"verifyCode\":\"" + verifyCode + "\"}", ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        ResponseDto<UserLoginDto> userLoginDtoResponseDto = JSON.parseObject(asString3, new TypeReference<ResponseDto<UserLoginDto>>() {
        });

        String loginToken = userLoginDtoResponseDto.getData().getToken();

        Request.Post(url + "/login/logout").addHeader("Authorization", loginToken).execute().returnContent().asString();
    }
}
