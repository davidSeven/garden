package com.sky.system.client.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.model.ResourceApplication;
import com.sky.system.api.model.ResourceDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Api(tags = "资源应用管理")
@RestController
@RequestMapping(value = "/resource/application")
public class ResourceApplicationClientController {

    @Autowired
    private ApplicationContext applicationContext;

    @ApiOperation(value = "刷新资源清单")
    @ApiImplicitParam(name = "resourceApplication", value = "参数", dataType = "ResourceApplication")
    @PostMapping(value = "/refreshResourceDetail")
    public ResponseDto<List<ResourceDetail>> refreshResourceDetail(@RequestBody ResourceApplication resourceApplication) {
        List<ResourceDetail> resourceDetailList = new ArrayList<>();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        System.out.println(handlerMethods);
        int index = 1;
        for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            Set<RequestMethod> methods = methodsCondition.getMethods();
            Set<String> patterns = patternsCondition.getPatterns();
            for (RequestMethod requestMethod : methods) {
                for (String pattern : patterns) {
                    ResourceDetail resourceDetail = new ResourceDetail();
                    resourceDetail.setMethod(requestMethod.name());
                    resourceDetail.setUrl(pattern);
                    resourceDetail.setSort(index++);
                    resourceDetailList.add(resourceDetail);
                }
            }
        }
        return new ResponseDto<>(resourceDetailList).ok();
    }
}
