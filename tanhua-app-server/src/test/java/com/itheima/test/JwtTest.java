package com.itheima.test;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: slx
 * @create: 2023/9/15
 */

public class JwtTest {

    @Test
    public void testCreateToken() {
        // 生成token
        // 1、准备数据
        Map map = new HashMap();
        map.put("id", 1);
        map.put("mobile", "13800138000");
        long now = System.currentTimeMillis();

        // 使用jwt工具类生成token
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, "itcast") // 指定加密算法
                .setClaims(map) // 指定写入的数据
                .setExpiration(new Date(now + 50000)) // 设置失效时间
                .compact();

        System.out.println(token);
    }

    @Test
    public void testParseToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgwMDEzODAwMCIsImlkIjoxLCJleHAiOjE2OTQ3NTQ2MDl9.dRi6v_jFlCRnAGi9j7Jd9e-v1B0Bmve7gvz7afb59BNNSlSvo1xL0Vfe9Yy7bmBzmhlLxhpfz8OJQcpTlobWvw";

        // 解析token
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("itcast")
                    .parseClaimsJws(token)
                    .getBody();

            Object id = claims.get("id");
            Object mobile = claims.get("mobile");
            System.out.println(id + "---" + mobile);
        } catch (ExpiredJwtException e) {
            System.out.println("token已过期！");
        } catch (SignatureException e) {
            System.out.println("token不合法！");
        }
    }

}
