package com.sazzler.ecommerce.util.JWTutil;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JWTUtil {

    private final SecretKey secretKey;
    private final long expirationTimeMillis;

    public JWTUtil(String secretKeyString, long expirationTimeMillis) {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyString));
        this.expirationTimeMillis = expirationTimeMillis;
    }

    //Generate JWT token
    public String generateToken(Long uid, String username, String email, Set<String> authorities) {
        return Jwts.builder()
                .subject(String.valueOf(uid))
                .claim("username", username)
                .claim("email", email)
                .claim("auth", authorities)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
                .signWith(secretKey)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        // Use parser, build the parser and then parse claims
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extract user ID from JWT token
    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    //Extract roles from JWT token
    @SuppressWarnings("unchecked")
    public Set<String> extractAuthorities(String token) {
        Claims claims = extractAllClaims(token);
        if (!claims.containsKey("auth")) {
            throw new JwtException("JWT token does not contain 'auth' claim");
        }
        // The claim is stored as a List, so we need to convert it to a Set
        List<String> authoritiesList = (List<String>) claims.get("auth");
        return authoritiesList.stream().collect(Collectors.toSet());
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            // Build parser then parse
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true; // Token is valid
        } catch (ExpiredJwtException e) {
            System.err.println("JWT Token is expired: " + e.getMessage());
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }
}
