package com.example.waiterservice.userRegistration;

import com.example.waiterservice.model.request.ResetPasswordRequest;
import com.example.waiterservice.model.request.UserRegistrationRequest;
import com.example.waiterservice.model.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UUID saveUser(UserRegistrationRequest userRequest);
    List<UserEntity> getAllUser();
    String resetPassword(ResetPasswordRequest request);
    UserEntity getUserProfile(UUID id);
    UserEntity getUserProfileByEmail(String email);

    //UUID createAdmin(UserRegistrationRequest userRegistrationRequest);
}

