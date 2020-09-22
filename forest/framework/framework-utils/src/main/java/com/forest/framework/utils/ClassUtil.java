package com.forest.framework.utils;

import com.forest.framework.common.constant.CommonConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Class工具类
 */
@Slf4j
@SuppressWarnings("unused")
public final class ClassUtil {

    @SneakyThrows
    private ClassUtil() {
        throw new NoSuchMethodException();
    }

    private static void addClasses(Class<?> clazz, List<Class<?>> classes, Class<?> exclude, Class<?> parent) {
        if (log.isDebugEnabled()) {
            log.trace("----");
            if (exclude != null) {
                log.trace("exclude:" + exclude.getName());
            }
            if (parent != null) {
                log.trace("parent:" + parent.getName());
            }
            String str = "class:" + clazz.getName();
            if (clazz.getSuperclass() != null) {
                str += ", super:" + clazz.getSuperclass().getName();
            }
            log.trace(str);
            log.trace("----");
        }
        if (exclude != null && clazz == exclude) {
            return;
        }
        // 如果parent是interface
        if (checkExtendsImplement(clazz, parent)) {
            classes.add(clazz);
        }
    }

    public static boolean checkExtendsImplement(Class<?> sub, Class<?> parent) {
        if (parent == null || parent == Object.class) {
            return true;
        }
        if (parent.isMemberClass() || parent.isAnonymousClass() || parent.isLocalClass()) {
            if (sub.getSuperclass() != parent) {
                return false;
            }
        }
        if (parent.isInterface()) {
            Class<?>[] interfaces = sub.getInterfaces();
            for (Class<?> inter : interfaces) {
                if (inter == parent) {
                    return true;
                }
            }
        }
        return sub.getSuperclass() == parent;
    }

    @SneakyThrows
    private static void findAndAddClassesInPackageByJar(@NonNull String packageName, @NonNull URL url, @NonNull String packageDirName, @NonNull List<Class<?>> classes, Class<?> exclude, Class<?> parent) {
        // 定义一个JarFile，获取jar
        JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entries = jar.entries();
        // 同样的进行循环迭代
        while (entries.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }
            // 如果前半部分和定义的包名相同
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                // 如果以"/"结尾 是一个包
                if (idx != -1) {
                    // 获取包名 把"/"替换成"."
                    packageName = name.substring(0, idx).replace('/', '.');
                }
                // 如果可以迭代下去 并且是一个包
                if (idx != -1) {
                    // 如果是一个.class文件 而且不是目录
                    if (name.endsWith(".class") && !entry.isDirectory()) {
                        // 去掉后面的".class" 获取真正的类名
                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                        // 添加到classes
                        addClasses(Class.forName(packageName + '.' + className), classes, exclude, parent);
                    }
                }
            }
        }
    }

    @SneakyThrows
    private static void findAndAddClassesInPackageByFile(@NonNull String packageName, @NonNull String packagePath, @NonNull List<Class<?>> classes, Class<?> exclude, Class<?> parent) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirFiles = dir.listFiles();
        // 循环所有文件
        if (dirFiles != null) {
            for (File file : dirFiles) {
                // 如果是目录 则继续扫描
                if (file.isDirectory()) {
                    findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), classes, exclude, parent);
                } else {
                    // 如果是java类文件 去掉后面的.class 只留下类名
                    String className = file.getName();
                    if (!className.endsWith(".class")) {
                        log.trace(className + "is not a class");
                    } else {
                        className = className.substring(0, className.length() - 6);
                        if (log.isDebugEnabled()) {
                            log.trace(packageName + '.' + className);
                        }
                        // 添加到集合中去
                        addClasses(Class.forName(packageName + '.' + className), classes, exclude, parent);
                    }
                }
            }
        }
    }

    @SneakyThrows
    private static List<Class<?>> getAllSubClass(@NonNull String packageName, Class<?> exclude, Class<?> parent) {
        // 第一个class类的集合
        List<Class<?>> classes = new ArrayList<>();
        // 获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), CommonConstant.ENCODING);
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, classes, exclude, parent);
                }
                if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    findAndAddClassesInPackageByJar(packageName, url, packageDirName, classes, exclude, parent);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return classes;
    }

    public static List<Class<?>> getAllSubClassByStr(@NonNull String pkg) {
        return getAllSubClass(pkg, null, null);
    }

    public static List<Class<?>> getAllSubClassByStr(@NonNull String pkg, Class<?> exclude) {
        return getAllSubClass(pkg, exclude, null);
    }

    public static List<Class<?>> getAllSubClassByStr(@NonNull String pkg, Class<?> exclude, Class<?> parent) {
        return getAllSubClass(pkg, exclude, parent);
    }

    public static List<Class<?>> getAllSubClass(@NonNull Package pkg) {
        return getAllSubClass(pkg.getName(), null, null);
    }

    public static List<Class<?>> getAllSubClass(@NonNull Package pkg, Class<?> exclude) {
        return getAllSubClass(pkg.getName(), exclude, null);
    }

    public static List<Class<?>> getAllSubClass(@NonNull Package pkg, Class<?> exclude, Class<?> parent) {
        return getAllSubClass(pkg.getName(), exclude, parent);
    }

    public static List<Field> getAllField(@NonNull Class<?>... clazzes) {
        List<Field> list = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int modifier = field.getModifiers();
                if (Modifier.isPrivate(modifier)) {
                    field.setAccessible(true);
                }
                list.add(field);
            }
        }
        return list;
    }

    public static Field getField(Class<?> clazz, String name) {
        Field field;
        try {
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
        int modifier = field.getModifiers();
        if (Modifier.isPrivate(modifier)) {
            field.setAccessible(true);
        }
        return field;
    }

    public static List<Method> getAllMethod(Class<?>... clazzes) {
        List<Method> list = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                int modifier = method.getModifiers();
                if (Modifier.isPrivate(modifier)) {
                    method.setAccessible(true);
                }
                list.add(method);
            }
        }
        return list;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        Method method;
        try {
            method = clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            return null;
        }
        int modifier = method.getModifiers();
        if (Modifier.isPrivate(modifier)) {
            method.setAccessible(true);
        }
        return method;
    }

    public static List<Class<?>> getGenericClass(Field field) {
        if (field == null) {
            return new ArrayList<>();
        }
        List<Class<?>> list = new ArrayList<>();
        Type type = field.getGenericType();
        if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            for (Type t : parameterizedType.getActualTypeArguments()) {
                if (t instanceof Class) {
                    Class<?> clazz = (Class<?>) t;
                    list.add(clazz);
                }
            }
        }
        return list;
    }

    public static boolean isSimpleType(Class<?> clazz) {
        if (clazz == null) {
            return true;
        }
        switch (clazz.getSimpleName().toLowerCase()) {
            case "int":
            case "integer":
            case "long":
            case "short":
            case "byte":
            case "float":
            case "double":
            case "boolean":
            case "char":
            case "character":
            case "date":
            case "timestamp":
            case "string":
            case "biginteger":
            case "bigdecimal":
                return true;
            default:
                return false;
        }
    }

    @SneakyThrows
    public static File getClasspathFile(String fileName) {
        ClassLoader classLoader = ClassUtil.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        Assert.notNull(url, "找不到资源" + fileName);
        String filepath = URLDecoder.decode(url.getFile(), CommonConstant.ENCODING);
        File file = new File(filepath);
        Assert.isTrue(file.exists(), "文件" + file.getAbsolutePath() + "不存在");
        return file;
    }

    public static InputStream getClasspathInputStream(String fileName) {
        ClassLoader classLoader = ClassUtil.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    /**
     * 获取范型的class
     *
     * @param clazz 注入了范型的本类Class
     * @param index 从0开始
     * @return Class
     */
    public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }

    public static Type[] getGenericTypeExceptions(Method method, int index) {
        Type[] exceptionTypes = method.getGenericExceptionTypes();
        int i = 0;
        for (Type type : exceptionTypes) {
            log.trace(String.valueOf(type));
            if (type instanceof ParameterizedType && i == index) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                log.trace(String.valueOf(parameterizedType.getRawType()));
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type t : actualTypeArguments) {
                    log.trace(String.valueOf(t));
                }
                return actualTypeArguments;
            }
            i++;
        }
        return new Type[0];
    }

    public static Type[] getGenericTypeParameters(Method method, int index) {
        Type[] parameterTypes = method.getGenericParameterTypes();
        int i = 0;
        for (Type type : parameterTypes) {
            log.trace(String.valueOf(type));
            if (type instanceof ParameterizedType && i == index) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                log.trace(String.valueOf(parameterizedType.getRawType()));
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type t : actualTypeArguments) {
                    log.trace(String.valueOf(t));
                }
                return actualTypeArguments;
            }
            i++;
        }
        return new Type[0];
    }

    public static Type[] getGenericTypeReturnType(Method method) {
        Type returnType = method.getGenericReturnType();
        log.trace(String.valueOf(returnType));
        if (returnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
            for (Type type : actualTypeArguments) {
                log.trace(String.valueOf(type));
            }
            return actualTypeArguments;
        }
        return new Type[0];
    }

}