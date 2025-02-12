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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found"+username));
        List<GrantedAuthority> authorities=userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new User(userEntity.getEmail(),userEntity.getPassword(),authorities);
    }
//@Override
//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    UserEntity userEntity = userRepository.findByEmail(username)
//            .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
//
//    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name());
//
//    return new User(userEntity.getEmail(), userEntity.getPassword(), Collections.singletonList(authority));
//}
}
