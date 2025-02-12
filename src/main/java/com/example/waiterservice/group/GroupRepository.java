package com.example.waiterservice.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {
    @Query("select p from GroupEntity p where p.groupName like :key")
    GroupEntity findByGroupName(@Param("key") String name );
}
