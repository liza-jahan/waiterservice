package com.example.waiterservice.userRegistration;

import com.example.waiterservice.exception.NotFoundException;
import com.example.waiterservice.model.request.ResetPasswordRequest;
import com.example.waiterservice.model.request.UserRegistrationRequest;
import com.example.waiterservice.role.RoleEntity;
import com.example.waiterservice.role.RoleRepository;
import com.example.waiterservice.serviceImp.JWTServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTServiceImp jwtService;
    private final RoleRepository roleRepository;
    @Override
    public UUID saveUser(UserRegistrationRequest userRequest) {
        if (isUserExists(userRequest.getEmail())) {
            throw new IllegalArgumentException("This Email id is already registered;");
        }
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

        UserEntity userEntity=new UserEntity();

        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setGender(userRequest.getGender());
        userEntity.setPassword(encodedPassword);
        userEntity.setOccupation(userRequest.getOccupation());
        userEntity.setDateOfBirth(userRequest.getDateOfBirth());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setNationalIdCard(userRequest.getNationalIdCard());
        userEntity.setBkash(userRequest.getBkash());
        userEntity.setState(userRequest.getState());
        userEntity.setRole(userRequest.getRoles());


        String roleName = (userRequest.getRoles() != null) ? userRequest.getRoles() : "USER"; // ✅ Default role if null

        // Assigning Role to the User
        Optional<RoleEntity> optionalRole = roleRepository.findByName(roleName);
        RoleEntity roles = optionalRole.orElseGet(() -> {
            RoleEntity newRole = new RoleEntity();
            newRole.setName(roleName);
            return roleRepository.save(newRole);
        });
        userEntity.getRoles().add(roles);

        userRepository.save(userEntity);
        return userEntity.getId();
    }

    @Override
    public List<UserEntity> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public String resetPassword(ResetPasswordRequest request) {
        //verify token
//        String email = jwtService.verifyPasswordResetToken(request.getCode());
//
//        //update password
//        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
//        userRepository.updatePassword(email,encodedPassword);
        return null;
    }
    @Override
    public UserEntity getUserProfile(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserEntity getUserProfileByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found", "1-01-02"));
    }

//    @Override
//    public UUID createAdmin(UserRegistrationRequest userRegistrationRequest) {
//        if (isUserExists(userRegistrationRequest.getEmail())) {
//            throw new IllegalArgumentException("This Email id is already registered;");
//        }
//        String encodedPassword = passwordEncoder.encode(userRegistrationRequest.getPassword());
//
//        UserEntity admin=new UserEntity();
//
//        admin.setName(userRegistrationRequest.getName());
//        admin.setEmail(userRegistrationRequest.getEmail());
//        admin.setGender(userRegistrationRequest.getGender());
//        admin.setPassword(encodedPassword);
//        admin.setDateOfBirth(userRegistrationRequest.getDateOfBirth());
//        admin.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
//        admin.setNationalIdCard(userRegistrationRequest.getNationalIdCard());
//        admin.setBkash(userRegistrationRequest.getBkash());
//        admin.setState(userRegistrationRequest.getState());
//     //   admin.setRole(Role.ADMIN);
//
//        userRepository.save(admin);
//        return  admin.getId();
//    }

    private boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
