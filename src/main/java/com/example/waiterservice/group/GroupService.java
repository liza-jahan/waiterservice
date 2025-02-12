package com.example.waiterservice.group;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    UUID createGroup(String groupName, List<UUID> userIds);
    void deleteGroup(UUID groupId);
    List<GroupEntity> getAllGroupsWithMembers();
    void addUserToGroup(String groupName, UUID userId);
}
