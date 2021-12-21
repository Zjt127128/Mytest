package com.example.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;


    //根据用户信息生成Token
    public String generateToken(UserDetails userDetails){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    //从Token中获取登录用户名
    public String getUerNameFormToken(String token){
        String username;
        try {
            Claims claims=getClaimsFormToken(token);
            username=claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;
    }

    //验证token是否有效
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUerNameFormToken(token);
        return username.equals(userDetails.getUsername())&& !isTokenExpirde(token);
    }
    //判断token是否失效
    private boolean isTokenExpirde(String token) {
        Date expireDate=getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    //判断token是否被刷新
    public boolean canRefresh(String token){
        return !isTokenExpirde(token);
    }

    //刷新token
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    //从token中获取失效时间
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    //从Token中获取荷载
    private Claims getClaimsFormToken(String token) {
        Claims claims=null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


    //根据荷载生成JWT Token
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    //生成Token失效时间
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

}
