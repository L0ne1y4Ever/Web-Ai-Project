package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    //请帮我基于如下单元测试，改造成一个JWT令牌操作的工具类，类名：JwtUtils，具体要求如下：
    //1.工具类中有两个方法，一个用于生成JWT令牌，一个用于解析JWT令牌
    //2.生成令牌时使用的密钥和测试类中的一样
    //3.过期时限为12小时
    //测试生成JWT令牌
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap= new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"bG9uZWx5NGV2ZXI=")//指定加密算法及其密钥
                .addClaims(dataMap)//添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();//生成令牌
        System.out.println(jwt);
    }

    //测试解析JWT令牌
    @Test
    public void testParseJwt() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc1OTQ4NTM3MH0.u1wQDSo0r6R1Ks9uhhLzKYbNIuqIck9lY8iAcU0OgBI";
        Claims claims = Jwts.parser().setSigningKey("bG9uZWx5NGV2ZXI=")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);


    }
}
