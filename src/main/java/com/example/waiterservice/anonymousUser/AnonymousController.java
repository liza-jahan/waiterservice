package com.example.waiterservice.anonymousUser;

import com.example.waiterservice.model.request.AnonymousUserRequest;
import com.example.waiterservice.model.response.APIResponse;
import com.example.waiterservice.model.response.CreationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AnonymousController {

    private final AnonymousService anonymousService;

    @PostMapping("/guest-user")
    public ResponseEntity<APIResponse<CreationResponse>> registerUser(@RequestBody @Valid AnonymousUserRequest anonymousUserRequest) {
        log.info("Received user registration request: {}", anonymousUserRequest);
        UUID userId = anonymousService.saveAnonymousUser(anonymousUserRequest);

        // Prepare the response data
        APIResponse<CreationResponse> responseDTO = APIResponse
                .<CreationResponse>builder()
                .dateTime(new Date().toString())
                .status(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK)
                .results(new CreationResponse(userId))
                .build();

        // Return the response with HTTP status OK
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/all-anonymous-user-info")
    public ResponseEntity<List<AnonymousUserEntity>> getAllUser() {
        List<AnonymousUserEntity> anonymousUserEntities = anonymousService.getAllUser();
        return new ResponseEntity<>(anonymousUserEntities, HttpStatus.OK);
    }
}
