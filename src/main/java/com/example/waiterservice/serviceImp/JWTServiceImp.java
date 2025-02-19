package com.example.waiterservice.serviceImp;

import com.example.waiterservice.utils.ApplicationConstantsUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServiceImp {
//
//    @Value(ApplicationConstantsUtils.JWT_SECRET_DEFAULT_VALUE)
//    private String secretKey;
//
//    // Removed manual key generation logic
//
//    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))  // 30 minutes
//                .signWith(getKey())
//                .compact();
//    }
//
//    private SecretKey getKey() {
////        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // If the secret is base64 encoded
////        return Keys.hmacShaKeyFor(keyBytes); // Use the correct key for signing and validation
//        return Keys.secretKeyFor(SignatureAlgorithm.HS384);
//    }
//
//
//    public String extractUserName(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
////    private Claims extractAllClaims(String token) {
////        return Jwts.parserBuilder()
////                .setSigningKey(getKey())
////                .build()
////                .parseClaimsJws(token)
////                .getBody();
////    }
//public Claims extractAllClaims(String token) {  // Change from private to public
//    return Jwts.parserBuilder()
//            .setSigningKey(getKey())
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//}
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String userName = extractUserName(token);
//        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//}

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))  // 30 minutes
                .signWith(getKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    private SecretKey getKey() {
        // Use the key for HS384 algorithm
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String extractUserEmail(String token) {
//        return extractClaim(token, Claims::getSubject);
        Claims claims = extractAllClaims(token);
        return (String) claims.get("email");
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserEmail(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}