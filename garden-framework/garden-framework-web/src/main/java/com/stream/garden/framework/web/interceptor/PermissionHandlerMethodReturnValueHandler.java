package com.stream.garden.framework.web.interceptor;

import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author garden
 * @date 2020-01-04 19:46
 */
public class PermissionHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    private Map<String, Set<String>> filterFields = new HashMap<>();

    {
        Set<String> fieldSet = new HashSet<>();
        fieldSet.add("data.rows.lastLoginIp");
        fieldSet.add("data.lastLoginIp");
        filterFields.put("/system/user/pageList", fieldSet);
        filterFields.put("/system/user/list", fieldSet);
    }

    public PermissionHandlerMethodReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return this.delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        // 获取到请求的url
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Set<String> fieldSet = null;
        if (null != httpServletRequest) {
            String servletPath = httpServletRequest.getServletPath();
            System.out.println(servletPath);
            fieldSet = filterFields.get(servletPath);
        }
        // 处理返回值
        this.delegate.handleReturnValue(this.handleReturnValue(o, fieldSet), methodParameter, modelAndViewContainer, nativeWebRequest);
    }

    /**
     * 处理返回值
     *
     * @param source 对象
     * @return object
     */
    private Object handleReturnValue(Object source, Set<String> fieldSet) {
        // 验证
        if (null == source) {
            return null;
        }
        if (null == fieldSet) {
            return source;
        }
        // 结构兼容
        if (source instanceof Result) {
            Result<?> result = (Result<?>) source;
            Object data = result.getData();
            if (data instanceof PageInfo) {
                PageInfo<?> pageInfo = (PageInfo<?>) data;
                doEncrypt(pageInfo.getRows(), "data.rows", fieldSet);
            } else {
                doEncrypt(data, "data", fieldSet);
            }
        } else {
            doEncrypt(source, "", fieldSet);
        }
        return source;
    }

    @SuppressWarnings({"unchecked"})
    private void doEncrypt(Object source, String path, Set<String> fieldSet) {
        if (source instanceof List || source instanceof Set || source.getClass().isArray()) {
            Iterable iterable;
            if (source.getClass().isArray()) {
                iterable = Arrays.asList((Object[]) source);
            } else {
                iterable = (Iterable) source;
            }
            for (Object object : iterable) {
                doEncryptObject(object, path, fieldSet);
            }
        } else {
            doEncryptObject(source, path, fieldSet);
        }
    }

    private void doEncryptObject(Object source, String path, Set<String> fieldSet) {
        Field[] fields = source.getClass().getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields)) {
            doEncryptField(fields, path, source, fieldSet);
        }
        if (null != source.getClass().getSuperclass()) {
            Field[] superFields = source.getClass().getSuperclass().getDeclaredFields();
            if (ArrayUtils.isNotEmpty(superFields)) {
                doEncryptField(superFields, path, source, fieldSet);
            }
        }
    }

    private void doEncryptField(Field[] fields, String path, Object object, Set<String> fieldSet) {
        for (Field field : fields) {
            if (fieldSet.contains(suffix(path, field.getName()))) {
                doEncryptField(field, object);
            }
        }
    }

    private void doEncryptField(Field field, Object object) {
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        try {
            // 字符串返回***
            if ("java.lang.String".equals(field.getType().getName())) {
                field.set(object, "***");
            } else {
                field.set(object, null);
            }
            field.setAccessible(accessible);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private String suffix(String path, String value) {
        if (null == path) {
            return null;
        }
        if (path.endsWith(".")) {
            return path + value;
        }
        return path + "." + value;
    }
}
