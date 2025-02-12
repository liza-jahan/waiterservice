package com.example.waiterservice.authentication;

import com.example.waiterservice.model.request.AuthenticationRequest;
import com.example.waiterservice.model.response.APIResponse;
import com.example.waiterservice.model.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@AllArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthenticationResponse>> login(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse  jwtToken = authenticationService.loginUser(request);

        // If the token is null (authentication failure), return a bad request response
        if (jwtToken == null) {
            APIResponse<AuthenticationResponse> errorResponse = APIResponse
                    .<AuthenticationResponse>builder()
                    .dateTime(new Date().toString())
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Build the API response with the JWT token
        APIResponse<AuthenticationResponse> responseDTO = APIResponse
                .<AuthenticationResponse>builder()
                .dateTime(new Date().toString())
                .status(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK)
                .results(jwtToken)
                .build();


        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
