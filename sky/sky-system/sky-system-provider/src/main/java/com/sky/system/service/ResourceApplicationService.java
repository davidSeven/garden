package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.model.ResourceApplication;

import java.util.List;

public interface ResourceApplicationService extends IService<ResourceApplication> {

    List<String> applicationList();

    boolean refreshResourceDetail(ResourceApplication resourceApplication);
}
