package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    // Ensure this key is at least 32 bytes (256 bits) and is a secure random value
    private String secretKey = "jndkjndjkndjknkdjnjkdnkdjndjkndjkndlknlknlknwlknldnskn211222"; // This key must be 32 bytes long or more

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            // Handle exceptions such as expired token or invalid signature
            return null;
        }
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUserRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? (String) claims.get("role") : null;  // Custom claim for user role
    }

    public String extractOrganization(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? (String) claims.get("organization") : null;  // Custom claim for organization
    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null && claims.getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {
        final String extractedUsername = extractUserName(token);
        return (extractedUsername != null && !isTokenExpired(token));
    }
}