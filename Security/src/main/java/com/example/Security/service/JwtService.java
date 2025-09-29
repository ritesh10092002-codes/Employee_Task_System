package com.example.Security.service;


import com.example.Security.entity.sign;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }


    public String generateToken(sign user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name()) // include role in token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public String extractRole(String token) {
        return (String) Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
