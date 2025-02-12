package com.example.waiterservice.anonymousUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnonymousRepository extends JpaRepository<AnonymousUserEntity, UUID> {
}
