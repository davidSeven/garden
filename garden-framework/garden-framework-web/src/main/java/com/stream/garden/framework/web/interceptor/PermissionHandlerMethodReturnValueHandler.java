package com.stream.garden.framework.web.interceptor;

import com.stream.garden.framework.api.model.BaseModel;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.web.json.HandlerJsonView;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author garden
 * @date 2020-01-04 19:46
 */
public class PermissionHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    private final HandlerFieldSerializer handlerFieldSerializer;
    private final HandlerFieldNameSerializer handlerFieldNameSerializer;

    public PermissionHandlerMethodReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
        this.handlerFieldSerializer = null;
        this.handlerFieldNameSerializer = null;
    }

    public PermissionHandlerMethodReturnValueHandler(HandlerMethodReturnValueHandler delegate, HandlerFieldSerializer handlerFieldSerializer, HandlerFieldNameSerializer handlerFieldNameSerializer) {
        this.delegate = delegate;
        this.handlerFieldSerializer = handlerFieldSerializer;
        this.handlerFieldNameSerializer = handlerFieldNameSerializer;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return this.delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        // ??????????????????url
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        FieldSerializer fieldSerializer = null;
        if (null != httpServletRequest && null != this.handlerFieldSerializer) {
            String servletPath = httpServletRequest.getServletPath();
            // System.out.println(servletPath);
            fieldSerializer = this.handlerFieldSerializer.filterUrl(servletPath);
        }
        // ???????????????
        // this.delegate.handleReturnValue(this.handleReturnValue(o, handlerFieldSet), methodParameter, modelAndViewContainer, nativeWebRequest);
        // ??????????????????????????????????????????
        // ??????ID???????????????????????????
        if (null != fieldSerializer || fieldIntensify(o)) {
            this.delegate.handleReturnValue(this.handleReturnValue(o, fieldSerializer, handlerFieldNameSerializer), methodParameter, modelAndViewContainer, nativeWebRequest);
        } else {
            this.delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }

    private Object handleReturnValue(Object source, FieldSerializer fieldSerializer, HandlerFieldNameSerializer handlerFieldNameSerializer) {
        if (null == source) {
            return null;
        }
        HandlerJsonView<?> handlerJsonView = new HandlerJsonView<>(source);
        handlerJsonView.setHandlerJsonViewFilter(new PermissionHandlerJsonViewFilter(fieldSerializer, handlerFieldNameSerializer));
        return handlerJsonView;
    }

    /**
     * ????????????
     *
     * @param source source
     * @return boolean
     */
    private boolean fieldIntensify(Object source) {
        if (source instanceof Result) {
            Result<?> result = (Result<?>) source;
            Object data = result.getData();
            if (null == data) {
                return false;
            }
            if (data instanceof PageInfo) {
                PageInfo<?> pageInfo = (PageInfo<?>) data;
                List<?> list = pageInfo.getRows();
                if (!CollectionUtils.isEmpty(list)) {
                    Object sourceType = list.get(0);
                    return sourceType instanceof BaseModel;
                }
            } else {
                if (data instanceof List || data instanceof Set || data.getClass().isArray()) {
                    Iterable iterable;
                    if (data.getClass().isArray()) {
                        iterable = Arrays.asList((Object[]) data);
                    } else {
                        iterable = (Iterable) data;
                    }
                    Object sourceType = iterable.iterator().next();
                    return sourceType instanceof BaseModel;
                } else {
                    return data instanceof BaseModel;
                }
            }
        }
        return false;
    }

    /**
     * ???????????????
     *
     * @param source ??????
     * @return object
     */
    private Object handleReturnValue(Object source, Set<String> fieldSet) {
        // ??????
        if (null == source) {
            return null;
        }
        if (null == fieldSet) {
            return source;
        }
        // ????????????
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
            // ???????????????***
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
