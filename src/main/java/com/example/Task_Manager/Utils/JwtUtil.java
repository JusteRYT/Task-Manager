package com.example.Task_Manager.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Key SECRET_KEY = keyGeneration();
    private static final  long EXPIRATION_TIME = 1000*60*60*10; //10 часов

    public String generateToken(String username) {
        assert SECRET_KEY != null;
        Key key = new SecretKeySpec(SECRET_KEY.getEncoded(), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        return !extractClaims(token).getExpiration().before(new Date());
    }

    public Claims extractClaims(String token) {
        assert SECRET_KEY != null;
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getEncoded())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    private static Key keyGeneration(){
        try {
           KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
