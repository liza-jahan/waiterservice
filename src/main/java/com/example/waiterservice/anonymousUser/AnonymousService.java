package com.example.waiterservice.anonymousUser;

import com.example.waiterservice.model.request.AnonymousUserRequest;

import java.util.List;
import java.util.UUID;

public interface AnonymousService {
    UUID saveAnonymousUser(AnonymousUserRequest anonymousUserRequest);
    List<AnonymousUserEntity> getAllUser();
}
