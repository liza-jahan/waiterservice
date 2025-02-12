package com.example.waiterservice.model.request;

import java.util.UUID;

public record AuthenticationRequest(String email, String password, UUID id) {
}
