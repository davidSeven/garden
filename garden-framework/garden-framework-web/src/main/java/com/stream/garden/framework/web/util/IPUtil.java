package com.stream.garden.framework.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author garden
 * @date 2019-07-20 9:18
 */
public class IPUtil {
    public static final Logger logger = LoggerFactory.getLogger(IPUtil.class);
    private static final String UNKNOWN = "unknown";

    private IPUtil() {
    }

    /**
     *  
     * 获取IP地址
     *
     * @param request request
     * @return ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        logger.debug("--- get ip address");
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            ip = getLocalHost(ip);
        }
        logger.debug("--- ip address : [{}]", ip);
        return ip;
    }

    /**
     * 获取到本机的ip
     *
     * @param ip ip
     * @return ip
     */
    private static String getLocalHost(String ip) {
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
            //根据网卡取本机配置的IP
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                logger.error(e.getMessage(), e);
            }
            if (null != inetAddress) {
                ip = inetAddress.getHostAddress();
            }
        }
        return ip;
    }
}
