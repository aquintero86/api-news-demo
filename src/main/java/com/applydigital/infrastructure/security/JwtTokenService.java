package com.applydigital.infrastructure.security;

import com.applydigital.domain.security.TokenGenerator;
import com.applydigital.domain.security.TokenValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtTokenService implements TokenGenerator, TokenValidator {
    @Value("${jwt.secret-key}")
    private String secret;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public String generate(String serviceName) {
        return Jwts.builder()
                .subject(serviceName)
                .signWith(getKey())
                .compact();
    }

    @Override
    public boolean isValid(String token) {
        try {

            Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String extractService(String token) {
        return Jwts.parser().verifyWith(getKey()).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
