package com.stream.garden.framework.web.util;

import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.constant.GlobalConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtHelper {

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security)).parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    public static Claims parseJWT(HttpServletRequest request, String base64Security) {
        try {
            String authHeader = request.getHeader(GlobalConstant.HEADER_AUTHORIZATION);
            if (StringUtils.isEmpty(authHeader)) {
                authHeader = CookieUtil.getUid(request, GlobalConstant.HEADER_AUTHORIZATION);
            }
            if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith(GlobalConstant.HEADER_AUTHORIZATION_BEARER)) {
                return null;
            }
            String token = authHeader.substring(7);
            return JwtHelper.parseJWT(token, base64Security);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isLogin(HttpServletRequest request, String base64Security) {
        return null != parseJWT(request, base64Security);
    }

    public static String createJWT(String name, String userId, String role, GlobalConfig.JwtConfig jwtConfig) {
        return createJWT(name, userId, role, jwtConfig.getClientId(), jwtConfig.getName(), jwtConfig.getExpiresSecond(), jwtConfig.getBase64Secret());
    }

    /**
     * 构建jwt
     */
    public static String createJWT(String name, String userId, String role, String audience, String issuer, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("role", role).claim("unique_name", name).claim("userid", userId).setIssuer(issuer).setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        // 添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + (TTLMillis * 1000);
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        // 生成JWT
        return builder.compact();
    }
}
