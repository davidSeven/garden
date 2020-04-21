package com.stream.garden.content.service.impl;

import com.stream.garden.content.dao.IContentDao;
import com.stream.garden.content.model.Content;
import com.stream.garden.content.service.IContentService;
import com.stream.garden.framework.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-10-25 16:38
 */
@Service
public class ContentService extends AbstractBaseService<Content, String, IContentDao> implements IContentService {

}
