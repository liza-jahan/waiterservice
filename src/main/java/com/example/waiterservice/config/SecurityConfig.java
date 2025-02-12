package com.example.waiterservice.config;

import com.example.waiterservice.filter.JWTTokenGeneratorFilter;
import com.example.waiterservice.filter.JWTTokenValidatorFilter;
import com.example.waiterservice.filter.JwtFilter;
import com.example.waiterservice.filter.RequestValidationAfterFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new RequestValidationAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/user-registration", "/guest-user","/css/**","/js/**","/images/**",
//                                "/login","/api/v1/guest-user","/api/v1/**","/guest-registration-page",
//                                "/user-registration-page","/home-page","/dashboard","/service-page", "/webjars/**","/gallery",
//                                "/reset-password","/about-us","/show-group-list","/delete/**","/delete-group","/userinfoTable",
//                                "/guest-user-info","/profile/**","/profile","/user-profile/**","/groups/**",
//                                "/reset-password","/create","/profile","/contact").permitAll()

                                .requestMatchers("/css/**","/js/**","/images/**",
                                        "/api/v1/guest-user","/api/v1/**",
                                        "/home-page","/service-page", "/webjars/**","/gallery","/login", "/user-registration", "/guest-user", "/guest-registration-page", "/user-registration-page",
                                        "/reset-password","/about-us","/delete/**","/delete-group","/userinfoTable",
                                        "/guest-user-info","/profile/**","/profile","/user-profile/**","/groups/**",
                                        "/reset-password","/create","/profile","/contact").permitAll()
                        // URLs accessible only to users with the USER role
//                                .requestMatchers()
//                                .permitAll()
                                .requestMatchers( "/profile/**", "/groups/**")
                                .hasRole("USER")

                        // URLs accessible only to admins
                        .requestMatchers("/delete/**", "/delete-group", "/api/v1/**","/user-info-table","/show-group-list","/userinfoTable")
                        .hasRole("ADMIN")


                        //     .requestMatchers("/profile/**","/profile","/user-profile/**","/groups/**").authenticated()
                        .anyRequest()
                        .authenticated()
                )

                .authenticationProvider(authenticationProvider())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    //    @Bean
//    public CompromisedPasswordChecker compromisedPasswordChecker() {
//        return new HaveIBeenPwnedRestApiPasswordChecker();
//    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
