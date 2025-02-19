package com.example.waiterservice.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
@Component
@RequiredArgsConstructor
public class JWTTokenValidatorFilter extends OncePerRequestFilter {


    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostConstruct
    public void init() {
        System.out.println("JWT Secret: " + jwtSecret);
    }


    @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        System.out.println("JWT Secret in filterChain: " + jwtSecret);

        String jwt = request.getHeader("Authorization");

            // Debugging logs
            System.out.println("Authorization Header: " + jwt);

            if (jwt != null && jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7); // Remove "Bearer " prefix
            } else {
                System.out.println("Invalid Authorization Header");
                filterChain.doFilter(request, response);
                return;
            }

            try {
                SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                System.out.println("Extracted Claims: " + claims);

                String email = (String) claims.get("email");
                System.out.println("Extracted Email: " + email);

                if (email == null || email.isEmpty()) {
                    throw new BadRequestException("Invalid Token: Email not found");
                }

                String authorities = String.valueOf(claims.get("roles"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception ex) {
                System.out.println("JWT Parsing Error: " + ex.getMessage());
                throw new BadRequestException("Invalid Token received");
            }

            filterChain.doFilter(request, response);
        }
    }


