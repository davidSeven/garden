package com.stream.forest.filter;

import com.stream.forest.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * SSO Token校验与白名单校验过滤器<br>
 * 该过滤器为全局过滤，对所有经过Gateway的访问生效<br>
 */
@Slf4j
public class GlobalAuthFilter implements GlobalFilter, Ordered {
    /**
     * Cookie中的SSOToken的键值、同时也用于HTTP Header中作为键值<br>
     */
    public static final String LOGIN_TOKEN = "AUTHSSOTGC";
    /**
     * Redis中作为用户鉴权信息<br>
     */
    public static final String LOGIN_USER_ID = "loginUserId";
    public static final String LOGIN_ADDR_CODE = "loginAddrCode";
    public static final String LOGIN_IP = "loginIp";
    /**
     * Redis中用作设置允许访问的URI/IP白名单的键值<br>
     */
    private static final String REDIS_KEY_AUTH_WHITE_NAME_LIST_URI_PATH = "SCM_ENV_CACHE:GlobalAuthFilter:authWhiteNameListUriPath";
    private static final String REDIS_KEY_AUTH_WHITE_NAME_LIST_ADDRESS = "SCM_ENV_CACHE:GlobalAuthFilter:authWhiteNameListAddress";

    /**
     * Redis访问实例<br>
     */
//    @Autowired
//    @Qualifier("userAuthRedisTemplate")
//    private RedisTemplate<String, String> redisTemplate;

    /**
     * Token解密加密密钥<br>
     */
    @Value("${info.config.authTokenSecret}")
    private String authTokenSecret;

    /**
     * 允许访问地址的白名单<br>
     * 限制访问来源时使用该配置项<br>
     */
    @Value("#{'${info.config.authWhiteNameListAddress}'.split(',')}")
    private List<String> authWhiteNameListAddress;

    /**
     * 允许访问的URI白名单<br>
     * 对外开放服务访问时使用该配置<br>
     */
    @Value("#{'${info.config.authWhiteNameListUriPath}'.split(',')}")
    private List<String> authWhiteNameListUriPath;

    /**
     * 过滤优先级<br>
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 过滤逻辑的实现<br>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String redisConfigOfAuthWhiteNameListUriPath = redisTemplate.opsForValue().get(REDIS_KEY_AUTH_WHITE_NAME_LIST_URI_PATH);
//        if (StringUtils.isNotBlank(redisConfigOfAuthWhiteNameListUriPath)) {
//            authWhiteNameListUriPath = Arrays.asList(redisConfigOfAuthWhiteNameListUriPath.split(","));
//        }
//        String redisConfigOfAuthWhiteNameListAddress = redisTemplate.opsForValue().get(REDIS_KEY_AUTH_WHITE_NAME_LIST_ADDRESS);
//        if (StringUtils.isNotBlank(redisConfigOfAuthWhiteNameListAddress)) {
//            authWhiteNameListAddress = Arrays.asList(redisConfigOfAuthWhiteNameListAddress.split(","));
//        }

        String uri = exchange.getRequest().getURI().toString();
        String path = exchange.getRequest().getPath().value();
        log.debug(uri);
        log.debug(path);
        String address = getClientRealAddress(exchange);
        // 进行路径白名单验证
        if (authWhiteNameListUriPath != null) {
            for (String e : authWhiteNameListUriPath) {
                e = e.trim();
                if (e.endsWith("**")) {
                    e = e.substring(0, e.indexOf("**"));
                    if (path.startsWith(e)) {
                        log.info("通过路径白名单验证,IP{},URI{}:", address, uri);
                        return chain.filter(exchange);
                    }
                } else if (path.equalsIgnoreCase(e)) {
                    log.info("通过路径白名单验证,IP{},URI{}:", address, uri);
                    return chain.filter(exchange);
                }
            }
        }
        // 进行地址白名单验证
        if (StringUtils.isNotBlank(address) && !address.equalsIgnoreCase("unknown") && authWhiteNameListAddress != null) {
            for (String e : authWhiteNameListAddress) {
                e = e.trim();
                if (e.endsWith("**")) {
                    e = e.substring(0, e.indexOf("**"));
                    if (address.startsWith(e)) {
                        log.info("通过地址白名单验证,IP{},URI{}:", address, uri);
                        return chain.filter(exchange);
                    }
                } else if (address.equalsIgnoreCase(e)) {
                    log.info("通过地址白名单验证,IP{},URI{}:", address, uri);
                    return chain.filter(exchange);
                }
            }
        }
        if (StringUtils.isBlank(authTokenSecret)) {
            // 如果未设定鉴权密钥，视为不需要鉴权
            log.info("未设定鉴权密钥,IP{},URI{}:", address, uri);
            return chain.filter(exchange);
        }

        // 读取鉴权信息
        Map<String, String> userData = getAuthorizedUserData(uri, getSSOTokenStr(exchange));
        if (userData == null) {
            log.error("鉴权失败,用户信息不存在,address:{},uri:{}", address, uri);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        if (!StringUtils.isNotEmpty(address) && userData.containsKey(LOGIN_USER_ID)) {
            log.error("鉴权失败,用户信息异常,address:{},uri:{},userData:{}", address, uri, userData);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String loginAddress = userData.get(LOGIN_IP);
        if (StringUtils.isNotEmpty(address) && StringUtils.isNotEmpty(loginAddress) && !loginAddress.equals("*") && !address.equalsIgnoreCase(loginAddress)) {
            log.error("鉴权失败,用户IP异常,address:{},loginAddress:{},uri:{},userData:{}", address, loginAddress, uri, userData);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 重新设置转发的Header属性
        String loginUserId = userData.get(LOGIN_USER_ID);
        String loginAddrCode = userData.get(LOGIN_ADDR_CODE);
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set(LOGIN_USER_ID, loginUserId);
            httpHeader.set(LOGIN_ADDR_CODE, loginAddrCode);
            httpHeader.set(LOGIN_TOKEN, userData.get(LOGIN_TOKEN));
        };
        ServerHttpRequest newRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).response(exchange.getResponse()).build();
        log.info("鉴权通过,IP{},URI{}:", address, uri);
        return chain.filter(newExchange);
    }

    /**
     * 验证用户是否经过SSOToken的鉴权并获取鉴权信息<br>
     *
     * @param uri      uri
     * @param ssoToken ssoToken
     * @return Map
     */
    @SuppressWarnings("unchecked")
    protected Map<String, String> getAuthorizedUserData(String uri, String ssoToken) {
        if (StringUtils.isBlank(ssoToken)) {
            log.error("鉴权失败,SSOToken为空,uri:" + uri);
            return null;
        }
        try {
            // 解密SSOToken
            ssoToken = AesUtil.decrypt(ssoToken, authTokenSecret);
        } catch (Exception e) {
            log.error("鉴权失败,SSOToken解密异常:" + ssoToken + ",uri:" + uri, e);
        }
        if (StringUtils.isBlank(ssoToken)) {
            log.error("鉴权失败,SSOToken为空,uri:" + uri);
            return null;
        }

        Map<String, String> userMap = null;
        // 拼接Redis Key的命名规则
        String key = "AUTH:SSO:" + ssoToken;
        try {
//            String json = redisTemplate.opsForValue().get(key);
//            userMap = JSON.parseObject(json, HashMap.class);
            if (null == userMap) {
                userMap = new HashMap<>();
            }
            userMap.put(LOGIN_TOKEN, ssoToken);
        } catch (Exception e) {
            log.error("鉴权信息解析失败,key:" + key + ",uri:" + uri, e);
        }
        return userMap;
    }

    /**
     * 从Cookie中读取SSO Token
     *
     * @param exchange exchange
     * @return String
     */
    protected String getSSOTokenStr(ServerWebExchange exchange) {
        String ssoToken = null;
        List<String> cookies = exchange.getRequest().getHeaders().get("Cookie");
        if (cookies != null) {
            for (String cookieStr : cookies) {
                cookieStr = cookieStr.trim();
                if (cookieStr.contains("AUTHSSOTGC=") && cookieStr.contains("; ")) {
                    // Cookie的格式为单个字串且各个项目间使用"; "分隔时进入该处理逻辑
                    String[] parts = cookieStr.split("; ");
                    for (String part : parts) {
                        part = part.trim();
                        if (part.startsWith("AUTHSSOTGC=")) {
                            ssoToken = part.substring(part.indexOf("=") + 1);
                            break;
                        }
                    }
                    if (ssoToken != null) {
                        break;
                    }
                } else if (cookieStr.startsWith("AUTHSSOTGC=")) {
                    // Cookie的格式为多个项目时进入该处理逻辑
                    ssoToken = cookieStr.substring(cookieStr.indexOf("=") + 1);
                    break;
                }
            }
        }
        if (ssoToken == null) {
            // Cookie无法获取token的情形，尝试从HttpRequest参数中获取
            ssoToken = exchange.getRequest().getQueryParams().getFirst(LOGIN_TOKEN);
            if (StringUtils.isBlank(ssoToken)) {
                ssoToken = exchange.getRequest().getHeaders().getFirst(LOGIN_TOKEN);
            }
        }
        return ssoToken;
    }

    /**
     * 获取最初的客户端地址<br>
     *
     * @param exchange exchange
     * @return String
     */
    protected String getClientRealAddress(ServerWebExchange exchange) {
        String unknown = "unknown";
        String ipAddr = exchange.getRequest().getHeaders().getFirst("x-forwarded-for");
        if (ipAddr == null || ipAddr.length() == 0 || unknown.equalsIgnoreCase(ipAddr)) {
            ipAddr = exchange.getRequest().getHeaders().getFirst("Proxy-Client-IP");
        }
        if (ipAddr == null || ipAddr.length() == 0 || unknown.equalsIgnoreCase(ipAddr)) {
            ipAddr = exchange.getRequest().getHeaders().getFirst("WL-Proxy-Client-IP");
        }
        if (ipAddr == null || ipAddr.length() == 0 || unknown.equalsIgnoreCase(ipAddr)) {
            ipAddr = exchange.getRequest().getHeaders().getFirst("HTTP_CLIENT_IP");
        }
        if (ipAddr == null || ipAddr.length() == 0 || unknown.equalsIgnoreCase(ipAddr)) {
            ipAddr = exchange.getRequest().getHeaders().getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddr == null || ipAddr.length() == 0 || unknown.equalsIgnoreCase(ipAddr)) {
            InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
            if (null != remoteAddress) {
                ipAddr = remoteAddress.getHostName();
            }
        }
        return ipAddr;
    }

}
