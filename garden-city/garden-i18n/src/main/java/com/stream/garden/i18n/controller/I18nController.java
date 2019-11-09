package com.stream.garden.i18n.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.framework.web.util.ApplicationUtil;
import com.stream.garden.i18n.exception.I18nExceptionCode;
import com.stream.garden.i18n.model.I18n;
import com.stream.garden.i18n.service.II18nService;
import com.stream.garden.i18n.service.impl.I18nExcelImportService;
import com.stream.garden.i18n.vo.I18nVO;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

/**
 * @author garden
 * @date 2019-10-24 17:17
 */
@Controller
@RequestMapping(value = "/i18n/i18n")
public class I18nController {
    private Logger logger = LoggerFactory.getLogger(I18nController.class);

    private II18nService i18nService;

    @Autowired
    private I18nExcelImportService excelImportService;

    @Autowired
    public I18nController(II18nService i18nService) {
        this.i18nService = i18nService;
    }

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "i18n/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "i18n/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(I18n i18n) {
        try {
            return new Result<Integer>().ok().setData(i18nService.insert(i18n));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, I18nExceptionCode.I18N_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(I18n i18n) {
        try {
            return new Result<Integer>().ok().setData(i18nService.update(i18n));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, I18nExceptionCode.I18N_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<I18n> get(I18n i18n) {
        try {
            return new Result<I18n>().ok().setData(i18nService.get(i18n.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(I18n i18n) {
        try {
            return new Result<Integer>().ok().setData(i18nService.delete(i18n.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<I18n>> pageList(I18nVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<I18n>>().setData(i18nService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/reload")
    @ResponseBody
    public Result<Integer> reload() {
        try {
            Object messageSource = ApplicationUtil.getBeans(MessageSource.class);
            // 反射调用
            MethodUtils.invokeMethod(messageSource, true, "reload");
            return new Result<Integer>().ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/currentI18nList")
    @ResponseBody
    @SuppressWarnings({"unchecked"})
    public Result<Map<String, String>> currentI18nList(HttpServletRequest request) {
        try {
            Locale locale = null;
            // Determine locale to use for this RequestContext.
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver instanceof LocaleContextResolver) {
                LocaleContext localeContext = ((LocaleContextResolver) localeResolver).resolveLocaleContext(request);
                locale = localeContext.getLocale();
            } else if (localeResolver != null) {
                // Try LocaleResolver (we're within a DispatcherServlet request).
                locale = localeResolver.resolveLocale(request);
            }
            if (null == locale) {
                locale = request.getLocale();
            }
            if (null == locale) {
                locale = Locale.getDefault();
            }
            Object messageSource = ApplicationUtil.getBeans(MessageSource.class);
            Object messageMap = MethodUtils.invokeMethod(messageSource, true, "getMessageMap", locale.getLanguage());
            Map<String, String> localeMap = (Map<String, String>) messageMap;
            return new Result<Map<String, String>>().ok().setData(localeMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/importExcel")
    @ResponseBody
    public Result<Integer> importExcel(@RequestBody Map<String, String> params) {
        try {
            String excelId = params.get("excelId");
            String excelCode = params.get("excelCode");
            String configCode = params.get("configCode");
            this.excelImportService.importExcel(excelId, excelCode, configCode);
            return new Result<Integer>().ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }
}
