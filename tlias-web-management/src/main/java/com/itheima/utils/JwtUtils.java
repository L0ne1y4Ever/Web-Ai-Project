package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 固定密钥（Base64 编码过的字符串）
    private static final String SECRET_KEY = "bG9uZWx5NGV2ZXI=";

    // 12 小时过期
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 12;

    /**
     * 生成 JWT 令牌
     * @param claims 自定义数据，比如 id、username
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)   // 指定算法和密钥
                .setClaims(claims)                                // 添加负载
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .compact();
    }

    /**
     * 解析 JWT 令牌
     * @param token 待解析的 JWT
     * @return Claims (包含自定义数据和标准字段)
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)   // 指定密钥
                .parseClaimsJws(token)
                .getBody();
    }
}
