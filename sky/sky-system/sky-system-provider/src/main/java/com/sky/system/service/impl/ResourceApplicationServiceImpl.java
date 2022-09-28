package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.utils.ApplicationUtil;
import com.sky.system.api.model.ResourceApplication;
import com.sky.system.api.model.ResourceDetail;
import com.sky.system.config.ResourceApplicationConfig;
import com.sky.system.dao.ResourceApplicationDao;
import com.sky.system.service.ResourceApplicationService;
import com.sky.system.service.ResourceDetailService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Service
public class ResourceApplicationServiceImpl extends ServiceImpl<ResourceApplicationDao, ResourceApplication> implements ResourceApplicationService {

    @Autowired
    private ResourceApplicationConfig resourceApplicationConfig;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ResourceDetailService resourceDetailService;

    @Override
    public List<String> applicationList() {
        Set<String> ignoreSet = resourceApplicationConfig.getIgnore();
        List<String> values = resourceApplicationConfig.getValues();
        Set<String> valuesSet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(values)) {
            valuesSet.addAll(values);
        }
        if (resourceApplicationConfig.isDiscovery()) {
            List<String> list = discoveryClient.getServices();
            if (CollectionUtils.isNotEmpty(list)) {
                if (CollectionUtils.isNotEmpty(ignoreSet)) {
                    for (String value : list) {
                        if (!ignoreSet.contains(value)) {
                            valuesSet.add(value);
                        }
                    }
                } else {
                    valuesSet.addAll(list);
                }
            }
        }
        return new ArrayList<>(valuesSet);
    }

    @Override
    public boolean refreshResourceDetail(ResourceApplication resourceApplication) {
        ResourceApplication resourceApplication1 = super.getById(resourceApplication.getId());
        if (null == resourceApplication1) {
            return false;
        }
        String applicationName = resourceApplication1.getApplicationName();
        if (StringUtils.isEmpty(applicationName)) {
            return false;
        }
        List<ResourceDetail> resourceDetailList;
        if (this.applicationName.equalsIgnoreCase(applicationName)) {
            resourceDetailList = new ArrayList<>();
            RequestMappingHandlerMapping requestMappingHandlerMapping = ApplicationUtil.getBeans(RequestMappingHandlerMapping.class);
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
        } else {
            String url = "http://" + applicationName + "/resource/application/refreshResourceDetail";
            HttpMethod httpMethod = HttpMethod.POST;
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.set("Content-Type", "application/json;charset=utf-8");
            Map<String, String> httpBody = new HashMap<>();
            HttpEntity<?> httpEntity = new HttpEntity<>(httpBody, headers);
            ResponseEntity<ResponseDto<List<ResourceDetail>>> responseEntity = this.restTemplate.exchange(url, httpMethod, httpEntity, new ParameterizedTypeReference<ResponseDto<List<ResourceDetail>>>() {
            });
            ResponseDto<List<ResourceDetail>> responseDto = responseEntity.getBody();
            resourceDetailList = ResponseDto.getDataAndException(responseDto);
        }
        return this.resourceDetailService.refreshResourceDetail(resourceApplication1.getMenuId(), resourceApplication1.getId(), resourceDetailList);
    }
}
