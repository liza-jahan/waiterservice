package com.example.waiterservice.userRegistration;


import com.example.waiterservice.model.request.UserRegistrationRequest;
import com.example.waiterservice.model.response.APIResponse;
import com.example.waiterservice.model.response.CreationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {
    private final  UserService userService;


    @PostMapping("/user-registration")
    public ResponseEntity<APIResponse<CreationResponse>> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest){
        log.info("Received user registration request: {}", userRegistrationRequest);
        UUID userId = userService.saveUser(userRegistrationRequest);
        APIResponse<CreationResponse> responseDTO = APIResponse
                .<CreationResponse>builder()
                .dateTime(new Date().toString())
                .status(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK)
                .results(new CreationResponse(userId))
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all-user-info")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> userEntities= userService.getAllUser();
        return new ResponseEntity<>(userEntities, HttpStatus.OK);
    }


    @GetMapping("/user-profile")
    public ResponseEntity<UserEntity> showProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        UUID id = UUID.fromString(userId);

        UserEntity userEntity = userService.getUserProfile(id);
        return ResponseEntity.ok(userEntity);
    }


//    @PostMapping("/admin")
//    public ResponseEntity<APIResponse<CreationResponse>> createAdmin(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest){
//        UUID userId = userService.createAdmin(userRegistrationRequest);
//        APIResponse<CreationResponse> responseDTO = APIResponse
//                .<CreationResponse>builder()
//                .dateTime(new Date().toString())
//                .status(HttpStatus.OK.getReasonPhrase())
//                .code(HttpStatus.OK)
//                .results(new CreationResponse(userId))
//                .build();
//
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
}



// userinfo table, edit,