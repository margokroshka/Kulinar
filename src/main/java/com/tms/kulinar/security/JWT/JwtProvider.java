package com.tms.kulinar.security.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtProvider {
    @Value("${jwt-secret-key}")
    public String Jwt_key;

    @Value("${expire-time}")
    public int expireTime;

    public String createJwtToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(expireTime)))
                .signWith(SignatureAlgorithm.HS256, Jwt_key)
                .compact();
    }

    public String getLoginFromJwt(String token) {
        return Jwts.parser()
                .setSigningKey(Jwt_key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(Jwt_key).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
