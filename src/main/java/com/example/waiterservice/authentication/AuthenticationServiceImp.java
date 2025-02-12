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
    @Value(ApplicationConstantsUtils.JWT_SECRET_DEFAULT_VALUE)
    private String jwtSecretValue;
    private  final UserRepository userRepository;

//    @Override
//    public String loginUser(AuthenticationRequest authRequest) {
//        try {
//            // Create an authentication object with the user's credentials
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    authRequest.email(), authRequest.password());
//
//            Authentication authenticationResponse = authenticationManager.authenticate(authentication);
//
//            if (authenticationResponse.isAuthenticated()) {
//                UserEntity user = userRepository.findByEmail(authRequest.email())
//                        .orElseThrow(() -> new RuntimeException("User not found"));
//                SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretValue.getBytes(StandardCharsets.UTF_8));
//
//                return Jwts.builder()
//                        .setIssuer("Waiter Service")
//                        .setSubject("JWT token")
//                        .claim("id", user.getId())  // Include ID in JWT
//                        .claim("username", authenticationResponse.getName())
//                        .claim("username", user.getName())
//                        .claim("email", user.getEmail())
//                        .claim("authorities", authenticationResponse.getAuthorities().stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.joining(",")))
//                        .setIssuedAt(new Date())
//                        .setExpiration(new Date((new Date()).getTime() + 30000000))
//                        .signWith(secretKey)
//                        .compact();
//            }
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Invalid credentials", e);
//        }
//
//        return null;
//    }
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

            // ✅ Generate JWT Token
            String jwtToken = Jwts.builder()
                    .setIssuer("Waiter Service")
                    .setSubject("JWT token")
                    .claim("id", user.getId()) // ✅ Include User ID from DB
                    .claim("username", user.getName())
                    .claim("email", user.getEmail())
                    .claim("authorities", authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")))
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
