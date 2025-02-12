package com.example.waiterservice.anonymousUser;

import com.example.waiterservice.model.request.AnonymousUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnonymousServiceImp implements AnonymousService{
    private final AnonymousRepository anonymousRepository;
    @Override
    public UUID saveAnonymousUser(AnonymousUserRequest anonymousUserRequest) {

        AnonymousUserEntity anonymousUserEntity=new AnonymousUserEntity();
        anonymousUserEntity.setUserName(anonymousUserRequest.getUserName());
        anonymousUserEntity.setEmail(anonymousUserRequest.getEmail());
        anonymousUserEntity.setMessage(anonymousUserRequest.getMessage());
        anonymousUserEntity.setPhoneNumber(anonymousUserRequest.getPhoneNumber());
        anonymousRepository.save(anonymousUserEntity);

        return anonymousUserEntity.getId();
    }

    @Override
    public List<AnonymousUserEntity> getAllUser() {
        return anonymousRepository.findAll();
    }
}
