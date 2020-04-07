package com.stream.garden.framework.web.json;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author garden
 * @date 2020-04-07 19:43
 */
public class HandlerJsonViewSerializer extends JsonSerializer<JsonView> {

    /**
     * Cached results from expensive (pure) methods
     */
    private final Memoizer memoizer;

    /**
     * Map of custom serializers to take into account when serializing fields.
     */
    private Map<Class<?>, JsonSerializer<Object>> customSerializersMap = null;

    public HandlerJsonViewSerializer() {
        this(1024);
    }

    public HandlerJsonViewSerializer(int maxCacheSize) {
        this.memoizer = new Memoizer(maxCacheSize);
    }

    /**
     * Registering custom serializer allows to the JSonView to deal with custom serializations for certains field types.<br>
     * This way you could register for instance a JODA serialization as  a DateTimeSerializer. <br>
     * Thus, when JSonView find a field of that type (DateTime), it will delegate the serialization to the serializer specified.<br>
     * Example:<br>
     * <code>
     * JsonViewSupportFactoryBean bean = new JsonViewSupportFactoryBean( mapper );
     * bean.registerCustomSerializer( DateTime.class, new DateTimeSerializer() );
     * </code>
     *
     * @param <T>     Type class of the serializer
     * @param cls     {@link Class} The class type you want to add a custom serializer
     * @param forType {@link JsonSerializer} The serializer you want to apply for that type
     */
    @SuppressWarnings("unchecked")
    public <T> void registerCustomSerializer(Class<T> cls, JsonSerializer<T> forType) {
        if (customSerializersMap == null) {
            customSerializersMap = new HashMap<>();
        }

        if (cls == null) {
            throw new IllegalArgumentException("Class must not be null");
        } else if (cls.equals(JsonView.class)) {
            throw new IllegalArgumentException("Class cannot be " + JsonView.class);
        } else if (customSerializersMap.containsKey(cls)) {
            throw new IllegalArgumentException("Class " + cls + " already has a serializer registered (" + customSerializersMap.get(cls) + ")");
        }

        customSerializersMap.put(cls, (JsonSerializer<Object>) forType);
    }

    /**
     * Unregister a previously registered serializer. @see registerCustomSerializer
     *
     * @param cls The class type the serializer was registered for
     */
    public void unregisterCustomSerializer(Class<?> cls) {
        if (customSerializersMap != null) {
            customSerializersMap.remove(cls);
        }
    }


    @Override
    public void serialize(JsonView result, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
        new JsonWriter(serializers, jgen, result).writeByFirst(null, result.getValue());
    }

    class JsonWriter {
        final SerializerProvider serializerProvider;
        final JsonGenerator jgen;
        final JsonView result;
        Stack<String> path = new Stack<>();
        String currentPath = "";
        Match currentMatch = null;
        Field referringField = null;
        private HandlerJsonViewFilter handlerJsonViewFilter;

        JsonWriter(SerializerProvider serializerProvider, JsonGenerator jgen, JsonView result) {
            this.serializerProvider = serializerProvider;
            this.jgen = jgen;
            this.result = result;

            if (result instanceof HandlerJsonView) {
                this.handlerJsonViewFilter = ((HandlerJsonView) result).getHandlerJsonViewFilter();
            }
        }

        //internal use only to encapsulate what the current state was
        private JsonWriter(JsonGenerator jgen, JsonView result, Match currentMatch, SerializerProvider serializerProvider) {
            this.jgen = jgen;
            this.result = result;
            this.currentMatch = currentMatch;
            this.serializerProvider = serializerProvider;

            if (result instanceof HandlerJsonView) {
                this.handlerJsonViewFilter = ((HandlerJsonView) result).getHandlerJsonViewFilter();
            }
        }

        //internal use only to encapsulate what the current state was
        private JsonWriter(JsonGenerator jgen, JsonView result, Match currentMatch,
                           String currentPath, Stack<String> path, Field referringField, SerializerProvider serializerProvider) {
            this.jgen = jgen;
            this.result = result;
            this.currentMatch = currentMatch;
            this.currentPath = currentPath;
            this.referringField = referringField;
            this.path = path;
            this.serializerProvider = serializerProvider;

            if (result instanceof HandlerJsonView) {
                this.handlerJsonViewFilter = ((HandlerJsonView) result).getHandlerJsonViewFilter();
            }
        }

        boolean writePrimitive(Object obj) throws IOException {
            if (obj instanceof String) {
                jgen.writeString((String) obj);
            } else if (obj instanceof Integer) {
                jgen.writeNumber((Integer) obj);
            } else if (obj instanceof Long) {
                jgen.writeNumber((Long) obj);
            } else if (obj instanceof Short) {
                jgen.writeNumber((Short) obj);
            } else if (obj instanceof Double) {
                jgen.writeNumber((Double) obj);
            } else if (obj instanceof Float) {
                jgen.writeNumber((Float) obj);
            } else if (obj instanceof Character) {
                jgen.writeNumber((Character) obj);
            } else if (obj instanceof Byte) {
                jgen.writeNumber((Byte) obj);
            } else if (obj instanceof Boolean) {
                jgen.writeBoolean((Boolean) obj);
            } else if (obj == null) {
                jgen.writeNull();
            } else if (obj instanceof BigDecimal) {
                jgen.writeNumber((BigDecimal) obj);
            } else {
                return false;
            }
            return true;
        }

        boolean writeSpecial(Object obj) throws IOException {
            if (obj instanceof Date) {
                serializerProvider.defaultSerializeDateValue((Date) obj, jgen);
            } else if (obj instanceof Temporal) {
                serializerProvider.defaultSerializeValue(obj, jgen);
            } else if (obj instanceof URL) {
                jgen.writeString(obj.toString());
            } else if (obj instanceof URI) {
                jgen.writeString(obj.toString());
            } else if (obj instanceof UUID) {
                jgen.writeString(obj.toString());
            } else if (obj instanceof Class) {
                jgen.writeString(((Class) obj).getCanonicalName());
            } else {
                return false;
            }
            return true;
        }

        boolean writeEnum(Object obj) throws IOException {
            if (obj.getClass().isEnum()) {
                jgen.writeString(((Enum) obj).name());
            } else {
                return false;
            }
            return true;
        }

        @SuppressWarnings("unchecked")
        boolean writeList(Object obj) throws IOException {
            if (obj instanceof List || obj instanceof Set || obj.getClass().isArray()) {
                Iterable iter;
                if (obj.getClass().isArray()) {
                    if (obj instanceof byte[]) {
                        jgen.writeBinary((byte[]) obj);
                        return true;
                    } else {
                        iter = convertArray(obj);
                    }
                } else {
                    iter = (Iterable<Object>) obj;
                }

                jgen.writeStartArray();
                for (Object o : iter) {
                    new JsonWriter(jgen, result, currentMatch, currentPath, path, referringField, serializerProvider).writeByFirst(null, o);
                }
                jgen.writeEndArray();
            } else {
                return false;
            }
            return true;
        }

        @SuppressWarnings("unchecked")
        Iterable convertArray(Object obj) {
            Iterable iter;
            if (obj instanceof int[]) {
                int[] arr = (int[]) obj;
                iter = new ArrayList<>();
                for (int v : arr) {
                    ((List<Integer>) iter).add(v);
                }
            } else if (obj instanceof double[]) {
                double[] arr = (double[]) obj;
                iter = new ArrayList<>();
                for (double v : arr) {
                    ((List<Double>) iter).add(v);
                }
            } else if (obj instanceof float[]) {
                float[] arr = (float[]) obj;
                iter = new ArrayList<>();
                for (float v : arr) {
                    ((List<Float>) iter).add(v);
                }
            } else if (obj instanceof long[]) {
                long[] arr = (long[]) obj;
                iter = new ArrayList<>();
                for (long v : arr) {
                    ((List<Long>) iter).add(v);
                }
            } else if (obj instanceof short[]) {
                short[] arr = (short[]) obj;
                iter = new ArrayList<>();
                for (short v : arr) {
                    ((List<Short>) iter).add(v);
                }
            } else if (obj instanceof char[]) {
                char[] arr = (char[]) obj;
                iter = new ArrayList<>();
                for (char v : arr) {
                    ((List<Character>) iter).add(v);
                }
            } else if (obj instanceof boolean[]) {
                boolean[] arr = (boolean[]) obj;
                iter = new ArrayList<>();
                for (boolean v : arr) {
                    ((List<Boolean>) iter).add(v);
                }
            } else {
                iter = Arrays.asList((Object[]) obj);
            }
            return iter;
        }

        @SuppressWarnings("unchecked")
        boolean writeMap(Object obj) throws IOException {
            if (obj instanceof Map) {
                Map<Object, Object> map = (Map<Object, Object>) obj;

                jgen.writeStartObject();
                for (Object key : map.keySet()) {
                    jgen.writeFieldName(key.toString());
                    new JsonWriter(jgen, result, currentMatch, serializerProvider).writeByFirst(null, map.get(key));
                }
                jgen.writeEndObject();
            } else {
                return false;
            }
            return true;
        }

        void writeObject(Object obj) throws IOException {
            jgen.writeStartObject();

            Class cls = obj.getClass();
            while (!cls.equals(Object.class)) {
                Field[] fields = getDeclaredFields(cls);
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        Object val = field.get(obj);

                        if (valueAllowed(val, obj.getClass()) && fieldAllowed(field, obj.getClass())) {
                            String name = getFieldName(field);
                            jgen.writeFieldName(name);

                            if (customSerializersMap != null && val != null) {
                                JsonSerializer<Object> serializer = customSerializersMap.get(val.getClass());
                                if (serializer != null) {
                                    serializer.serialize(val, jgen, serializerProvider);
                                } else {
                                    new JsonWriter(jgen, result, currentMatch, currentPath, path, field, serializerProvider).write(name, val);
                                }
                            } else {
                                new JsonWriter(jgen, result, currentMatch, currentPath, path, field, serializerProvider).write(name, val);
                            }
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                cls = cls.getSuperclass();
            }

            jgen.writeEndObject();
        }

        void writeObjectByFirst(Object obj) throws IOException {
            jgen.writeStartObject();

            Class cls = obj.getClass();
            while (!cls.equals(Object.class)) {
                Field[] fields = getDeclaredFields(cls);
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        Object val = field.get(obj);

                        if (valueAllowed(val, obj.getClass()) && fieldAllowed(field, obj.getClass())) {
                            String name = getFieldName(field);
                            boolean skip = false;
                            if (null != handlerJsonViewFilter) {
                                // 返回true，跳过这个字段的写入json，返回false，继续写入json
                                skip = handlerJsonViewFilter.filter(currentPath, name, val, jgen);
                            }
                            if (!skip) {
                                jgen.writeFieldName(name);
                                if (customSerializersMap != null && val != null) {
                                    JsonSerializer<Object> serializer = customSerializersMap.get(val.getClass());
                                    if (serializer != null) {
                                        serializer.serialize(val, jgen, serializerProvider);
                                    } else {
                                        new JsonWriter(jgen, result, currentMatch, currentPath, path, field, serializerProvider).writeByFirst(name, val);
                                    }
                                } else {
                                    new JsonWriter(jgen, result, currentMatch, currentPath, path, field, serializerProvider).writeByFirst(name, val);
                                }
                            }
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                cls = cls.getSuperclass();
            }

            jgen.writeEndObject();
        }

        boolean valueAllowed(Object value, Class cls) {
            return value != null
                    || (serializerProvider.getConfig() != null
                    && serializerProvider.getConfig().getSerializationInclusion() == JsonInclude.Include.ALWAYS
                    && getAnnotation(cls, JsonSerialize.class) == null)
                    || (getAnnotation(cls, JsonSerialize.class) != null
                    && readClassAnnotation(cls, JsonSerialize.class, "include") == JsonSerialize.Inclusion.ALWAYS);
        }

        @SuppressWarnings("unchecked")
        boolean fieldAllowed(Field field, Class declaringClass) {
            String name = field.getName();
            String prefix = currentPath.length() > 0 ? currentPath + "." : "";
            if (Modifier.isStatic(field.getModifiers())) {
                return false;
            }

            //search for matching class
            Match match = null;
            Class cls = declaringClass;
            while (!cls.equals(Object.class) && match == null) {
                match = result.getMatch(cls);

                //search for any matching interfaces as well, stopping on the first one
                if (match == null && getInterfaces(cls) != null) {
                    for (Class iface : getInterfaces(cls)) {
                        match = result.getMatch(iface);
                        if (match != null) {
                            break;
                        }
                    }
                }
                cls = cls.getSuperclass();
            }
            if (match == null) {
                match = currentMatch;
            } else {
                prefix = "";
            }

            //if there is a match, respect it
            if (match != null) {

                if (currentMatch == null) {
                    currentMatch = match;
                }

                int included = containsMatchingPattern(match.getIncludes(), prefix + name, true);
                int excluded = containsMatchingPattern(match.getExcludes(), prefix + name, false);

        /*
        The logic for this is a little complex. We're dealing with ternary logic to
        properly handle wildcard matches. We want matches made with wildcards to be
        overruled by matches without them.
         */
                if (included == 1) {
                    return true;
                } else if (excluded == 1) {
                    return false;
                } else if (included == 0) {
                    return true;
                } else if (excluded == 0) {
                    return false;
                } else {
                    return !annotatedWithIgnore(field);
                }
            } else {
                //else, respect JsonIgnore only
                return !annotatedWithIgnore(field);
            }
        }

        void writeByFirst(String fieldName, Object value) throws IOException {
            if (fieldName != null) {
                path.push(fieldName);
                updateCurrentPath();
            }

            //try to handle all primitives/special cases before treating this as json object
            if (!writePrimitive(value) && !writeSpecial(value) && !writeEnum(value) && !writeList(value) && !writeMap(value)) {
                writeObjectByFirst(value);
            }

            if (fieldName != null) {
                path.pop();
                updateCurrentPath();
            }
        }

        void write(String fieldName, Object value) throws IOException {
            if (fieldName != null) {
                path.push(fieldName);
                updateCurrentPath();
            }

            //try to handle all primitives/special cases before treating this as json object
            if (!writePrimitive(value) && !writeSpecial(value) && !writeEnum(value) && !writeList(value) && !writeMap(value)) {
                writeObject(value);
            }

            if (fieldName != null) {
                path.pop();
                updateCurrentPath();
            }
        }

        void updateCurrentPath() {
            StringBuilder builder = new StringBuilder();
            for (String s : path) {
                builder.append(".");
                builder.append(s);
            }
            currentPath = builder.length() > 0 ? builder.toString().substring(1) : "";
        }

        @SuppressWarnings("unchecked")
        <E> E readClassAnnotation(Class cls, Class annotationType, String methodName) {
            try {
                for (Annotation an : getAnnotations(cls)) {
                    Class<? extends Annotation> type = an.annotationType();
                    if (an.annotationType().equals(annotationType)) {
                        for (Method method : type.getDeclaredMethods()) {
                            if (method.getName().equals(methodName)) {
                                return (E) method.invoke(an, (Object[]) null);
                            }
                        }
                        throw new IllegalArgumentException("Method " + methodName + " not found on annotation " + annotationType);
                    }
                }
                throw new IllegalArgumentException("Annotation " + annotationType + " not found on class " + cls);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Returns one of the following values:
         * <pre>
         * -1: No match found
         *  0: Wildcard-based match
         *  1: Non-wildcard match
         * </pre>
         * <p>
         * This method is memoized to speed up execution time
         */
        int containsMatchingPattern(Set<String> values, String pattern, boolean matchPrefix) {
            return memoizer.matches(values, pattern, matchPrefix, () -> {
                int match = -1;
                for (String val : values) {
                    String replaced = val.replaceAll("\\.", "\\\\.").replaceAll("\\*", ".*");
                    if (Pattern.compile(replaced).matcher(pattern).matches() || (matchPrefix && val.startsWith(pattern + "."))) {
                        match = replaced.contains("*") ? 0 : 1;
                        break;
                    }
                }
                return match;
            });
        }

        /**
         * Returns a boolean indicating whether the provided field is annotated with
         * some form of ignore. This method is memoized to speed up execution time
         */
        boolean annotatedWithIgnore(Field f) {
            return memoizer.ignoreAnnotations(f, () -> {
                JsonIgnore jsonIgnore = getAnnotation(f, JsonIgnore.class);
                JsonIgnoreProperties classIgnoreProperties = getAnnotation(f.getDeclaringClass(), JsonIgnoreProperties.class);
                JsonIgnoreProperties fieldIgnoreProperties = null;
                boolean backReferenced = false;

                //make sure the referring field didn't specify properties to ignore
                if (referringField != null) {
                    fieldIgnoreProperties = getAnnotation(referringField, JsonIgnoreProperties.class);
                }

                //make sure the referring field didn't specify a backreference annotation
                if (getAnnotation(f, JsonBackReference.class) != null && referringField != null) {
                    for (Field lastField : getDeclaredFields(referringField.getDeclaringClass())) {
                        JsonManagedReference fieldManagedReference = getAnnotation(lastField, JsonManagedReference.class);
                        if (fieldManagedReference != null && lastField.getType().equals(f.getDeclaringClass())) {
                            backReferenced = true;
                            break;
                        }
                    }
                }

                return (jsonIgnore != null && jsonIgnore.value()) ||
                        (classIgnoreProperties != null && Arrays.asList(classIgnoreProperties.value()).contains(f.getName())) ||
                        (fieldIgnoreProperties != null && Arrays.asList(fieldIgnoreProperties.value()).contains(f.getName())) ||
                        backReferenced;
            });
        }

        private Class<?>[] getInterfaces(Class cls) {
            return cls.getInterfaces();
        }

        private Field[] getDeclaredFields(Class cls) {
            return cls.getDeclaredFields();
        }

        private Annotation[] getAnnotations(Class cls) {
            return cls.getAnnotations();
        }

        private Annotation[] getAnnotations(Field field) {
            return field.getAnnotations();
        }

        private String getFieldName(Field field) {
            return memoizer.fieldName(field, () -> {
                JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                if (jsonProperty != null && jsonProperty.value().length() > 0) {
                    return jsonProperty.value();
                } else {
                    return field.getName();
                }
            });
        }

        @SuppressWarnings("unchecked")
        private <T extends Annotation> T getAnnotation(Class cls, Class<T> annotation) {
            Annotation[] annotations = getAnnotations(cls);
            if (annotations != null) {
                for (Annotation a : annotations) {
                    if (a.annotationType().equals(annotation)) {
                        return (T) a;
                    }
                }
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        private <T extends Annotation> T getAnnotation(Field field, Class<T> annotation) {
            Annotation[] annotations = getAnnotations(field);
            if (annotations != null) {
                for (Annotation a : annotations) {
                    if (a.annotationType().equals(annotation)) {
                        return (T) a;
                    }
                }
            }
            return null;
        }

        //synchronizes on the provided map to provide threadsafety
        private <T, V> Map<T, V> fitToMaxSize(Map<T, V> map) {
            synchronized (map) {
                if (map.size() > 1) {
                    map.remove(map.keySet().iterator().next());
                }
            }
            return map;
        }
    }
}
