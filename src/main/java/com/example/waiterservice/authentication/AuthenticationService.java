package com.example.waiterservice.authentication;


import com.example.waiterservice.model.request.AuthenticationRequest;
import com.example.waiterservice.model.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest);

}
