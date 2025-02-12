package com.example.deviceshop.service;

import com.example.deviceshop.model.request.AuthenticationRequest;

public interface AuthenticationService {
    String loginUser(AuthenticationRequest authenticationRequest);

}
