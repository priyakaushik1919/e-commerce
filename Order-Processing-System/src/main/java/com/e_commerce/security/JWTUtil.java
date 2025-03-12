package com.e_commerce.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    private final String secret = "eD8ygdJ2jtFTlAzGg39W6CtA8IWQQCIs14Irj7Rmo9wdsfioueoiruewoirudxfjkldsjfdsflkjcxvnkjsdfsdf"; // Use environment variables in production
    private final long jwtExpirationInMs = 3600000; // 1 hour

    //Generate Token with userId and role
    public String generateToken(Long userId, String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);  // Add user ID
        claims.put("role", role);  // Add user role
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Extract Username
    public String getUsernameFromJWT(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    // Extract User ID
    public Long getUserIdFromJWT(String token) {
        Claims claims = extractClaims(token);
        return claims.get("id", Long.class);
    }

    // Extract Role
    public String getRoleFromJWT(String token) {
        Claims claims = extractClaims(token);
        return claims.get("role", String.class);
    }

    // Validate Token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            System.out.println("JWT Validation Failed: " + ex.getMessage());
        }
        return false;
    }

    // Extract Claims
    private Claims extractClaims(String token) {
    
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}

//package com.e_commerce.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//
//@Component
//public class JWTUtil {
//    private final String secret = "eD8ygdJ2jtFTlAzGg39W6CtA8IWQQCIs14Irj7Rmo9wdsfioueoiruewoirudxfjkldsjfdsflkjcxvnkjsdfsdf"; // Use environment variables in production
//    private final long jwtExpirationInMs = 3600000; // 1 hour
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    public String getUsernameFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
//            return true;
//        } catch (Exception ex) {
//            // Log exception or handle token errors as needed
//        }
//        return false;
//    }
//}
//
