package com.cowras.auth_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key;

    @PostConstruct
    public void init() {

        this.key =
                Keys.hmacShaKeyFor(
                        secret.getBytes(
                                StandardCharsets.UTF_8
                        )
                );
    }

    /**
     * Generate JWT Token
     */
    public String generateToken(
            String email,
            String roles
    ) {

        return Jwts.builder()

                .setSubject(email)

                .claim("roles", roles)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration
                        )
                )

                .signWith(
                        key,
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    /**
     * Extract Email
     */
    public String extractEmail(String token) {

        return getClaims(token)
                .getSubject();
    }

    /**
     * Extract Roles
     */
    public List<String> extractRoles(String token) {

        return getClaims(token)
                .get("roles", List.class);
    }

    /**
     * Validate Token
     */
    public boolean validateToken(
            String token,
            String email
    ) {

        return extractEmail(token)
                .equals(email)
                && !isExpired(token);
    }

    /**
     * Check Expiration
     */
    private boolean isExpired(String token) {

        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    /**
     * Extract Claims
     */
    private Claims getClaims(String token) {

        return Jwts.parser()

                .setSigningKey(key)

                .build()

                .parseClaimsJws(token)

                .getBody();
    }
}