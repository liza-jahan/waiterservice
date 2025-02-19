package com.example.waiterservice.serviceImp;


import com.example.waiterservice.exception.UserNotFoundException;
import com.example.waiterservice.userRegistration.UserEntity;
import com.example.waiterservice.userRegistration.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity=userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found"+username));
//        List<GrantedAuthority> authorities=userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        System.out.println("User: " + username + ", Roles: " + authorities);
//        // Debugging Output
//        System.out.println("User: " + userEntity.getEmail());
//        System.out.println("Roles Found: " + userEntity.getRoles().size());  // Check if roles exist
//        System.out.println("Authorities: " + authorities);  // Print the mapped authorities
//
//        return new User(userEntity.getEmail(),userEntity.getPassword(),authorities);
//    }
@Transactional
@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + email));

        System.out.println("User found: " + userEntity.getEmail());

        if (userEntity.getRoles().isEmpty()) {
            System.out.println("⚠️ No roles found for user: " + email);
        } else {
            System.out.println("✅ Roles found: " + userEntity.getRoles());
        }

        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        System.out.println("Authorities: " + authorities); // 🔥 Debugging roles

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
