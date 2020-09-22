package com.forest.framework.utils;

import com.forest.framework.common.constant.CommonConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 *
 */
@Slf4j
@SuppressWarnings("unused")
public final class ProjectUtil {

    @SneakyThrows
    private ProjectUtil() {
        throw new NoSuchMethodException();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str String
     * @return boolean
     */
    public static boolean strIsNull(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断对象不是null且不是空字符串
     *
     * @param o Object
     * @return 是否不为null且不是空字符串
     */
    public static boolean objectIsNotEmpty(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof String || o instanceof StringBuilder || o instanceof StringBuffer) {
            return !StringUtils.isEmpty(o.toString());
        }
        return true;
    }

    /**
     * null返回""
     *
     * @param str Object
     * @return String
     */
    public static String convertNullToEmpty(Object str) {
        if (str == null) {
            return null;
        }
        return str.toString();
    }

    /**
     * null返回0
     *
     * @param source BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal convertNullToZero(BigDecimal source) {
        if (source == null) {
            return BigDecimal.ZERO;
        }
        return source;
    }

    public static <T> T convertNullToValue(T target, T value) {
        if (target == null) {
            return value;
        }
        return target;
    }

    /**
     * 生成随机的验证码(手机短信验证码)
     *
     * @param num 位数
     * @return 随机数字
     */
    public static String genValidationCode(int num) {
        if (num < 6) {
            num = 6;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int a = (int) (Math.random() * (10 - 1 + 1));
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }

    /**
     * 计算两个经纬度之间的距离
     *
     * @param y1 double
     * @param x1 double
     * @param y2 double
     * @param x2 double
     * @return String
     */
    public static String calcDistance(double y1, double x1, double y2, double x2) {
        double radLat1 = y1 * Math.PI / 180.0;
        double radLat2 = y2 * Math.PI / 180.0;
        double a = radLat1 - radLat2;
        double b = x1 * Math.PI / 180.0 - x2 * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * CommonConstant.EARTH_RADIUS * 1000;
        long result = Math.round(s);
        DecimalFormat dfa = new DecimalFormat("#####0");
        return dfa.format(result);
    }

    /**
     * 将异常堆栈信息转换为字符串
     *
     * @param e 异常对象
     * @return String
     */
    public static String exceptionStack2String(Throwable e) {
        if (e != null) {
            ByteArrayOutputStream traceOutputStream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(traceOutputStream));
            return new String(traceOutputStream.toByteArray(), StandardCharsets.UTF_8);
        }
        return null;
    }

    /**
     * List转String
     *
     * @param list      List
     * @param separator 分隔符
     * @return String
     */
    public static String listToString(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append(separator);
        }
        return sb.substring(0, sb.toString().length() - 1);
    }

    /**
     * String转List
     *
     * @param str       String
     * @param separator 分隔符
     * @return List
     */
    public static List<String> stringToList(String str, String separator) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        Collections.addAll(list, str.split(separator));
        return list;
    }

    /**
     * 获取文件的MD5
     *
     * @param inputStream InputStream
     * @return String
     */
    @SneakyThrows
    public static String getFileMd5(InputStream inputStream) {
        String md5;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer, 0, 1024)) != -1) {
            md.update(buffer, 0, length);
        }
        BigInteger bigInt = new BigInteger(1, md.digest());
        md5 = bigInt.toString(16).toUpperCase();
        return md5;
    }

    /**
     * 连接2个url
     *
     * @param first  String
     * @param second String
     * @return String
     */
    public static String appendUrl(String first, String second) {
        if (StringUtils.isEmpty(first)) {
            return second;
        }
        if (StringUtils.isEmpty(second)) {
            return first;
        }
        // 两个衔接的均有斜杠
        if (first.endsWith(CommonConstant.SPLIT) && second.startsWith(CommonConstant.SPLIT)) {
            return first + second.substring(1);
        }
        // 两个衔接的均没有斜杠
        if (!first.endsWith(CommonConstant.SPLIT) && !second.startsWith(CommonConstant.SPLIT)) {
            return first + CommonConstant.SPLIT + second;
        }
        return first + second;
    }

    /**
     * @param str String
     * @return String
     * @deprecated replace with StringUtil
     */
    @Deprecated
    public static String transferUnderlineToUppercase(String str) {
        if (str == null) {
            return null;
        }
        String[] arr = str.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (i > 0 && s.length() > 0) {
                sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }

    /**
     * @param str String
     * @return String
     * @deprecated replace with StringUtil
     */
    @Deprecated
    public static String transferUppercaseToUnderline(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i > 0 && c >= 'A' && c <= 'Z') {
                sb.append("_").append(String.valueOf(c).toLowerCase());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static InputStream getInputStream(String fileName) {
        String name = processFileName(fileName);
        ClassLoader classLoader = ProjectUtil.class.getClassLoader();
        return classLoader.getResourceAsStream(name);
    }

    /**
     * @param fileName String
     * @return File
     * @deprecated 打成jar包不可用
     */
    @Deprecated
    public static File getFile(String fileName) {
        String name = processFileName(fileName);
        ClassLoader classLoader = ProjectUtil.class.getClassLoader();
        URL url = classLoader.getResource(name);
        assert url != null;
        return new File(url.getFile());
    }

    private static String processFileName(String fileName) {
        assert fileName != null;
        String classpath = "classpath:";
        if (fileName.startsWith(classpath)) {
            fileName = fileName.substring(classpath.length());
        }
        if (fileName.startsWith("/")) {
            fileName = fileName.substring(1);
        }
        return fileName;
    }

    /**
     * getClientRealAddress
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getClientRealAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String address = request.getHeader("x-forwarded-for");
        if (address == null || address.length() == 0 || unknown.equalsIgnoreCase(address)) {
            address = request.getHeader("Proxy-Client-IP");
        }
        if (address == null || address.length() == 0 || unknown.equalsIgnoreCase(address)) {
            address = request.getHeader("WL-Proxy-Client-IP");
        }
        if (address == null || address.length() == 0 || unknown.equalsIgnoreCase(address)) {
            address = request.getHeader("HTTP_CLIENT_IP");
        }
        if (address == null || address.length() == 0 || unknown.equalsIgnoreCase(address)) {
            address = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (address == null || address.length() == 0 || unknown.equalsIgnoreCase(address)) {
            address = request.getRemoteAddr();
        }
        return address;
    }

    @SneakyThrows
    public static String getIp(Environment env) {
        Assert.notNull(env, "参数错误");
        String ip = env.getProperty("eureka.instance.ip-address");
        if (StringUtils.isEmpty(ip)) {
            ip = env.getProperty("spring.cloud.client.ip-address");
        }
        InetAddress address = InetAddress.getLocalHost();
        if (StringUtils.isEmpty(ip)) {
            ip = address.getHostAddress();
        }
        Assert.hasText(ip, "获取本机IP失败");
        return ip;
    }

    public static String getPort(Environment env) {
        Assert.notNull(env, "参数错误");
        String port = env.getProperty("server.port");
        Assert.hasText(port, "配置server.port不存在");
        return port;
    }

    public static void afterRun(ApplicationContext applicationContext) {
        afterRun(applicationContext, true, true, true);
    }

    public static void afterRun(ApplicationContext applicationContext, boolean showIp) {
        afterRun(applicationContext, true, true, showIp);
    }

    public static void afterRun(ApplicationContext applicationContext, boolean showNetwork, boolean showIp) {
        afterRun(applicationContext, true, showNetwork, showIp);
    }

    @SneakyThrows
    public static void afterRun(ApplicationContext applicationContext, boolean showHttpMessageConverter, boolean showNetwork, boolean showIp) {
        String spit = "------";
        // 打印HttpMessageConverter
        if (showHttpMessageConverter) {
            log.info(spit);
            List<HttpMessageConverter<?>> list = applicationContext.getBean(HttpMessageConverters.class).getConverters();
            for (HttpMessageConverter<?> hmc : list) {
                log.info(hmc.getClass().toString());
            }
        }
        // 打印所有网卡
        if (showNetwork) {
            log.info(spit);
            for (Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements(); ) {
                NetworkInterface ifc = nis.nextElement();
                log.info(ifc.getIndex() + "," + ifc.getName() + "," + ifc.getDisplayName() + "," + ifc.getInterfaceAddresses());
            }
        }
        // 打印本应用的ip和端口，Bean的数量
        if (showIp) {
            log.info(spit);
            Environment env = applicationContext.getEnvironment();
            String app = env.getProperty("spring.application.name");
            String ip = getIp(env);
            String port = getPort(env);
            log.info("app:" + app + ",ip:" + ip + ",port:" + port + ",beans:" + applicationContext.getBeanDefinitionCount());
        }
        System.out.println("Congratulations!");
    }

    public static String getContext(String profiles) {
        switch (profiles) {
            case "dev":
            case "test":
            case "sit":
                return "/g4TD";
            case "bug":
                return "/g4BUG";
            case "it":
                return "/g4IT";
            case "pre":
                return "/g4-pre";
            case "master":
            case "production":
                return "/g4";
            default:
                throw new RuntimeException("not supported");
        }
    }

    public static String getUrl(String profiles, String app, String path) {
        StringBuilder sb = new StringBuilder();
        switch (profiles) {
            case "production":
                sb.append("http://scm.hq.cmcc/g4/gateway");
                break;
            case "sit":
                sb.append("http://scmqas.hq.cmcc/g4TD/gateway");
                break;
            case "bug":
                sb.append("http://scmqas.hq.cmcc/g4BUG/gateway");
                break;
            case "it":
                sb.append("http://scmqas.hq.cmcc/g4IT/gateway");
                break;
            case "master":
                sb.append("http://scmqas.hq.cmcc/g4/gateway");
                break;
            case "pre":
                sb.append("http://scmqas.hq.cmcc/g4-pre/gateway");
                break;
            case "dev":
                sb.append("http://scmjd31.novalocal.com:8100/g4TD/gateway");
                break;
            case "test":
                sb.append("http://scmjd31.novalocal.com:8105/g4TD/gateway");
                break;
            default:
                throw new RuntimeException("not supported");
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        sb.append("/").append(app).append(path);
        return sb.toString();
    }

}
