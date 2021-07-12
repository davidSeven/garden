package com.sky.framework.interceptor.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2020-10-28 028 9:41
 */
public final class IpUtil {

    /**
     * getClientRealAddress
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getClientRealAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String address = request.getHeader("x-forwarded-for");
        if (address != null && address.length() != 0 && !unknown.equalsIgnoreCase(address)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (address.contains(",")) {
                address = address.split(",")[0];
            }
        }
        if (address == null || address.length() == 0 || unknown.equalsIgnoreCase(address)) {
            address = request.getHeader("Gateway-X-Access-IP");
        }
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
}
