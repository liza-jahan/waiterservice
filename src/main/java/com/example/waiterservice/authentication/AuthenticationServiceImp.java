package com.example.waiterservice.authentication;

import com.example.waiterservice.model.request.AuthenticationRequest;
import com.example.waiterservice.model.response.AuthenticationResponse;
import com.example.waiterservice.userRegistration.UserEntity;
import com.example.waiterservice.userRegistration.UserRepository;
import com.example.waiterservice.utils.ApplicationConstantsUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String jwtSecretValue;
    private  final UserRepository userRepository;

@Override
public AuthenticationResponse loginUser(AuthenticationRequest authRequest) {
    try {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password())
        );

        if (authentication.isAuthenticated()) {
            // ✅ Fetch user details from DB
            UserEntity user = userRepository.findByEmail(authRequest.email())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretValue.getBytes(StandardCharsets.UTF_8));

            String roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            // ✅ Generate JWT Token
            String jwtToken = Jwts.builder()
                    .setIssuer("Waiter Service")
                    .setSubject("JWT token")
                   // .claim("roles",roles)
                    .claim("id", user.getId()) // ✅ Include User ID from DB
                    .claim("username", user.getName())
                    .claim("email", user.getEmail())
//                    .claim("authorities", authentication.getAuthorities().stream()
//                            .map(GrantedAuthority::getAuthority)
//                            .collect(Collectors.joining(",")))
                    .claim("roles", authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 30000000))
                    .signWith(secretKey)
                    .compact();

            // ✅ Return both JWT token and User ID
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .id(user.getId()) // ✅ Return the actual user ID
                    .build();
        }
    } catch (AuthenticationException e) {
        throw new RuntimeException("Invalid credentials", e);
    }
    return null;
}

}
