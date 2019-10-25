package com.stream.garden.content.controller;

import com.stream.garden.content.exception.ContentExceptionCode;
import com.stream.garden.content.model.Content;
import com.stream.garden.content.service.IContentService;
import com.stream.garden.content.vo.ContentVO;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author garden
 * @date 2019-10-24 17:17
 */
@Controller
@RequestMapping(value = "/content/content")
public class ContentController {
    private Logger logger = LoggerFactory.getLogger(ContentController.class);

    private IContentService contentService;

    @Autowired
    public ContentController(IContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "content/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "content/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Content content) {
        try {
            return new Result<Integer>().ok().setData(contentService.insert(content));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ContentExceptionCode.CONTENT_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Content content) {
        try {
            return new Result<Integer>().ok().setData(contentService.update(content));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ContentExceptionCode.CONTENT_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<Content> get(Content content) {
        try {
            return new Result<Content>().ok().setData(contentService.get(content.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Content content) {
        try {
            return new Result<Integer>().ok().setData(contentService.delete(content.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<Content>> pageList(ContentVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<Content>>().setData(contentService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }
}
